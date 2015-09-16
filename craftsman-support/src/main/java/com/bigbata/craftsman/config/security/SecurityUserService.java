package com.bigbata.craftsman.config.security;

import com.bigbata.craftsman.dao.model.SysUser;
import com.bigbata.craftsman.dao.model.SysUserRoleEntity;
import com.bigbata.craftsman.dao.system.SysUserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bigbata.craftsman.dao.system.SysUserDao;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 覆盖security系统的用户资源提供方法,提供从数据库获取用户的能力
 */
@Component
public class SecurityUserService implements UserDetailsService {

    @Autowired
    private SysUserDao userDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        SysUser user = userDao.findUserByName(username);
        List<SysUserRoleEntity> userRoles = sysUserRoleDao.findByUserid(user.getId());
        SecurityUser secUser = new SecurityUser(user);
        for (SysUserRoleEntity userRole : userRoles) {
            secUser.addAuthority(userRole.getRoleid() + "");
        }
        return secUser;
    }

}
