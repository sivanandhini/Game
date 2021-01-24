package com.kgisl.socket.test;

import java.util.Objects;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;

import com.kgisl.socket.service.SocketService;

public class SocketServiceTest {
	
	@InjectMocks
	private SocketService socketService;
	
	/* To test negative cases like exceptions */
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	private String id;
	
	private String issuer;
	
	private String subject; 
	
	private String ttlMillis;

	public void initializeVariables() {
		
		this.id = null;
		this.issuer = null;
		this.subject = null;
		this.subject = null;
	}

	public void setJWTValues(String id, String issuer, String subject, String ttlMillis) {

		this.id = id;
		this.issuer = issuer;
		this.subject = subject;
		this.ttlMillis = ttlMillis;
	}

	@SuppressWarnings("static-access")
	public void checkJWTEncryptedString() {

		String returnString = socketService.createJWT(id, issuer, subject, Long.parseLong(ttlMillis));
		
		Assert.assertTrue(Objects.nonNull(returnString));
	}

	@SuppressWarnings("static-access")
	public void checkJWTEncryptedStringException() {
		
		exceptionRule.expect(NullPointerException.class);
		
		try {
			socketService.createJWT(id, issuer, subject, !ttlMillis.isEmpty() ? Long.parseLong(ttlMillis) : null);
		} catch (Exception e) {
			
			Assert.assertTrue(e instanceof NullPointerException);
		}
		
		//System.out.println(returnString);
	}
	
	
	
}
