package com.bigbata.craftsman.bean;

import com.bigbata.craftsman.dao.model.SysDictEntity;
import com.bigbata.craftsman.dao.model.SysDictTypeEntity;
import com.bigbata.craftsman.dao.system.SysDictDao;
import com.bigbata.craftsman.dao.system.SysDictTypeDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixianghui on 15-4-10.
 */
@Component
public class DictFileInitBean {
    private static String jsFilePath = "/frame/js/dict/dictData.js";
    Resource res = null;

    @Autowired
    private ApplicationContext appContext;
    @Autowired
    private SysDictTypeDao sysDictTypeDao;
    @Autowired
    private SysDictDao sysDictDao;

    public void init() throws IOException {
        //创建文件
        res = createFile();
        //写头信息
        writeHead();
        //写内容
        writeContent();
    }

    private Resource createFile() throws IOException {
        File file = appContext.getResource(jsFilePath).getFile();
        if (file.exists())
            file.delete();
        file.createNewFile();
        return appContext.getResource(jsFilePath);
    }

    private void writeHead() throws IOException {
        writeFileLine("Main.dict={}");
        writeFileLine("Main.dict.data=");
    }

    private void writeContent() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        writeFileLine(objectMapper.writeValueAsString(getDictDataContent()));
    }

    private Map<String, Object> getDictDataContent() {
        Map map = new HashMap();
        Iterable<SysDictTypeEntity> types = sysDictTypeDao.findAll();
        for (SysDictTypeEntity type : types) {
            Map content = new HashMap<>();
            content.put("name", type.getName());
            List<SysDictEntity> dicts = sysDictDao.findByTypeCode(type.getCode());
            content.put("data", dicts);

            map.put(type.getCode(), content);
        }
        return map;
    }

    private void writeFileLine(String content) throws IOException {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(res.getFile(), true)));
            out.println(content);
            out.flush();
        } finally {
            if (out != null)
                out.close();
        }
    }
}
