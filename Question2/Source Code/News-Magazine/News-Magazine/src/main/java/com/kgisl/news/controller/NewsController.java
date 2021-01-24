package com.kgisl.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {
	
	@GetMapping("/")
	public String viewHome()  {
		
		return "magazine";
	}

}
