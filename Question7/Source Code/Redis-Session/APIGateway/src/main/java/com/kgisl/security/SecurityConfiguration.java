package com.kgisl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * To provide authentication, authorization, access-control and other security features.
 */
@Configurable
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private LoginAuthenticationProvider loginAuthenticationProvider;
	
	/**
	 * Used to configure/override authentication provider.
	 * @param auth - default authentication manager.
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.authenticationProvider(loginAuthenticationProvider);
	}

	/**
	 * Overriding the default spring boot security.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				/* authenticate all remaining URLS */
				.anyRequest().authenticated()
				/* authentication type */
				.and()
				.sessionManagement()
				/* session state */
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.maximumSessions(1).maxSessionsPreventsLogin(true);

		//http.csrf();
	}
	
}
