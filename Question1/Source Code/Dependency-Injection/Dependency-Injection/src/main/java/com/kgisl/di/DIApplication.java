package com.kgisl.di;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.kgisl.di"})
@EntityScan("com.kgisl.di.modal")
@EnableAutoConfiguration
public class DIApplication {

	public static void main(String[] args) {
		SpringApplication.run(DIApplication.class, args);
	}
	
}
