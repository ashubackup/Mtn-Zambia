package com.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SMSController 
{

	@PostMapping("/sms")
	public void getSmSRequest()
	{
		try
		{
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
