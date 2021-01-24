package com.kgisl.multi.config;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kgisl.multi.model.TenantUserDetails;

public class SessionRoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {

		String tenantId = null;

		RequestAttributes attribs = RequestContextHolder.getRequestAttributes();

		if (Objects.nonNull(attribs)) {

			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
					.getRequest();

			if (Objects.nonNull(request.getParameter("Tenant-Id"))) {
				tenantId = request.getParameter("Tenant-Id");
			} else if (Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())) {
				TenantUserDetails securedUser = (TenantUserDetails) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal();
				tenantId = securedUser.getTenantId();
			}
		}

		return tenantId;
	}

}
