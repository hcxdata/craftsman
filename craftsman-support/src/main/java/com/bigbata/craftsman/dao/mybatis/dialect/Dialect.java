package com.bigbata.craftsman.dao.mybatis.dialect;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.SimpleTypeRegistry;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;

/**
 * 类似hibernate的Dialect,但只精简出分页部分
 */
public class Dialect {
    protected TypeHandlerRegistry typeHandlerRegistry;
    protected MappedStatement mappedStatement;
    protected Pageable pageable;
    protected Object parameterObject;
    protected BoundSql boundSql;
    protected List<ParameterMapping> parameterMappings;
    protected Map<String, Object> pageParameters = new HashMap<String, Object>();
    private String pageSQL;
    private String countSQL;


    public Dialect(MappedStatement mappedStatement, Object parameterObject, Pageable pageable) {
        this.mappedStatement = mappedStatement;
        this.parameterObject = parameterObject;
        this.pageable = pageable;
        this.typeHandlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();

        init();
    }

    protected void init() {

        boundSql = mappedStatement.getBoundSql(parameterObject);
        //融合分页参数信息到参数信息中
        parameterMappings = new ArrayList(boundSql.getParameterMappings());
        if (parameterObject instanceof Map) {
            pageParameters.putAll((Map) parameterObject);
        } else if (parameterObject != null) {
            Class cls = parameterObject.getClass();
            if (cls.isPrimitive() || cls.isArray() ||
                    SimpleTypeRegistry.isSimpleType(cls) ||
                    Enum.class.isAssignableFrom(cls) ||
                    Collection.class.isAssignableFrom(cls)) {
                for (ParameterMapping parameterMapping : parameterMappings) {
                    pageParameters.put(parameterMapping.getProperty(), parameterObject);
                }
            } else {
                MetaObject metaObject = mappedStatement.getConfiguration().newMetaObject(parameterObject);
                ObjectWrapper wrapper = metaObject.getObjectWrapper();
                for (ParameterMapping parameterMapping : parameterMappings) {
                    PropertyTokenizer prop = new PropertyTokenizer(parameterMapping.getProperty());
                    pageParameters.put(parameterMapping.getProperty(), wrapper.get(prop));
                }
            }

        }

        //去除分号
        StringBuffer bufferSql = new StringBuffer(boundSql.getSql().trim());
        if (bufferSql.lastIndexOf(";") == bufferSql.length() - 1) {
            bufferSql.deleteCharAt(bufferSql.length() - 1);
        }
        String sql = bufferSql.toString();
        pageSQL = sql;
        //添加order by 信息
        if (pageable != null && pageable.getSort() != null && pageable.getSort().iterator() != null && pageable.getSort().iterator().hasNext()) {
            pageSQL = getSortString(sql, pageable.getSort());
        }
        //添加分页内容信息
        if (pageable.getOffset() != RowBounds.NO_ROW_OFFSET
                || pageable.getPageSize() != RowBounds.NO_ROW_LIMIT) {
            pageSQL = getLimitString(pageSQL, "__offset", pageable.getOffset(), "__limit", pageable.getPageSize());
        }
        //获取总行数信息
        countSQL = getCountString(sql);
    }


    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public Object getParameterObject() {
        return pageParameters;
    }


    public String getPageSQL() {
        return pageSQL;
    }

    protected void setPageParameter(String name, Object value, Class type) {
        ParameterMapping parameterMapping = new ParameterMapping.Builder(mappedStatement.getConfiguration(), name, type).build();
        parameterMappings.add(parameterMapping);
        pageParameters.put(name, value);
    }


    public String getCountSQL() {
        return countSQL;
    }


    /**
     * 将sql变成分页sql语句
     */
    protected String getLimitString(String sql, String offsetName, int offset, String limitName, int limit) {
        throw new UnsupportedOperationException("paged queries not supported");
    }

    /**
     * 将sql转换为总记录数SQL
     *
     * @param sql SQL语句
     * @return 总记录数的sql
     */
    protected String getCountString(String sql) {
        return "select count(1) from (" + sql + ") tmp_count";
    }

    /**
     * 将sql转换为带排序的SQL
     *
     * @param sql SQL语句
     * @return 总记录数的sql
     */
    protected String getSortString(String sql, Sort sort) {
        Iterator<Sort.Order> orders = sort.iterator();
        if (orders == null || !orders.hasNext()) {
            return sql;
        }

        StringBuffer buffer = new StringBuffer("select * from (").append(sql).append(") temp_order order by ");
        while (orders.hasNext()) {
            Sort.Order order = orders.next();
            if (order != null) {
                buffer.append(order.getProperty()).append(" ").append(order.getDirection())
                        .append(", ");
            }
        }
        buffer.delete(buffer.length() - 2, buffer.length());
        return buffer.toString();
    }
}
