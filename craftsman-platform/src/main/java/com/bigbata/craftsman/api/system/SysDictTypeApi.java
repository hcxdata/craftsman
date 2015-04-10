package com.bigbata.craftsman.api.system;

import com.bigbata.craftsman.dao.model.SysDictTypeEntity;
import com.bigbata.craftsman.dao.system.SysDictDao;
import com.bigbata.craftsman.dao.system.SysDictTypeDao;
import com.bigbata.craftsman.exception.ME;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

/**
 * Created by lixianghui on 15-4-2.
 */
@RestController
@RequestMapping({"/api/system/dictTypes"})
public class SysDictTypeApi {
    @Autowired
    private SysDictTypeDao sysDictTypeDao;
    @Autowired
    private SysDictDao sysDictDao;

    @RequestMapping(method = RequestMethod.GET)
    public Page<SysDictTypeEntity> index(@PageableDefault Pageable pageable, String name, String code) {
        if (name == null) name = "%";
        else name = "%" + name + "%";
        if (code == null) code = "%";
        else code = "%" + code + "%";
        return sysDictTypeDao.findByNameLikeAndCodeLike(pageable, name, code);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SysDictTypeEntity show(@PathVariable Integer id) {
        return sysDictTypeDao.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public SysDictTypeEntity create(@RequestBody SysDictTypeEntity sysDictType) {
        if (sysDictTypeDao.findByName(sysDictType.getName()) != null)
            throw new ME("该名称字典类型已存在");
        if (sysDictTypeDao.findByCode(sysDictType.getCode()) != null)
            throw new ME("该编号字典类型已存在");
        return sysDictTypeDao.save(sysDictType);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void edit(@RequestBody SysDictTypeEntity sysDictType) {
        SysDictTypeEntity tmp = sysDictTypeDao.findOne(sysDictType.getId());
        if (!tmp.getName().equals(sysDictType.getName())) {
            if (sysDictTypeDao.findByName(sysDictType.getName()) != null)
                throw new ME("该名称字典类型已存在");
        }
        if (!tmp.getCode().equals(sysDictType.getCode())) {
            if (sysDictTypeDao.findByCode(sysDictType.getCode()) != null)
                throw new ME("该编号字典类型已存在");
        }
        sysDictTypeDao.save(sysDictType);
        if (!tmp.getCode().equals(sysDictType.getCode())) {
            sysDictDao.updateDictType(sysDictType.getCode(), tmp.getCode());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @Transactional
    public void destory(@PathVariable Integer id) {
        SysDictTypeEntity type = sysDictTypeDao.findOne(id);
        sysDictDao.deleteByTypeCode(type.getCode());
        sysDictTypeDao.delete(id);
    }
}
