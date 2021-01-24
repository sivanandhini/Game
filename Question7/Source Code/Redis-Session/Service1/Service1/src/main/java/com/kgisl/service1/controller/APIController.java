package com.kgisl.service1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

	@GetMapping(value = { "/test/status" })
	public String getStatus(){
		
		return "From service1";
	}
}
