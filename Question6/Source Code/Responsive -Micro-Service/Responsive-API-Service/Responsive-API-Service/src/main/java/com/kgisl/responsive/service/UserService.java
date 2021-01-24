package com.kgisl.responsive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.kgisl.responsive.model.UserProfileInfo;

@Service
public class UserService {

	@Autowired
	MongoOperations mongoOperations;

	public void saveUserService(UserProfileInfo userProfileInfo) {
		
		mongoOperations.save(userProfileInfo);
	}
	
}
