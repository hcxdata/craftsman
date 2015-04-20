package com.bigbata.craftsman.aspect;

import com.bigbata.craftsman.bean.DictFileInitBean;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by lixianghui on 15-4-10.
 */
@Aspect
@Component
public class DictFileAspect {
    @Autowired
    DictFileInitBean dictFileInitBean;

    @After("execution(* com.bigbata.craftsman.api.system.SysDictApi.create(..)) || execution(* com.bigbata.craftsman.api.system.SysDictApi.edit(..)) || execution(* com.bigbata.craftsman.api.system.SysDictApi.destory(..)) || execution(* com.bigbata.craftsman.api.system.SysDictApi.up(..))|| execution(* com.bigbata.craftsman.api.system.SysDictApi.edit(..))|| execution(* com.bigbata.craftsman.api.system.SysDictApi.down(..)))")
    public void afterCreateFile() throws IOException {
        dictFileInitBean.init();
    }
}
