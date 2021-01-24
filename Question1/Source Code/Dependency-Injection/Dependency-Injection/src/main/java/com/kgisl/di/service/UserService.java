package com.kgisl.di.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgisl.di.dao.UserDao;
import com.kgisl.di.modal.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;

	public List<User> getAllUsers() {

		return userDao.getAllUsers();
	}
	
}
