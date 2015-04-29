package com.bigbata.craftsman.api.system;

import com.bigbata.craftsman.dao.model.SysMenusEntity;
import com.bigbata.craftsman.dao.system.SysMenuDao;
import com.bigbata.craftsman.exception.ME;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/api/system/menus"})
public class SysMenusApi {
    public static List<SysMenusEntity> menus = new ArrayList<>();

    @Autowired
    private SysMenuDao menuDao;

    @RequestMapping(value = "{id}/children", method = RequestMethod.GET)
    public List<SysMenusEntity> index(@PathVariable Integer id) {
        List<SysMenusEntity> menus = menuDao.findByParentIdOrderByOrdersAsc(id);
        return menus;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SysMenusEntity show(@PathVariable Integer id) {
        return menuDao.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public SysMenusEntity create(@RequestBody SysMenusEntity menu) {
        Integer parent = menu.getParentId();
        menu.setOrders(menuDao.getCountByParentId(parent) + 1);
        return menuDao.save(menu);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void edit(@RequestBody SysMenusEntity menu) {
        menuDao.save(menu);
    }

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void destory(@PathVariable Integer id) {
        SysMenusEntity menu = menuDao.findOne(id);
        //删除所有子节点
        delWithAllChildren(id);
        //更新其他节点的排序
        menuDao.upOrderFrom(menu.getParentId(), menu.getOrders());
    }

    //递归删除所有节点
    private void delWithAllChildren(Integer id) {
        List<SysMenusEntity> menus = menuDao.findByParentIdOrderByOrdersAsc(id);
        if (menus.size() > 0) {
            for (SysMenusEntity menu : menus) {
                delWithAllChildren(menu.getId());
            }
        }
        menuDao.delete(id);

    }


    @Transactional
    @RequestMapping(value = "/{id}", params = {"action=order", "direction=up"}, method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void up(@PathVariable Integer id) {
        SysMenusEntity menu = menuDao.findOne(id);
        upOrder(menu);
    }

    @Transactional
    @RequestMapping(value = "/{id}", params = {"action=order", "direction=down"}, method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void down(@PathVariable Integer id) {
        SysMenusEntity menu = menuDao.findOne(id);
        downOrder(menu);
    }

    private void upOrder(SysMenusEntity menu) {
        //已到最上不用变更
        if (menu.getOrders() == 1) return;
        //更改向上排序内容
        menuDao.updateOrderFromParentIdAndOrder(menu.getParentId(), menu.getOrders(), menu.getOrders() - 1);
        //向上排序
        menu.setOrders(menu.getOrders() - 1);
        //保存排序
        menuDao.save(menu);
    }

    private void downOrder(SysMenusEntity menu) {
        //已到最下不用变更
        if (menu.getOrders().intValue() == menuDao.getCountByParentId(menu.getParentId())) return;
        //更改向下排序内容
        menuDao.updateOrderFromParentIdAndOrder(menu.getParentId(), menu.getOrders(), menu.getOrders() + 1);
        //向下排序
        menu.setOrders(menu.getOrders() + 1);
        //保存排序
        menuDao.save(menu);
    }

    @Transactional
    @RequestMapping(value = "/{id}", params = {"action=changeParent"}, method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void changeParent(@RequestBody SysMenusEntity menu) {
        if (childHaveParent(menu.getId(), menu.getParentId())) {
            throw new ME("导致循环引用，禁止操作！！");
        } else {
            SysMenusEntity menusEntity = menuDao.findOne(menu.getId());
            menuDao.upParentId(menu.getId(), menu.getParentId(), menuDao.getCountByParentId(menu.getParentId()) + 1);
            //更新其他节点的排序
            menuDao.upOrderFrom(menusEntity.getParentId(), menusEntity.getOrders());
        }
    }

    //判定节点是否循环引用
    private boolean childHaveParent(Integer id, Integer parentId) {
        List<SysMenusEntity> menus = menuDao.findByParentIdOrderByOrdersAsc(id);
        if (menus.size() > 0) {
            for (SysMenusEntity menu : menus) {
                if (menu.getId() == parentId)
                    return true;
                if (childHaveParent(menu.getId(), parentId))
                    return true;
            }
        }
        return false;
    }
}
