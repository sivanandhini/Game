package com.kgisl.socket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SocketController {
	
	@GetMapping("/")
	public String userChat() {
		
		return "user";
	}
	
}
