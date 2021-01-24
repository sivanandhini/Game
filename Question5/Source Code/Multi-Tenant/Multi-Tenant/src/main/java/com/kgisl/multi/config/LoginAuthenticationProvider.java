package com.kgisl.multi.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kgisl.multi.model.TenantUserDetails;
import com.kgisl.multi.model.User;
import com.kgisl.multi.service.UserService;


/**
 * Spring security custom authentication provider.
 * 
 * ALL Authentication request will be processed by AuthenticationProvider and a fully authenticated object with full credentials is returned or an exception will be returned.
 */
@Service
public class LoginAuthenticationProvider extends DaoAuthenticationProvider{
	
	@Autowired
	private UserService userService;
	
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
		
		TenantWebDetails tenantWebDetails = null;
		
		if(authentication.getDetails() instanceof TenantWebDetails){
			
			tenantWebDetails = ((TenantWebDetails) authentication.getDetails());
			
			String username = authentication.getName();
			String password = authentication.getCredentials().toString();
			
			List<User> user = userService.getUserByName(username);
			
			if(Objects.nonNull(user) && !user.isEmpty()){
				
				if(user.get(0).getPassword().equals(password)){
					
					List<SimpleGrantedAuthority> authorities = new ArrayList<>();
					
					authorities.add(new SimpleGrantedAuthority("ROLE_WEB"));
					
					/* Form custom user details */
					TenantUserDetails tenantUserDetails = new TenantUserDetails();
					tenantUserDetails.setUserId(user.get(0).getId());
					tenantUserDetails.setUsername(username);
					tenantUserDetails.setPassword(password);
					tenantUserDetails.setAuthority(authorities);
					tenantUserDetails.setTenantId(tenantWebDetails.getTenantId());
					
					return new UsernamePasswordAuthenticationToken(tenantUserDetails, password, authorities);
				}
			}
		}
		
	   throw new BadCredentialsException("Invalid username/password");
	}
	
}
