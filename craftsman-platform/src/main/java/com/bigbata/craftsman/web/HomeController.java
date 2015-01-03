/**
 * 
 */
package com.bigbata.craftsman.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 类说明:<br>
 * 创建时间: 2014年12月11日 上午11:19:22<br>
 * 
 * @author 刘岩松<br>
 * @email yansong.lau@gmail.com<br>
 */
@Controller
@RequestMapping({ "/", "/homepage" })
public class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	public String home() {
		return "home";
	}

}
