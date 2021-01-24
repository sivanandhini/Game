package com.kgisl.socket.controller;

import javax.xml.bind.DatatypeConverter;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
public class SocketRestController {
	
	private static String SECRET_KEY="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJrZXkiOiJzZWNyZXQiLCJqdGkiOiJkNDY2YjE0MC1jZmY2LTQzZjYtODNjMS01MjhlZGRkMTY5NjgiLCJpYXQiOjE2MTE0MTEzNTAsImV4cCI6MTYxMTQxNDk1MH0.khisPcXxglLaOHz9M2n-xDSe1murrDRosYV-VkGb7xY";
	
	@MessageMapping("/user/{id}")
	public void sendMessageToBot(String message, @DestinationVariable String id) {
		
		Claims claims = decodeJWT(message);
		
		System.out.println(claims.get("sub"));
	}
	
	public static Claims decodeJWT(String jwt) {
	    //This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser()
	            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
	            .parseClaimsJws(jwt).getBody();
	    return claims;
	}
	
}
 