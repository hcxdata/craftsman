package com.bigbata.craftsman.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Created by lixianghui on 15-8-4.
 */
@Component
public class InitBean {
    @Autowired
    DictFileInitBean dictFileInitBean;

    public void init() throws IOException {
        dictFileInitBean.init();
    }
}
