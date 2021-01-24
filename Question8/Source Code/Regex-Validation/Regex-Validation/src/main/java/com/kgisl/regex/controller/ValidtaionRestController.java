package com.kgisl.regex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kgisl.regex.resource.Properties;
import com.kgisl.regex.service.PropertiesService;

@RestController
public class ValidtaionRestController {
	
	@Autowired
	private PropertiesService propertiesService;
	
	@PostMapping(value = { "/api/validate" })
	public ResponseEntity<Boolean> validateProperty(@RequestBody Properties property) {
		
		Boolean validationStatus = false;
		
		validationStatus = propertiesService.validateProperty(property);
		
		return ResponseEntity.status(HttpStatus.OK).body(validationStatus);
	}
	
}
