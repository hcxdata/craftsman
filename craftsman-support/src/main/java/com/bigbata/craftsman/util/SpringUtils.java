package com.bigbata.craftsman.util;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;

/**
 * Created by lixianghui on 15-7-14.
 */
public class SpringUtils {

    private static <T> T getRealTarget(T t) throws Exception {
        if (!AopUtils.isAopProxy(t)) {
            return t;
        } else {
            return (T) ((Advised) t).getTargetSource().getTarget();
        }
    }

    public static Class<?>[] getInterfaces(Object t) {
        if (!AopUtils.isAopProxy(t)) {
            return t.getClass().getInterfaces();
        } else {
            return ((Advised) t).getProxiedInterfaces();
        }
    }
}
