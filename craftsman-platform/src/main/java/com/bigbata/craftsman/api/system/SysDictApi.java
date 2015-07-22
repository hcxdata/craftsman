package com.bigbata.craftsman.api.system;

import com.bigbata.craftsman.dao.model.SysDictEntity;
import com.bigbata.craftsman.dao.system.SysDictDao;
import com.bigbata.craftsman.exception.ME;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lixianghui on 15-4-3.
 */
@RestController
@RequestMapping({"/api/system/dicts"})
public class SysDictApi {
    @Autowired
    private SysDictDao sysDictDao;

    @RequestMapping(method = RequestMethod.GET)
    public Page<SysDictEntity> index(@PageableDefault Pageable pageable, String typeCode, String name, String code) {
        if (typeCode == null) typeCode = "null";
        if (name == null) name = "%%";
        else name = "%" + name + "%";
        if (code == null) code = "%%";
        else code = "%" + code + "%";
        return sysDictDao.findByTypeCodeAndNameLikeAndCodeLikeOrderByOrdersAsc(pageable, typeCode, name, code);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SysDictEntity show(@PathVariable Integer id) {
        return sysDictDao.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public SysDictEntity create(@RequestBody SysDictEntity sysDictType) {
        if (sysDictDao.findByTypeCodeAndCode(sysDictType.getTypeCode(), sysDictType.getCode()) != null)
            throw new ME("该编号字典已存在");
        if (sysDictDao.findByTypeCodeAndName(sysDictType.getTypeCode(), sysDictType.getName()) != null)
            throw new ME("该名称字典已存在");
        int count = sysDictDao.getCountByTypeCode(sysDictType.getTypeCode());
        sysDictType.setOrders(count + 1);
        return sysDictDao.save(sysDictType);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void edit(@RequestBody SysDictEntity sysDictType) {
        SysDictEntity tmp = sysDictDao.findOne(sysDictType.getId());
        if (!tmp.getCode().equals(sysDictType.getCode())) {
            if (sysDictDao.findByTypeCodeAndCode(sysDictType.getTypeCode(), sysDictType.getCode()) != null)
                throw new ME("该编号字典已存在");
        }
        if (!tmp.getName().equals(sysDictType.getName())) {
            if (sysDictDao.findByTypeCodeAndName(sysDictType.getTypeCode(), sysDictType.getName()) != null)
                throw new ME("该名称字典已存在");
        }
        sysDictDao.save(sysDictType);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void destory(@PathVariable Integer id) {
        SysDictEntity tmp = sysDictDao.findOne(id);
        sysDictDao.delete(id);
        sysDictDao.upOrderFrom(tmp.getTypeCode(), tmp.getOrders());
    }

    @RequestMapping(value = "/{id}", params = {"action=order", "direction=up"}, method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void editUp(@PathVariable Integer id) throws Exception {
        SysDictEntity dict = sysDictDao.findOne(id);
        upOrder(dict);
    }

    @RequestMapping(value = "/{id}", params = {"action=order", "direction=down"}, method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void down(@PathVariable Integer id) {
        SysDictEntity dict = sysDictDao.findOne(id);
        downOrder(dict);
    }

    private void upOrder(SysDictEntity dict) throws Exception {
        //已到最上不用变更
        if (dict.getOrders() == 1) return;
        //更改向上排序内容
        sysDictDao.updateOrderFromTypeCodeAndOrder(dict.getTypeCode(), dict.getOrders(), dict.getOrders() - 1);
        //向上排序
        dict.setOrders(dict.getOrders() - 1);
        //后更改本次字典的排序
        sysDictDao.save(dict);
    }

    private void downOrder(SysDictEntity dict) {
        //已到最下不用变更
        if (dict.getOrders().intValue() == sysDictDao.getCountByTypeCode(dict.getTypeCode())) return;
        //更改向下排序内容
        sysDictDao.updateOrderFromTypeCodeAndOrder(dict.getTypeCode(), dict.getOrders(), dict.getOrders() + 1);
        //向下排序
        dict.setOrders(dict.getOrders() + 1);
        //后更改本次字典的排序
        sysDictDao.save(dict);
    }

}
