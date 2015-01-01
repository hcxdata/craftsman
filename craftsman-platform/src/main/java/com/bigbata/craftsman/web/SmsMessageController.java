/**
 * 
 */
package com.bigbata.craftsman.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bigbata.craftsman.service.SmsMessageService;

/**
 * 类说明:<br>
 * 创建时间: 2015年1月1日 下午8:56:28<br>
 * 
 * @author 刘岩松<br>
 * @email yansong.lau@gmail.com<br>
 */
@Controller
@RequestMapping({ "/", "/smsMessages" })
public class SmsMessageController {

	private SmsMessageService smsMessageService;

	@Autowired
	public void setSmsMessageService(SmsMessageService smsMessageService) {
		this.smsMessageService = smsMessageService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String smsMessages(Model model) {
		model.addAttribute("smsMessageList",
				smsMessageService.fetchAllSmsMessages());
		return "smsMessages";
	}

}
