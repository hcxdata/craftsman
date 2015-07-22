package com.bigbata.craftsman.aspect;

import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Properties;

/**
 * 管理业务的事务处理
 * Created by lixianghui on 15-7-15.
 */
@Configuration
public class TransactionAspect {

    @Bean
    public Pointcut transPointCut() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.bigbata.craftsman.api..*.*(..))");
        return pointcut;
    }

    @Bean
    public TransactionInterceptor transactionInterceptor() {
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        transactionInterceptor.setTransactionAttributes(transactionAttributes());
        transactionInterceptor.setTransactionManagerBeanName("transactionManager");
        return transactionInterceptor;
    }

    @Bean
    public PointcutAdvisor pointcutAdvisor() {
        DefaultBeanFactoryPointcutAdvisor advisor = new DefaultBeanFactoryPointcutAdvisor();
        advisor.setPointcut(transPointCut());
        advisor.setAdvice(transactionInterceptor());
        return advisor;
    }

    Properties transactionAttributes() {
        Properties properties = new Properties();
        properties.setProperty("*", "PROPAGATION_REQUIRED");
        properties.setProperty("index*", "PROPAGATION_REQUIRED,readOnly");
        properties.setProperty("show*", "PROPAGATION_REQUIRED,readOnly");
        properties.setProperty("query*", "PROPAGATION_REQUIRED,readOnly");
        properties.setProperty("find*", "PROPAGATION_REQUIRED,readOnly");
        properties.setProperty("create*", "PROPAGATION_REQUIRED,-java.lang.Exception");//-代表异常回滚，+代码异常提交
        properties.setProperty("edit*", "PROPAGATION_REQUIRED,-java.lang.Exception");
        properties.setProperty("destory*", "PROPAGATION_REQUIRED,-java.lang.Exception");
        return properties;
    }
}