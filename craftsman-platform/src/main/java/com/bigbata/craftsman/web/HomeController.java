/**
 * 
 */
package com.bigbata.craftsman.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bigbata.craftsman.dao.model.SmsMessage;
import com.bigbata.craftsman.service.SmsMessageService;

/** 
 * 类说明:<br> 
 * 创建时间: 2014年12月11日 上午11:19:22<br> 
 * @author 刘岩松<br> 
 * @email yansong.lau@gmail.com<br>  
 */
@Controller
@RequestMapping({"/", "/homepage"})
public class HomeController {

	private SmsMessageService smsMessageService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String home() {
		
		List<SmsMessage> smsMessages = smsMessageService.fetchAllSmsMessages();
		
		
		
		System.out.println(smsMessages.size());
		return "home";
	}

	@Autowired
	public void setSmsMessageService(SmsMessageService smsMessageService) {
		this.smsMessageService = smsMessageService;
	}

}
