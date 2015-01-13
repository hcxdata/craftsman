package com.bigbata.craftsman.api.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bigbata.craftsman.dao.domain.system.User;

@RestController
@RequestMapping({ "/system/users" })
public class UsersApi {
	public static List<User> users = new ArrayList<User>();

	@RequestMapping(method = RequestMethod.GET)
	public List<User> index() {
		return users;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User show(@PathVariable Integer id) {
		for (User user : users) {
			if (user.getId() == id)
				return user;
		}
		return users.get(0);
	}

	@RequestMapping(method = RequestMethod.POST)
	public User create(@RequestBody User user) {
		user.setId(users.size());
		users.add(user);
		return user;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void edit(@RequestBody User user) {
		for (User tmp : users) {
			if (tmp.getId().equals(user.getId())) {
				BeanUtils.copyProperties(user, tmp);
			}
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void destory(@PathVariable Integer id) {
		User user = null;
		for (User tmp : users) {
			if (tmp.getId() == id) {
				user = tmp;
			}
		}
		users.remove(user);
	}

}
