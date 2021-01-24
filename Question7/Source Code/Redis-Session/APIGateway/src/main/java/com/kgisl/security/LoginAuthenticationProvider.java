package com.kgisl.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * Spring security custom authentication provider.
 * 
 * ALL Authentication request will be processed by AuthenticationProvider and a fully authenticated object with full credentials is returned or an exception will be returned.
 */
@Service
public class LoginAuthenticationProvider extends DaoAuthenticationProvider{
	
	@Value( "${security.user.name}" )
	private String sysUsername;
	
	@Value( "${security.user.password}" )
	private String sysPassword;
	
	/**
	 * To define password crypt algorithm
	 * 
	 * @return Password encoder object 
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Autowired
	@Override
	@Qualifier("userDetailsService")
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
		super.setPasswordEncoder(passwordEncoder());
	}
	
	/**
	 * Used to validate & authenticate user using submitted user name, password from login page.
	 * 
	 */
	@Override
	public Authentication authenticate(Authentication authentication) {
		
				String username = authentication.getName();
				String password = authentication.getCredentials().toString();
				
				if(Objects.nonNull(username) && Objects.nonNull(password)
						&& username.equals(sysUsername) && password.equals(sysPassword)){
					Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
					
					return new UsernamePasswordAuthenticationToken(username, password, authorities);
				}
				
				throw new BadCredentialsException("Invalid username/password");
 	}
	
}
