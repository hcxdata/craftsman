package com.bigbata.craftsman.api.system;

import java.util.ArrayList;
import java.util.List;

import com.bigbata.craftsman.exception.ME;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bigbata.craftsman.dao.model.system.User;
import com.bigbata.craftsman.dao.system.UserDao;

@RestController
@RequestMapping({ "/api/system/users" })
public class UsersApi {
	public static List<User> users = new ArrayList<User>();

	@Autowired
	private UserDao userDao;

	@RequestMapping(method = RequestMethod.GET)
	public Page<User> index(@PageableDefault Pageable pageable) {
		Page<User> users = userDao.findAll(pageable);
		return users;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User show(@PathVariable Long id) {
		return userDao.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public User create(@RequestBody User user) {
		if(userDao.findUserByName(user.getName()) != null)
			throw new ME("该用户已存在");
		return userDao.save(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void edit(@RequestBody User user) {
		userDao.save(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void destory(@PathVariable Long id) {
		userDao.delete(id);
	}

}
