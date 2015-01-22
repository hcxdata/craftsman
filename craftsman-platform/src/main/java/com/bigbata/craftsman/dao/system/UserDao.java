package com.bigbata.craftsman.dao.system;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bigbata.craftsman.dao.model.system.User;

public interface UserDao extends JpaRepository<User, Long> {
	
	public User findUserByName(String name);
}
