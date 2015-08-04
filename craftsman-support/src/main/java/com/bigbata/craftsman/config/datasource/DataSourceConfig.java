package com.bigbata.craftsman.config.datasource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySources;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * 从db.properties中获取配置信息，动态的生产数据源
 * Created by lixianghui on 15-7-13.
 */
public class DataSourceConfig implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            initDataSource(beanFactory);
        } catch (IOException e) {
            Assert.isTrue(false, "db.properties的配置信息无法获取!");
        }
    }

    public void initDataSource(ConfigurableListableBeanFactory defaultListableBeanFactory) throws IOException {
        // 获取bean工厂并转换为DefaultListableBeanFactory
        ResourcePropertySource env = new ResourcePropertySource("resource", "classpath:db.properties");
        String dataSrouces = (String) env.getProperty("datasources");
        if (!StringUtils.isEmpty(dataSrouces) && dataSrouces.split(",").length > 0) {
            String[] dsArray = dataSrouces.split(",");
            for (String ds : dsArray) {
                String driverClassName = (String) env.getProperty(ds + ".driverClassName");
                String url = (String) env.getProperty(ds + ".url");
                String username = (String) env.getProperty(ds + ".username");
                String password = (String) env.getProperty(ds + ".password");
                Assert.isTrue(!StringUtils.isEmpty(driverClassName), ds + "数据源的driverClassName未定义");
                Assert.isTrue(!StringUtils.isEmpty(url), ds + "数据源的url未定义");
                Assert.isTrue(!StringUtils.isEmpty(username), ds + "数据源的username未定义");
                Assert.isTrue(!StringUtils.isEmpty(password), ds + "数据源的password未定义");

                defaultListableBeanFactory.registerSingleton(ds, initDataSource(driverClassName, url, username, password));
                DataSourceContainer.add(ds, (DataSource) defaultListableBeanFactory.getBean(ds));
            }
        }
    }

    public DataSource initDataSource(String driverClassName, String url, String username, String password) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }
}
