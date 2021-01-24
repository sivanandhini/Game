package com.kgisl.di.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DIController {

	@GetMapping("/")
	public String userChat() {
		
		return "dependency-injection";
	}
	
}
