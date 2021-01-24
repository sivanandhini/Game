package com.kgisl.multi.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kgisl.multi.dao.DefaultDataSourceDao;

@Transactional("defaultTransactionManager")
@Service
public class DefaultDataSourceService {
	
	@Autowired
	private DefaultDataSourceDao defaultDataSourceDao;

	public List<String> getAllClients() {

		return defaultDataSourceDao.getAllClients();
	}

}
