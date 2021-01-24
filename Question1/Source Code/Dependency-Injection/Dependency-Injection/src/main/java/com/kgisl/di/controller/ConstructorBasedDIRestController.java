package com.kgisl.di.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConstructorBasedDIRestController {

	private String stringBean1;
	
	private String stringBean2;
	
	private Integer integerBean;
	
	ApplicationContext context;
	
	@Autowired
	public ConstructorBasedDIRestController(@Qualifier("strBean1") String stringBean1, 
			@Qualifier("strBean2") String stringBean2, Integer integerBean, ApplicationContext context){
		
		this.stringBean1 = stringBean1;
		this.stringBean2 = stringBean2;
		this.integerBean = integerBean;
		this.context = context;
	}
	
	@GetMapping("/constructor")
	public Map<String, Object> getBeanValues(){
		
		Map<String, Object> beans = new HashMap<>();
		
		beans.put("stringBean1", stringBean1);
		beans.put("stringBean2", stringBean2);
		beans.put("integerBean", integerBean);
		
		return beans; 
	}
	
	@PutMapping("/constructor")
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
