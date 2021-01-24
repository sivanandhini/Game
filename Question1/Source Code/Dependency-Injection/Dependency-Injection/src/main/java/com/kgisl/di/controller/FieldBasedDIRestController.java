package com.kgisl.di.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FieldBasedDIRestController {

	@Autowired
	@Qualifier("strBean1")
	private String stringBean1;
	
	@Autowired
	@Qualifier("strBean2")
	private String stringBean2;
	
	@Autowired
	private Integer integerBean;
	
	@GetMapping("/field")
	public Map<String, Object> getBeanValues(){
		
		Map<String, Object> beans = new HashMap<>();
		
		beans.put("stringBean1", stringBean1);
		beans.put("stringBean2", stringBean2);
		beans.put("integerBean", integerBean);
		
		return beans; 
	}
	
	@PutMapping("/field")
	public Map<String, Object> setBeanValues(@RequestParam(name = "stringBean1", required = false) String stringBean1, 
			@RequestParam(name = "stringBean2", required = false) String stringBean2, 
			@RequestParam(name = "integerBean", required = false) Integer integerBean){
		
		this.stringBean1 = stringBean1;
		this.stringBean2 = stringBean2;
		this.integerBean = integerBean;
		
		Map<String, Object> beans = new HashMap<>();
		
		beans.put("stringBean1", stringBean1);
		beans.put("stringBean2", stringBean2);
		beans.put("integerBean", integerBean);
		
		return beans; 
	}
}
