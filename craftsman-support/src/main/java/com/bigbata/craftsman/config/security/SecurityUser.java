package com.bigbata.craftsman.config.security;

import java.util.ArrayList;
import java.util.Collection;

import com.bigbata.craftsman.dao.model.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class SecurityUser extends SysUser implements UserDetails {

	private static final long serialVersionUID = -6993005649423382897L;

	public SecurityUser(SysUser user) {
		BeanUtils.copyProperties(user, this);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>() {
			{
				add(new SimpleGrantedAuthority("ADMIN"));
			}
		};
		return authorities;
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
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
