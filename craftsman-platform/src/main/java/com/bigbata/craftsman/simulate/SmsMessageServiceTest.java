/**
 * 
 */
package com.bigbata.craftsman.simulate;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bigbata.craftsman.dao.model.SmsMessage;
import com.bigbata.craftsman.service.SmsMessageService;

/**
 * 类说明:<br>
 * 创建时间: 2014年12月31日 下午8:40:24<br>
 * 
 * @author 刘岩松<br>
 * @email yansong.lau@gmail.com<br>
 */
public class SmsMessageServiceTest {

	private static ApplicationContext context;

	/**
	 * 功能说明:<br>
	 * 创建者: 刘岩松<br>
	 * 创建时间: 2014年12月31日 下午8:48:20<br>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		context = new AnnotationConfigApplicationContext("com.bigbata.craftsman.config");
		SmsMessageService smsMessageService = context.getBean(SmsMessageService.class);
		List<SmsMessage> smsMessages = smsMessageService.fetchAllSmsMessages();
		System.out.println(smsMessages.size());
	}

}
