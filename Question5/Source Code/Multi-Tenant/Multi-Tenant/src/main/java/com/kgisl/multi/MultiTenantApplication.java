package com.kgisl.multi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = {"com.kgisl.multi"})
@SpringBootApplication
@PropertySource({ "classpath:application.properties" })
public class MultiTenantApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MultiTenantApplication.class, args);
	}
	
}
