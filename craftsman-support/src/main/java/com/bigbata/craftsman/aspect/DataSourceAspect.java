package com.bigbata.craftsman.aspect;

import com.bigbata.craftsman.config.Constants;
import com.bigbata.craftsman.config.datasource.DataSourceContainer;
import com.bigbata.craftsman.util.SpringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据调用类的包名称的最后一个值是否为数据源,切换数据源
 * Created by lixianghui on 15-7-13.
 */
@Aspect
@Component
public class DataSourceAspect {
    @Before("execution(* com.bigbata.craftsman.dao..*.*(..))")
    public void changeDataSource(JoinPoint joinpoint) throws Exception {
        Object obj = joinpoint.getTarget();
        List<String> packageNames = getLocalPackages(obj);
        for (String packageName : packageNames) {
            String[] packArray = packageName.split("\\.");
            if (packArray.length > 0) {
                String lastName = packArray[packArray.length - 1];
                if (!StringUtils.isEmpty(lastName) && DataSourceContainer.containsKey(lastName))
                    DataSourceContainer.setCurrentDateSource(lastName);
                else
                    DataSourceContainer.setCurrentDateSource(Constants.DEFAULT_DATA_SOURCE);
            }
        }
    }

    /**
     * 获取类的本地定义接口名称
     */
    private List<String> getLocalPackages(Object obj) {
        String[] ignoresInterfaces = new String[]{"org.springframework"};
        Class<?>[] interfaces = SpringUtils.getInterfaces(obj);
        List<String> targetResult = new ArrayList<>();
        for (Class<?> inter : interfaces) {
            for (String ignoreInterface : ignoresInterfaces) {
                String interPackageName = inter.getPackage().getName();
                if (!interPackageName.contains(ignoreInterface)) {
                    targetResult.add(interPackageName);
                }
            }
        }
        return targetResult;
    }
}
