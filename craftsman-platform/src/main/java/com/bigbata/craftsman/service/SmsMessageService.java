/**
 * 
 */
package com.bigbata.craftsman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bigbata.craftsman.dao.SmsMessageDao;
import com.bigbata.craftsman.dao.model.SmsMessage;

/** 
 * 类说明:<br> 
 * 创建时间: 2014年12月31日 下午8:09:14<br> 
 * @author 刘岩松<br> 
 * @email yansong.lau@gmail.com<br>  
 */
@Component
public class SmsMessageService {
	
	private SmsMessageDao smsMessageDao;

	public SmsMessageDao getSmsMessageDao() {
		return smsMessageDao;
	}
	
	@Autowired
	public void setSmsMessageDao(SmsMessageDao smsMessageDao) {
		this.smsMessageDao = smsMessageDao;
	}
	
	public void addSmsMessage(SmsMessage smsMessage) {
		getSmsMessageDao().insert(smsMessage);
	}
	
	public List<SmsMessage> fetchAllSmsMessages() {
		return getSmsMessageDao().selectAll();
	}

}
