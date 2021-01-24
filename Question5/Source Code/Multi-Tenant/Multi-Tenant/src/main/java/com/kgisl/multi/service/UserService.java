package com.kgisl.multi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kgisl.multi.dao.UserDao;
import com.kgisl.multi.model.User;

@Transactional("tenantTransactionManager")
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public List<User> getUserByName(String username) {

		return userDao.getUserByName(username);
	}

}
