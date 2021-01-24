package com.kgisl.multi.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultDataSourceDao {

	@Autowired
	@Qualifier("defaultSessionFactory")
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<String> getAllClients() {

		return sessionFactory.getCurrentSession().createSQLQuery("SELECT c.clt_ds_tenant_id FROM clt_data_sources c WHERE c.clt_ds_active = 'Y'").list();
	}
	
}
