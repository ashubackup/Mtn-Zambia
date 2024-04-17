package com.Scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Api.Api;
import com.Entity.LoginInfo;
import com.Repository.LoginInfoRepo;

//@Component
@RestController
public class TokenScheduler 
{
	
	@Autowired
	LoginInfoRepo infoRepo;
	
	@Autowired
	Api api;

	@GetMapping("/token")
	public void token()
	{
		try
		{
			//api.genrateToken();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
