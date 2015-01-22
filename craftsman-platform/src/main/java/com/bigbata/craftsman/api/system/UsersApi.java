package com.bigbata.craftsman.api.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<User> index() {
		return userDao.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User show(@PathVariable Long id) {
		return userDao.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public User create(@RequestBody User user) {
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
