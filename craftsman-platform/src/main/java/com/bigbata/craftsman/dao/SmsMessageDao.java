/**
 * 
 */
package com.bigbata.craftsman.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bigbata.craftsman.dao.model.SmsMessage;

/** 
 * 类说明:<br> 
 * 创建时间: 2014年12月31日 下午7:09:00<br> 
 * @author 刘岩松<br> 
 * @email yansong.lau@gmail.com<br>  
 */
@Repository("smsMessageDao")
@Transactional(propagation = Propagation.REQUIRED)
public class SmsMessageDao {

	private static final String SELECT_QUERY = "from SmsMessage";
	
	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void insert(SmsMessage smsMessage) {
		entityManager.persist(smsMessage);
	}
	
	@SuppressWarnings("unchecked")
	public List<SmsMessage> selectAll() {
		Query query = entityManager.createQuery(SELECT_QUERY);
		List<SmsMessage> smsMessages = (List<SmsMessage>) query.getResultList();
		return smsMessages;
	}
	
}
