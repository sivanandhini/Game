package com.kgisl.socket;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
@EnableWebSocketMessageBroker
@ComponentScan(basePackages = "com.kgisl.socket.controller")
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	    @Override
	    public void configureMessageBroker(MessageBrokerRegistry config) {
	        config.enableSimpleBroker("/user");
	        config.setApplicationDestinationPrefixes("/app");
	    }
	 
	    @Override
	    public void registerStompEndpoints(StompEndpointRegistry registry) {
	         registry.addEndpoint("user", "admin").withSockJS();
	         registry.addEndpoint("/client")
	         .setAllowedOrigins("http://localhost:9090/WebSocket-Encrypt").withSockJS();
	    }

		@Override
		public void addArgumentResolvers(List<HandlerMethodArgumentResolver> arg0) {
			
		}

		@Override
		public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> arg0) {
			
		}

		@Override
		public void configureClientInboundChannel(ChannelRegistration registration) {
        }

		@Override
		public void configureClientOutboundChannel(ChannelRegistration registration) {
			
		}

		@Override
		public boolean configureMessageConverters(List<MessageConverter> arg0) {
			return false;
		}

		@Override
		public void configureWebSocketTransport(WebSocketTransportRegistration arg0) {
			
		}

}
