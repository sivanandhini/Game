package com.kgisl.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kgisl.multi.service.DefaultDataSourceService;

@Controller
public class FormController {
	
	@Autowired
	private DefaultDataSourceService defaultDataSourceService;
	
	/**
	 * URL to render login page.
	 */
	@GetMapping({"/", "/login"})
	public String getLogin(Model model) throws Exception {
		
		List<String> clients = defaultDataSourceService.getAllClients();
		
		model.addAttribute("Clients", clients);
		
		return "login";
	}
	
	/**
	 * To render home after login success.
	 * @return home view page.
	 */
	@GetMapping("/home")
	public String getHome() {
		
		return "home";
	}
}
