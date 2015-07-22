package com.bigbata.craftsman.api.system;

import com.bigbata.craftsman.dao.model.SysParamTypeEntity;
import com.bigbata.craftsman.dao.system.SysParamDao;
import com.bigbata.craftsman.dao.system.SysParamTypeDao;
import com.bigbata.craftsman.exception.ME;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

/**
 * Created by lixianghui on 15-5-15.
 */
@RestController
@RequestMapping({"/api/system/paramTypes"})
public class SysParamTypeApi {
    @Autowired
    private SysParamTypeDao sysParamTypeDao;
    @Autowired
    private SysParamDao sysParamDao;

    @RequestMapping(method = RequestMethod.GET)
    public Page<SysParamTypeEntity> index(@PageableDefault Pageable pageable, String name, String code) {

        if (name == null) name = "%";
        else name = "%" + name + "%";
        if (code == null) code = "%";
        else code = "%" + code + "%";
        return sysParamTypeDao.findByNameLikeAndCodeLike(pageable, name, code);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SysParamTypeEntity show(@PathVariable Integer id) {
        return sysParamTypeDao.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public SysParamTypeEntity create(@RequestBody SysParamTypeEntity sysParamTypeEntity) {
        if (sysParamTypeDao.findByName(sysParamTypeEntity.getName()) != null) {
            throw new ME("该名称的参数类型已存在");
        }
        if (sysParamTypeDao.findByCode(sysParamTypeEntity.getCode()) != null) {
            throw new ME("该编号的参数类型已存在");
        }
        return sysParamTypeDao.save(sysParamTypeEntity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void edit(@RequestBody SysParamTypeEntity sysParamTypeEntity) {
        SysParamTypeEntity tmp = sysParamTypeDao.findOne(sysParamTypeEntity.getId());
        if (!tmp.getCode().equals(sysParamTypeEntity.getCode())) {
            if (sysParamTypeDao.findByCode(sysParamTypeEntity.getCode()) != null) {
                throw new ME("该编号的参数类型已存在");
            }
        }
        if (!tmp.getName().equals(sysParamTypeEntity.getName())) {
            if (sysParamTypeDao.findByName(sysParamTypeEntity.getName()) != null) {
                throw new ME("该名称的参数类型已存在");
            }
        }
        sysParamTypeDao.save(sysParamTypeEntity);
        if (!tmp.getCode().equals(sysParamTypeEntity.getCode())) {
            sysParamDao.updateDictType(tmp.getCode(), sysParamTypeEntity.getCode());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void destory(@PathVariable Integer id) {
        SysParamTypeEntity sysParamTypeEntity = sysParamTypeDao.findOne(id);
        sysParamDao.delByTypeCode(sysParamTypeEntity.getCode());
        sysParamTypeDao.delete(id);
    }
}
