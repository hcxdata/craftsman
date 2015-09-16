package com.bigbata.craftsman.config.security;

import com.bigbata.craftsman.config.Constants;
import com.bigbata.craftsman.dao.model.SysMenusEntity;
import com.bigbata.craftsman.dao.model.SysRoleEntity;
import com.bigbata.craftsman.dao.model.SysRoleMenuEntity;
import com.bigbata.craftsman.dao.system.SysMenuDao;
import com.bigbata.craftsman.dao.system.SysRoleDao;
import com.bigbata.craftsman.dao.system.SysRoleMenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 通过数据库获取角色和url之间的绑定关系，用于权限验证
 * Created by lixianghui on 15/9/9.
 */
@Component
public class DataBaseSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object filter) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) filter;
        String requestURI = filterInvocation.getRequestUrl();
        String url = requestURI.toString();
        Integer menuRes = null;
        Iterable<SysMenusEntity> menus = sysMenuDao.findAll();
        List<ConfigAttribute> cas = new ArrayList<ConfigAttribute>();

        for (SysMenusEntity menu : menus) {
            if (!StringUtils.isEmpty(menu.getHrefTarget()) && url.indexOf(menu.getHrefTarget()) != -1) {
                menuRes = menu.getId();
                break;
            }
        }
        if (menuRes != null) {
            List<SysRoleMenuEntity> sysRoles = sysRoleMenuDao.findByMenusid(menuRes);
            for (SysRoleMenuEntity sysRole : sysRoles) {
                cas.add(new org.springframework.security.access.SecurityConfig(Constants.ROLE_NAME_PREFIX + sysRole.getRoleid()));
            }
            return cas;
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Iterable<SysRoleEntity> roles = sysRoleDao.findAll();
        List<ConfigAttribute> res = new ArrayList<ConfigAttribute>();
        for (SysRoleEntity role : roles) {
            ConfigAttribute ca = new org.springframework.security.access.SecurityConfig(Constants.ROLE_NAME_PREFIX + role.getId());
        }
        return res;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        if (clazz.isAssignableFrom(FilterInvocation.class))
            return true;
        else
            return false;
    }
}
