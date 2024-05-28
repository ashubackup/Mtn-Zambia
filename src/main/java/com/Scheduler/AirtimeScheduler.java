package com.Scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Service.DailyAirtimeService;

@RestController
public class AirtimeScheduler 
{
	@Autowired
	private DailyAirtimeService airtimeService;

	@GetMapping("/airtime")
	public void airtimeScheduler()
	{
		try
		{
			airtimeService.dailyAirtimeService();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
