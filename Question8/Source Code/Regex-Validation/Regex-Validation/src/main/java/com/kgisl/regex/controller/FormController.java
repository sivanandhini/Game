package com.kgisl.regex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormController {
	
	@GetMapping({"/"})
	public String getHome(Model model) throws Exception {
		
		return "home";
	}
	
}
