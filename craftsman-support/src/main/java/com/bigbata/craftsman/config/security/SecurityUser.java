package com.bigbata.craftsman.config.security;

import java.util.ArrayList;
import java.util.Collection;

import com.bigbata.craftsman.dao.model.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 自定义用户类，用于提供系统进行安全验证的用户信息
 */

public class SecurityUser extends SysUser implements UserDetails {

    private static final long serialVersionUID = -6993005649423382897L;
    private Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    public SecurityUser(SysUser user) {
        BeanUtils.copyProperties(user, this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void addAuthority(String roleId) {
        authorities.add(new SimpleGrantedAuthority("ROLE_" + roleId));
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
