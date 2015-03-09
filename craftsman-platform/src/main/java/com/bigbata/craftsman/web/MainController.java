package com.bigbata.craftsman.web;

import com.bigbata.craftsman.page.PageWarp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping({ "/web" })
public class MainController {

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}


	@RequestMapping(value="/**",method = RequestMethod.GET)
	public String main(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		String rootPath = request.getContextPath();
		String path = request.getPathInfo();
		PageWarp pageWarp = new PageWarp(request);
		model.put("pageWarp",pageWarp);
		return "main";
	}
}
