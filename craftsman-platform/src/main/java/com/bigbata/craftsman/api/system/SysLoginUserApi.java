package com.bigbata.craftsman.api.system;

import com.bigbata.craftsman.api.system.service.MenusService;
import com.bigbata.craftsman.dao.model.SysMenusEntity;
import com.bigbata.craftsman.dao.model.SysRoleEntity;
import com.bigbata.craftsman.dao.model.SysUser;
import com.bigbata.craftsman.dao.system.SysMenuDao;
import com.bigbata.craftsman.dao.system.SysRoleDao;
import com.bigbata.craftsman.dao.system.SysRoleMenuDao;
import com.bigbata.craftsman.dao.system.SysUserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lixianghui on 15-5-19.
 */
@RestController
@RequestMapping({"/api/system/loginUser"})
public class SysLoginUserApi {
    @Autowired
    SysMenuDao sysMenuDao;
    @Autowired
    MenusService menusService;

    private SysUser getLoginUser() {
        return (SysUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

    }

    @RequestMapping(method = RequestMethod.GET)
    public SysUser index() {
        SysUser user = getLoginUser();
        user.setPassword("");
        return user;
    }

    @RequestMapping(value = "/menus", method = RequestMethod.GET)
    public List<SysMenusEntity> menus() {
        SysUser user = getLoginUser();
        List<SysMenusEntity> menus = sysMenuDao.findByUserId(user.getId());
        List<SysMenusEntity> result = menusService.getNestList(menus);
        return result;
    }
}
