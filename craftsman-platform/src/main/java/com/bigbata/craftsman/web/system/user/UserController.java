package com.bigbata.craftsman.web.system.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({ "/web/system/users" })
public class UserController {
	@RequestMapping(method = RequestMethod.GET)
	public String v() {
		return "main";
	}
}
