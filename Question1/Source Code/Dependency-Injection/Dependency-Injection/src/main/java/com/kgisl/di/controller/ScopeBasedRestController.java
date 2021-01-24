package com.kgisl.di.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kgisl.di.service.PrototypeScopeService;
import com.kgisl.di.service.SingletonScopeService;

@RestController
public class ScopeBasedRestController {

	@Autowired
	private PrototypeScopeService prototypeScopeService;
	
	@Autowired
	private SingletonScopeService singletonScopeService;
	
	@GetMapping("/sb/prototype/value")
	public Integer getProtoValue(){
		
		return prototypeScopeService.getValue();
	}
	
	@GetMapping("/sb/singleton/value")
	public Integer getSingletoneValue(){
		
		return singletonScopeService.getValue();
	}
	
	@PostMapping("/sb/singleton/value")
	public ResponseEntity<Boolean> setSingletoneValue(@RequestBody Integer value){
		
		singletonScopeService.setValue(value);
		
		return ResponseEntity.status(HttpStatus.OK).body(true);
	}
	
	@PostMapping("/sb/prototype/value")
	public ResponseEntity<Boolean> setprototypeValue(@RequestBody Integer value){
		
		prototypeScopeService.setValue(value);
		
		return ResponseEntity.status(HttpStatus.OK).body(true);
	}
	
}
