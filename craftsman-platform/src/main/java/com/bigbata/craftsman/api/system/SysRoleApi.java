package com.bigbata.craftsman.api.system;

import com.bigbata.craftsman.dao.model.*;
import com.bigbata.craftsman.dao.system.SysMenuDao;
import com.bigbata.craftsman.dao.system.SysRoleDao;
import com.bigbata.craftsman.dao.system.SysRoleMenuDao;
import com.bigbata.craftsman.exception.ME;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by lixianghui on 15-5-11.
 */
@RestController
@RequestMapping({"/api/system/roles"})
public class SysRoleApi {
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @RequestMapping(method = RequestMethod.GET)
    public Page<SysRoleEntity> index(@PageableDefault Pageable page, String name) {
        if (name == null)
            name = "%";
        else
            name = "%" + name + "%";
        return sysRoleDao.findByNameLike(page, name);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SysRoleEntity show(@PathVariable Integer id) {
        return sysRoleDao.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public SysRoleEntity create(@RequestBody SysRoleEntity role) {
        if (sysRoleDao.findByName(role.getName()) != null) {
            throw new ME("角色已存在");
        }
        return sysRoleDao.save(role);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void edit(@RequestBody SysRoleEntity role) {
        SysRoleEntity source = sysRoleDao.findOne(role.getId());
        if (!source.getName().equals(role.getName())) {
            if (sysRoleDao.findByName(role.getName()) != null) {
                throw new ME("角色已存在");
            }
        }
        sysRoleDao.save(role);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void destory(@PathVariable Integer id) {
        sysRoleDao.delete(id);
    }

    @RequestMapping(value = "/{roleId}/menus", method = RequestMethod.GET)
    public List<SysMenusCheck> showMenus(@PathVariable Integer roleId) {
        //所有菜单
        Iterable<SysMenusEntity> menus = sysMenuDao.findAll();
        //角色菜单
        List<SysRoleMenuEntity> roleMenus = sysRoleMenuDao.findByRoleid(roleId);
        //转换为checkMenus
        List<SysMenusCheck> checkList = getCheckList(menus, roleMenus);
        //转化为层叠结构
        return getNestCheckList(checkList);
    }


    @RequestMapping(value = "/{roleId}/menus", method = RequestMethod.PUT)
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public void updateMenus(@RequestBody RoleMenusParam param) {
        sysRoleMenuDao.deleteByRoleId(param.getRoleid());
        for (Integer menu : param.getMenus()) {
            SysRoleMenuEntity roleMenu = new SysRoleMenuEntity();
            roleMenu.setRoleid(param.getRoleid());
            roleMenu.setMenusid(menu);
            sysRoleMenuDao.save(roleMenu);
        }
    }

    public static class RoleMenusParam {
        private Integer roleid;

        public Integer getRoleid() {
            return roleid;
        }

        public void setRoleid(Integer roleid) {
            this.roleid = roleid;
        }

        public List<Integer> getMenus() {
            return menus;
        }

        public void setMenus(List<Integer> menus) {
            this.menus = menus;
        }

        private List<Integer> menus;


    }

    private List<SysMenusCheck> getCheckList(Iterable<SysMenusEntity> menus, List<SysRoleMenuEntity> roleMenus) {

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

    private List<SysMenusCheck> getNestCheckList(List<SysMenusCheck> checkList) {
        List<SysMenusCheck> results = new ArrayList<SysMenusCheck>();
        Map<Integer, SysMenusCheck> map = new HashMap<Integer, SysMenusCheck>();
        for (SysMenusCheck check : checkList) {
            if (check.getParentId() == 0) {
                results.add(check);
            }
            map.put(check.getId(), check);
        }

        for (SysMenusCheck check : checkList) {
            if (map.get(check.getParentId()) != null) {
                SysMenusCheck parent = map.get(check.getParentId());
                parent.getChildren().add(check);
            }
        }

        return results;
    }


}
