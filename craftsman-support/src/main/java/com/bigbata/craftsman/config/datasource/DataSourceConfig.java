package com.bigbata.craftsman.config.datasource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * 从db.properties中获取配置信息，动态的生产数据源
 * Created by lixianghui on 15-7-13.
 */
@PropertySource("classpath:db.properties")
public class DataSourceConfig implements ApplicationContextAware {
    @Autowired
    private Environment env;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        initDataSource(applicationContext);
    }

    public void initDataSource(ApplicationContext app) {
        //将applicationContext转换为ConfigurableApplicationContext
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) app;

        // 获取bean工厂并转换为DefaultListableBeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext
                .getBeanFactory();

        String dataSrouces = env.getProperty("datasources");
        if (!StringUtils.isEmpty(dataSrouces) && dataSrouces.split(",").length > 0) {
            String[] dsArray = dataSrouces.split(",");
            for (String ds : dsArray) {
                String driverClassName = env.getProperty(ds + ".driverClassName");
                String url = env.getProperty(ds + ".url");
                String username = env.getProperty(ds + ".username");
                String password = env.getProperty(ds + ".password");
                Assert.isTrue(!StringUtils.isEmpty(driverClassName), ds + "数据源的driverClassName未定义");
                Assert.isTrue(!StringUtils.isEmpty(url), ds + "数据源的url未定义");
                Assert.isTrue(!StringUtils.isEmpty(username), ds + "数据源的username未定义");
                Assert.isTrue(!StringUtils.isEmpty(password), ds + "数据源的password未定义");

                BeanDefinitionBuilder beanDefinitionBuilder = initDataSource(driverClassName, url, username, password);
                defaultListableBeanFactory.registerBeanDefinition(ds, beanDefinitionBuilder.getBeanDefinition());
                DataSourceContainer.add(ds, (DataSource) configurableApplicationContext.getBean(ds));
            }
        }
    }

    public BeanDefinitionBuilder initDataSource(String driverClassName, String url, String username, String password) {
        BeanDefinitionBuilder ds = BeanDefinitionBuilder
                .rootBeanDefinition(DriverManagerDataSource.class);
        ds.addPropertyValue("driverClassName", driverClassName);
        ds.addPropertyValue("url", url);
        ds.addPropertyValue("username", username);
        ds.addPropertyValue("password", password);
        return ds;
    }
}
