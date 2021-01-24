package com.kgisl.socket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SocketController {
	
	private Integer userId = 1;
	
	@GetMapping("/")
	public String userChat(ModelMap model) {
		
		userId = userId + 1;
		model.addAttribute("userId", userId);
		
		return "user";
	}
	
	@GetMapping("/admin")
	public String adminChat() {
		
		return "admin";
	}
}
