package com.kgisl.socket;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@ComponentScan(basePackages = "com.kgisl.socket.controller")
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	    @Override
	    public void configureMessageBroker(MessageBrokerRegistry config) {
	        config.enableSimpleBroker("/user");
	        config.setApplicationDestinationPrefixes("/client");
	    }
	 
	    @Override
	    public void registerStompEndpoints(StompEndpointRegistry registry) {
	         registry.addEndpoint("user").withSockJS();
	         registry.addEndpoint("/tapp")
	         .setAllowedOrigins("http://localhost:8109").withSockJS();
	    }

}
