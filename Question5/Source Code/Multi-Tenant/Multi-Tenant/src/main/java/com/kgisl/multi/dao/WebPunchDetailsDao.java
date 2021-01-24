package com.kgisl.multi.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.kgisl.multi.model.WebPunchDetails;

@Repository
public class WebPunchDetailsDao {
	
	@Autowired
	@Qualifier("tenantSessionFactory")
	private SessionFactory sessionFactory;

	public void savesaveWebPunch(WebPunchDetails webPunchDetails) {

		sessionFactory.getCurrentSession().save(webPunchDetails);
	}

}
