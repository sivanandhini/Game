package com.kgisl.multi.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kgisl.multi.dao.WebPunchDetailsDao;
import com.kgisl.multi.model.TenantUserDetails;
import com.kgisl.multi.model.WebPunchDetails;

@Transactional("tenantTransactionManager")
@Service
public class WebPunchDetailsService {
	
	@Autowired
	private WebPunchDetailsDao webPunchDetailsDao;

	public WebPunchDetails saveWebPunchById(TenantUserDetails tenantUserDetails) {
		
		WebPunchDetails webPunchDetails = new WebPunchDetails(); 
		webPunchDetails.setUserId(tenantUserDetails.getUserId());
		webPunchDetails.setPunchTime(new Date());
		
		webPunchDetailsDao.savesaveWebPunch(webPunchDetails);
		
		return webPunchDetails;
	}

}
