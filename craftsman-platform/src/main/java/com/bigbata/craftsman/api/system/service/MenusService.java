package com.bigbata.craftsman.api.system.service;

import com.bigbata.craftsman.dao.model.SysMenusCheck;
import com.bigbata.craftsman.dao.model.SysMenusEntity;
import com.bigbata.craftsman.dao.model.SysRoleMenuEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by lixianghui on 15-5-19.
 */
@Component
public class MenusService {

    public List<SysMenusCheck> getCheckList(Iterable<SysMenusEntity> menus, List<SysRoleMenuEntity> roleMenus) {

        //将角色菜单标记为check
        List<SysMenusCheck> checkList = new ArrayList<>();

        for (SysMenusEntity menu : menus) {
            SysMenusCheck check = new SysMenusCheck();
            BeanUtils.copyProperties(menu, check);
            checkList.add(check);

            for (SysRoleMenuEntity roleMenu : roleMenus) {
                if (menu.getId() == roleMenu.getMenusid()) {
                    check.setChecked(true);
                }
            }
        }
        return checkList;
    }

    public <T extends SysMenusEntity> List<T> getNestList(List<T> list) {
        List<T> results = new ArrayList<T>();
        Map<Integer, SysMenusEntity> map = new HashMap<Integer, SysMenusEntity>();
        for (T menu : list) {
            if (menu.getParentId() == 0) {
                results.add(menu);
            }
            map.put(menu.getId(), menu);
        }

        for (SysMenusEntity menu : list) {
            if (map.get(menu.getParentId()) != null) {
                SysMenusEntity parent = map.get(menu.getParentId());
                parent.getChildren().add(menu);
            }
        }
        //结构化排序
        orderNestMenus(list);
        return results;
    }

    public <T extends SysMenusEntity> List<T> orderMenus(List<T> menus) {
        Collections.sort(menus, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.getOrders() - o2.getOrders();
            }
        });
        return menus;
    }

    public <T extends SysMenusEntity> List<T> orderNestMenus(List<T> menus) {
        orderMenus(menus);
        for (SysMenusEntity menu : menus) {
            if (menu.getChildren() != null && menu.getChildren().size() > 0)
                orderNestMenus(menu.getChildren());
        }
        return menus;
    }
}
