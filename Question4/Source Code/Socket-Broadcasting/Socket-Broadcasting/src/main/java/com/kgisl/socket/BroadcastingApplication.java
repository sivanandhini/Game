package com.kgisl.socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = {"com.kgisl.socket"})
@SpringBootApplication
@EnableAutoConfiguration
@PropertySource({ "classpath:application.properties" })
public class BroadcastingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BroadcastingApplication.class, args);
	}

}
