package com.kgisl.service2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServiceRestController {

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping(value = { "/test/status" })
	public String getStatus(){

        String response = restTemplate.getForObject("http://Service1/test/status", String.class);
		
		return response + " From service2";
	}
	
	
}
