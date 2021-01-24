package com.kgisl.multi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kgisl.multi.config.TenantUser;
import com.kgisl.multi.model.TenantUserDetails;
import com.kgisl.multi.service.WebPunchDetailsService;

@RestController
public class WebPunchController {
	
	@Autowired
	private WebPunchDetailsService webPunchDetailsService;
	
	@GetMapping(value = { "/api/punch" })
	public ResponseEntity<Boolean> saveWebPunchById(@TenantUser TenantUserDetails tenantUserDetails) {
		
		webPunchDetailsService.saveWebPunchById(tenantUserDetails);

		return ResponseEntity.status(HttpStatus.OK).body(true);
	}
	
}
