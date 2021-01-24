package com.kgisl.responsive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kgisl.responsive.model.UserProfileInfo;
import com.kgisl.responsive.service.UserService;

@RestController
public class ResponsiveRestController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = { "/api/profile" })
	public ResponseEntity<Boolean> saveProfileInfo(@RequestBody UserProfileInfo userProfileInfo){
		
		userService.saveUserService(userProfileInfo);
		
		return ResponseEntity.status(HttpStatus.OK).body(true);
	}
}
