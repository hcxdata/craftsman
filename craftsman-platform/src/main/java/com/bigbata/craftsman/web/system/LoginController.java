package com.bigbata.craftsman.web.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({ "/login" })
public class LoginController {
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form() {
		return "login/form";
	}
}
