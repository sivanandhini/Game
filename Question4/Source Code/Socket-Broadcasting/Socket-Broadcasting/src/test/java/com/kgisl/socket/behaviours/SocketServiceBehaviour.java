package com.kgisl.socket.behaviours;

import com.kgisl.socket.test.SocketServiceTest;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SocketServiceBehaviour {
	
    private SocketServiceTest socketServiceTest;
	
	@Before
	public void intializeService(){
		
		socketServiceTest = new SocketServiceTest();
	}

	@Given("^secret key to encrypt message content$")
	public void secret_key_to_encrypt_message_content() throws Throwable {

		socketServiceTest.initializeVariables();
	}

	@When("^we need to create jwt encrypted string with (.*), (.*), (.*) and (.*)$")
	public void we_need_to_create_jwt_encrypted_string_with_a_test_sub_and(String id, String issuer, String subject, String ttlMillis) throws Throwable {

		socketServiceTest.setJWTValues(id, issuer, subject, ttlMillis);
	}

	@Then("^it should return created token$")
	public void it_should_return_created_token() throws Throwable {

		socketServiceTest.checkJWTEncryptedString();
	}

	@Then("^it should throw an exception$")
	public void it_should_throw_an_exception() throws Throwable {
		
		socketServiceTest.checkJWTEncryptedStringException();
	}
	
}
