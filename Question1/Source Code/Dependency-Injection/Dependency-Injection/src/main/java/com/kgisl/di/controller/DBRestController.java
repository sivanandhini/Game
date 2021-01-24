package com.kgisl.di.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kgisl.di.modal.User;
import com.kgisl.di.service.UserService;

@RestController
public class DBRestController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/user")
	public List<User> getAllUsers(){
		
		return userService.getAllUsers(); 
	}
	
}
