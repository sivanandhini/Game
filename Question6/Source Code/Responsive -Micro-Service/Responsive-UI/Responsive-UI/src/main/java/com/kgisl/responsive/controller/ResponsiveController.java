package com.kgisl.responsive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResponsiveController {
	
	@GetMapping("/")
	public String home() {
		
		return "home";
	}

}
