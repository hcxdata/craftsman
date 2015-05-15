package com.bigbata.craftsman.api.system;

import com.bigbata.craftsman.dao.model.SysParamEntity;
import com.bigbata.craftsman.dao.system.SysParamDao;
import com.bigbata.craftsman.exception.ME;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import javax.transaction.Transactional;


/**
 * Created by lixianghui on 15-5-15.
 */
@RestController
@RequestMapping({"/api/system/params"})
public class SysParamApi {
    @Autowired
    private SysParamDao sysParamDao;

    @RequestMapping(method = RequestMethod.GET)
    public Page<SysParamEntity> index(@PageableDefault Pageable pageable, String typeCode, String name, String code) {
        if (typeCode == null) typeCode = "null";
        if (name == null) name = "%%";
        else name = "%" + name + "%";
        if (code == null) code = "%%";
        else code = "%" + code + "%";
        return sysParamDao.findByTypeCodeAndNameLikeAndCodeLike(pageable, typeCode, name, code);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SysParamEntity show(@PathVariable Integer id) {
        return sysParamDao.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public SysParamEntity create(@RequestBody SysParamEntity sysParamEntity) {
        if (sysParamDao.findByName(sysParamEntity.getName()) != null) {
            throw new ME("已存在该名称的参数");
        }
        if (sysParamDao.findByCode(sysParamEntity.getCode()) != null) {
            throw new ME("已存在该编号的参数");
        }
        return sysParamDao.save(sysParamEntity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void edit(@RequestBody SysParamEntity sysParamEntity) {
        SysParamEntity tmp = sysParamDao.findOne(sysParamEntity.getId());
        if (!tmp.getCode().equals(sysParamEntity.getCode())) {
            if (sysParamDao.findByCode(sysParamEntity.getCode()) != null) {
                throw new ME("已存在该编号的参数");
            }
        }
        if (!tmp.getName().equals(sysParamEntity.getName())) {
            if (sysParamDao.findByName(sysParamEntity.getName()) != null) {
                throw new ME("已存在该名称的参数");
            }
        }
        sysParamDao.save(sysParamEntity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @Transactional
    public void destory(@PathVariable Integer id) {
        sysParamDao.delete(id);
    }

}
