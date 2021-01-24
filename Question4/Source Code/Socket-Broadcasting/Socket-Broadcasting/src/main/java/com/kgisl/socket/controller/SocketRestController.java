package com.kgisl.socket.controller;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import com.kgisl.socket.resource.Message;
import com.kgisl.socket.service.SocketService;

@RestController
public class SocketRestController {
	
	@Autowired
	private SocketService chatService;
	
	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;
	
	@MessageMapping("/admin/{id}")
	@SendTo("/user/messages")
	public Message sendMessageToAdmin(Message message,@DestinationVariable String id) {
		
		message.setUserId(id);
		
		return message;
	}
	
	@MessageMapping("/user/{id}")
	public void sendMessageToUser(Message message,@DestinationVariable("id") String id) {

		simpMessagingTemplate.convertAndSend("/user/message/"+id, message);
	}

	@MessageMapping("/user/all")
	@SendTo("/user/messages/all")
	public Message sendMessageToAll(Message message) throws InterruptedException, ExecutionException {
		
		WebSocketClient webSocketClient = new StandardWebSocketClient();
		
		SockJsClient sockJsClient = new SockJsClient(Arrays.asList(new WebSocketTransport(webSocketClient)));
		
		WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
		
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		StompSession session;
		    session = stompClient
		            .connect(
		                "http://localhost:9090/WebSocket-Encrypt/tapp", 
		                 new StompSessionHandlerAdapter() {})
		            .get();
		    
		String encryptMessage = chatService.jwtMessageEncryption(message.getMessage());
		    
		session.send("/client/user/1", encryptMessage);
		
		return message;
	}

}
