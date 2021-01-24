package com.kgisl.responsive.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.kgisl.responsive.model.UserProfileInfo;
import com.kgisl.responsive.service.UserProfileService;

@RestController
public class ResponsiveAPIController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserProfileService userProfileService;

	@PostMapping(value = { "/api/profile" })
	public ResponseEntity<Boolean> saveProfileInfo(@RequestPart("resume") MultipartFile resume, @RequestPart("profilePic") MultipartFile profilePic,
			@RequestPart("user") UserProfileInfo userProfileInfo) throws IOException{
		
		String resumeId = userProfileService.saveUserFile(resume);
		String profilePicId = userProfileService.saveUserFile(profilePic);
		
		userProfileInfo.setResumeId(resumeId);
		userProfileInfo.setProfilePicId(profilePicId);
		
		
		Boolean response = restTemplate.postForObject("http://Responsive-API-Service/api/profile", userProfileInfo, Boolean.class);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping(value = { "/api/feedback" })
	public ResponseEntity<Boolean> saveProfileInfo(@RequestBody String feedback) throws IOException{
		
		userProfileService.saveFeedback(feedback);
		
		return ResponseEntity.status(HttpStatus.OK).body(true);
	}
}
