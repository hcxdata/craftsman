package com.bigbata.craftsman.dao.mybatis;

import com.bigbata.craftsman.dao.mybatis.dialect.Dialect;
import com.bigbata.craftsman.dao.mybatis.support.PropertiesHelper;
import com.bigbata.craftsman.dao.mybatis.support.SQLHelp;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.apache.ibatis.session.ResultHandler;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * Created by lixianghui on 15-8-1.
 */
@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class OffsetLimitInterceptor implements Interceptor {

    private static Logger logger = LoggerFactory.getLogger(OffsetLimitInterceptor.class);
    static int MAPPED_STATEMENT_INDEX = 0;//第一个参数 satement
    static int PARAMETER_INDEX = 1;//第二个参数 查询用参数
    static int ROWBOUNDS_INDEX = 2;//第三个参数 分页信息

    static ExecutorService Pool;
    String dialectClass;
    boolean asyncTotalCount = true;//是否执行异步操作

    //参数设置
    @Override
    public void setProperties(Properties properties) {
        PropertiesHelper propertiesHelper = new PropertiesHelper(properties);
        //方言执行类
        String dialectClass = propertiesHelper.getRequiredString("dialectClass");
        setDialectClass(dialectClass);
        //是否异步操作
        setAsyncTotalCount(propertiesHelper.getBoolean("asyncTotalCount", false));
        //线程词个数
        setPoolMaxSize(propertiesHelper.getInt("poolMaxSize", 0));
    }

    //拦截方法
    @Override
    public Object intercept(final Invocation invocation) throws Throwable {
        //获取参数
        final Executor executor = (Executor) invocation.getTarget();
        final Object[] queryArgs = invocation.getArgs();
        final MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
        final Object parameter = queryArgs[PARAMETER_INDEX];
        final Pageable pageable = (Pageable) findPageableObject(parameter);

        //没有分页信息，继续执行
        if (pageable == null || (pageable.getOffset() == RowBounds.NO_ROW_OFFSET
                && pageable.getPageNumber() == RowBounds.NO_ROW_LIMIT
        )) {
            return invocation.proceed();
        }

        //创建方言执行器
        final Dialect dialect;
        try {
            Class clazz = Class.forName(dialectClass);
            Constructor constructor = clazz.getConstructor(MappedStatement.class, Object.class, Pageable.class);
            dialect = (Dialect) constructor.newInstance(new Object[]{ms, parameter, pageable});
        } catch (Exception e) {
            throw new ClassNotFoundException("Cannot create dialect instance: " + dialectClass, e);
        }

        //获取执行的SQL
        final BoundSql boundSql = ms.getBoundSql(parameter);

        //改造执行SQL - 添加order 信息
        queryArgs[MAPPED_STATEMENT_INDEX] = copyFromNewSql(ms, boundSql, dialect.getPageSQL(), dialect.getParameterMappings(), dialect.getParameterObject());
        //参数信息 - 融合分页内容信息
        queryArgs[PARAMETER_INDEX] = dialect.getParameterObject();
        //分页信息
        queryArgs[ROWBOUNDS_INDEX] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
        //异步分页查询结果
        Future<List> listFuture = call(new Callable<List>() {
            public List call() throws Exception {
                return (List) invocation.proceed();
            }
        }, asyncTotalCount);

        //异步统计查询结果
        Future<Long> countFutrue = call(new Callable() {
            public Object call() throws Exception {
                return SQLHelp.getCount(ms, executor.getTransaction(), parameter, boundSql, dialect);
            }
        }, asyncTotalCount);

        return new ArrayList() {{
            add(new PageImpl(listFuture.get(), pageable, countFutrue.get()));
        }};

    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 在方法参数中查找 分页请求对象
     *
     * @param params Mapper接口方法中的参数对象
     * @return
     */
    private Pageable findPageableObject(Object params) {

        if (params == null) {
            return null;
        }

        // 单个参数 表现为参数对象
        if (Pageable.class.isAssignableFrom(params.getClass())) {
            return (Pageable) params;
        }
        // 多个参数 表现为 ParamMap
        else if (params instanceof MapperMethod.ParamMap) {
            MapperMethod.ParamMap<Object> paramMap = (MapperMethod.ParamMap<Object>) params;
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                Object paramValue = entry.getValue();

                if (paramValue != null && Pageable.class.isAssignableFrom(paramValue.getClass())) {
                    return (Pageable) paramValue;
                }
            }
        }

        return null;
    }


    //执行异步操作
    private <T> Future<T> call(Callable callable, boolean async) {
        if (async) {
            return Pool.submit(callable);
        } else {
            FutureTask<T> future = new FutureTask(callable);
            future.run();
            return future;
        }
    }

    private MappedStatement copyFromNewSql(MappedStatement ms, BoundSql boundSql,
                                           String sql, List<ParameterMapping> parameterMappings, Object parameter) {
        BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, sql, parameterMappings, parameter);
        return copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
    }

    private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql,
                                      String sql, List<ParameterMapping> parameterMappings, Object parameter) {
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, parameterMappings, parameter);
        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }
        return newBoundSql;
    }

    //see: MapperBuilderAssistant
    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        Builder builder = new Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());

        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuffer keyProperties = new StringBuffer();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }

        //setStatementTimeout()
        builder.timeout(ms.getTimeout());

        //setStatementResultMap()
        builder.parameterMap(ms.getParameterMap());

        //setStatementResultMap()
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());

        //setStatementCache()
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }

    //
    public static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

    //设置方言类信息
    public void setDialectClass(String dialectClass) {
        logger.debug("dialectClass: {} ", dialectClass);
        this.dialectClass = dialectClass;
    }

    //是否进行异步统计
    public void setAsyncTotalCount(boolean asyncTotalCount) {
        logger.debug("asyncTotalCount: {} ", asyncTotalCount);
        this.asyncTotalCount = asyncTotalCount;
    }

    //设置线程池最大线程数
    public void setPoolMaxSize(int poolMaxSize) {
        if (poolMaxSize > 0) {
            logger.debug("poolMaxSize: {} ", poolMaxSize);
            Pool = Executors.newFixedThreadPool(poolMaxSize);
        } else {
            Pool = Executors.newCachedThreadPool();
        }
    }
}
