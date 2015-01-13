/**
 * 
 */
package com.bigbata.craftsman.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bigbata.craftsman.dao.model.SmsMessage;
import com.bigbata.craftsman.service.SmsMessageService;

/** 
 * 类说明:<br> 
 * 创建时间: 2014年12月31日 下午8:24:42<br> 
 * @author 刘岩松<br> 
 * @email yansong.lau@gmail.com<br>  
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SmsMessageService.class)
public class SmsMessageServiceTest {
	
	@Autowired
	private SmsMessageService smsMessageService;
	
	@Test
	public void printAllSmsMessage() {
		List<SmsMessage> smsMessages = smsMessageService.fetchAllSmsMessages();
		Assert.assertEquals(3, smsMessages.size(), 0);
	}

}
