package com.Scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Service.DailyAirtimeService;

//@RestController
@Component
public class AirtimeScheduler 
{
	@Autowired
	private DailyAirtimeService airtimeService;

	//@GetMapping("/airtime")
	@Scheduled(cron="0 0 13 * * * ",zone="IST")
	public void airtimeScheduler()
	{
		try
		{
			airtimeService.dailyAirtimeService();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
