package com.kgisl.di.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SetterBasedDIRestController {

    private String strBean1;
	
	private String strBean2;
	
	private Integer integerBean;

	@Autowired
	public void setStrBean1(String strBean1) {
		this.strBean1 = strBean1;
	}

	@Autowired
	public void setStrBean2(String strBean2) {
		this.strBean2 = strBean2;
	}

	@Autowired
	public void setIntegerBean(Integer integerBean) {
		this.integerBean = integerBean;
	}
	
	@GetMapping("/setter")
	public Map<String, Object> getBeanValues(){
		
		Map<String, Object> beans = new HashMap<>();
		
		beans.put("stringBean1", strBean1);
		beans.put("stringBean2", strBean2);
		beans.put("integerBean", integerBean);
		
		return beans; 
	}
	
	@PutMapping("/setter")
	public Map<String, Object> setBeanValues(@RequestParam(name = "stringBean1", required = false) String stringBean1, 
			@RequestParam(name = "stringBean2", required = false) String stringBean2, 
			@RequestParam(name = "integerBean", required = false) Integer integerBean){
		
		this.strBean1 = stringBean1;
		this.strBean2 = stringBean2;
		this.integerBean = integerBean;
		
		Map<String, Object> beans = new HashMap<>();
		
		beans.put("stringBean1", strBean1);
		beans.put("stringBean2", strBean2);
		beans.put("integerBean", integerBean);
		
		return beans; 
	}
	
}
