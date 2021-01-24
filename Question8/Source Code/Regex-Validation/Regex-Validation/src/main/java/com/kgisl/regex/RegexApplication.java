package com.kgisl.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.kgisl.regex"})
public class RegexApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegexApplication.class, args);
	}

}
