package com.bigbata.craftsman.config.security;

import com.bigbata.craftsman.dao.model.system.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bigbata.craftsman.dao.system.SysUserDao;
import org.springframework.stereotype.Component;

@Component
public class SecurityService implements UserDetailsService {

	@Autowired
	private SysUserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		SysUser user = userDao.findUserByName(username);
		return new SecurityUser(user);
	}

}
