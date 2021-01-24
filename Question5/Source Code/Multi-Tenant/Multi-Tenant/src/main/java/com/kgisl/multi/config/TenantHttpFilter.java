package com.kgisl.multi.config;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * To intercept and process requests before they are sent to servlets and response after servlet code is finished and 
 * before container sends the response back to the client. 
 */
public class TenantHttpFilter implements Filter{

    /**
     * To intercept and process requests before they are sent to servlets.
     */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		if(Objects.isNull(httpRequest.getSession().getAttribute("Tenant-Id")) && 
				SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken){
			httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		}
		
		chain.doFilter(request, response);
	}
	
}
