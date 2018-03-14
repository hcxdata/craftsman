/**
 *
 */
package com.bigbata.craftsman.config.dataconfig;

import com.bigbata.craftsman.config.datasource.DataSourceConfig;
import com.bigbata.craftsman.config.datasource.DynamicDataSource;
import com.bigbata.craftsman.dao.mybatis.OffsetLimitInterceptor;
import com.googlecode.flyway.core.Flyway;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * 类说明:<br>
 * 创建时间: 2014年12月12日 下午6:05:36<br>
 */
@Configuration
@PropertySource("classpath:db.properties")
@EnableJpaRepositories(basePackages = "com.bigbata.craftsman.dao")
@MapperScan("com.bigbata.craftsman.dao")
public class DataConfig {
    @Autowired
    private Environment env;

    //数据源的配置
    @Bean
    public DataSourceConfig dataSourceConfig() {
        return new DataSourceConfig();
    }

    @Bean
    public DataSource dataSource() {
        return new DynamicDataSource();
    }

    //jpa 的配置
    @Bean
    @DependsOn({"flyway"})
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

        DataSourceConfig config = dataSourceConfig();

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(new String[]{"com.bigbata.craftsman.dao.model"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(
            EntityManagerFactory emf) {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "none");
        properties.setProperty("hibernate.dialect",
                "org.hibernate.dialect.MySQL5Dialect");
        return properties;
    }

    //MyBatis的配置
    @Bean
    @DependsOn({"flyway"})
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        //mybatis分页拦截器
        Interceptor offsetLimitInterceptor = new OffsetLimitInterceptor();
        offsetLimitInterceptor.setProperties(mybatisLimitPorperties());
        Interceptor[] interceptors = new Interceptor[]{offsetLimitInterceptor};
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeAliasesPackage("com.bigbata.craftsman.dao.model");
        sessionFactory.setPlugins(interceptors);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sessionFactory;
    }

    private Properties mybatisLimitPorperties() {
        Properties pro = new Properties();
        pro.put("dialectClass", "com.bigbata.craftsman.dao.mybatis.dialect.MySQLDialect");
        return pro;
    }

    //flyway配置
    @Bean
    @DependsOn({"dataSource"})
    public Flyway flyway(DataSource dataSource) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setInitOnMigrate(true);
        flyway.migrate();

        return flyway;
    }
}
