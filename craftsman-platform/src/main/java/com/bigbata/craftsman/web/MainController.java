package com.bigbata.craftsman.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({ "/web" })
public class MainController {

	@RequestMapping(method = RequestMethod.GET)
	public String v() {
		return "main";
	}
}
