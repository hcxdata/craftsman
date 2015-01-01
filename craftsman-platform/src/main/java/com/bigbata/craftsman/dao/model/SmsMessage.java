/**
 * 
 */
package com.bigbata.craftsman.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 类说明:<br>
 * 创建时间: 2014年12月31日 下午6:59:57<br>
 * 
 * @author 刘岩松<br>
 * @email yansong.lau@gmail.com<br>
 */
@Entity
@Table(name = "SMS_MESSAGE")
public class SmsMessage {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;

	@Column(name = "SMS_CONTENT", nullable = false)
	private String smsContent;

	@Column(name = "REMOTE_NUMBER", nullable = false)
	private String remoteNumber;

	@Column(name = "SMS_TIME")
	private String smsTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getRemoteNumber() {
		return remoteNumber;
	}

	public void setRemoteNumber(String remoteNumber) {
		this.remoteNumber = remoteNumber;
	}

	public String getSmsTime() {
		return smsTime;
	}

	public void setSmsTime(String smsTime) {
		this.smsTime = smsTime;
	}

	@Override
	public String toString() {
		return "SmsMessage [id=" + id + ", smsContent=" + smsContent
				+ ", remoteNumber=" + remoteNumber + ", smsTime=" + smsTime
				+ "]";
	}

}
