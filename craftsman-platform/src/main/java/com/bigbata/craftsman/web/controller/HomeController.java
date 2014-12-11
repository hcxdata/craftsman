/**
 * 
 */
package com.bigbata.craftsman.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * 类说明:<br> 
 * 创建时间: 2014年12月11日 上午11:19:22<br> 
 * @author 刘岩松<br> 
 * @email yansong.lau@gmail.com<br>  
 */
@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
}
