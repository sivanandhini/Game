package com.kgisl.multi.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * Extending web authentication to adjust security configuration to accept extra
 * parameter verification token
 */
@SuppressWarnings("serial")
public class TenantWebDetails extends WebAuthenticationDetails {

	private String tenantId;

	/* To get and set Tenant-Id before authentication */
	public TenantWebDetails(HttpServletRequest request) {
		super(request);
		tenantId = request.getParameter("Tenant-Id");
	}

	public String getTenantId() {
		return tenantId;
	}

}
