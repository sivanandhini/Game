package com.kgisl.socket.service;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class SocketService {
	
	private static String SECRET_KEY="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJrZXkiOiJzZWNyZXQiLCJqdGkiOiJkNDY2YjE0MC1jZmY2LTQzZjYtODNjMS01MjhlZGRkMTY5NjgiLCJpYXQiOjE2MTE0MTEzNTAsImV4cCI6MTYxMTQxNDk1MH0.khisPcXxglLaOHz9M2n-xDSe1murrDRosYV-VkGb7xY";
	
	public String jwtMessageEncryption(String message) {
		//{jti=id, iat=1611412513, sub=xczcas, iss=issuer, exp=1612645768}
		String msgBody = createJWT("id", "issuer", message, 1233254345);

		return msgBody;
	}
	
	public static String createJWT(String id, String issuer, String subject, long ttlMillis) {
		  
	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);

	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder().setId(id)
	            .setIssuedAt(now)
	            .setSubject(subject)
	            .setIssuer(issuer)
	            .signWith(signatureAlgorithm, signingKey);
	  
	    //if it has been specified, let's add the expiration
	    if (ttlMillis > 0) {
	        long expMillis = nowMillis + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }  
	  
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}
}
