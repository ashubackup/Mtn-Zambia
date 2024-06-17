package com.Scheduler;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.Entity.AirtimeCallBackEntity;
import com.Repository.AirtimeCallBackRepo;
import com.Service.SmsService;

@Component
public class AirtimeSmsSend {
	@Autowired
	private AirtimeCallBackRepo repo;
	@Autowired
	private SmsService service;
	
	@Scheduled(cron ="0 30 13 * * * ", zone="IST")
	public void sendAirTimeSms() {
		List<AirtimeCallBackEntity> airtimeList = repo.findByStatusAndType("0","airtime");
		if(!airtimeList.isEmpty())
		{
			for (AirtimeCallBackEntity airtime : airtimeList) {
				JSONObject jsonObject = new JSONObject(airtime.getCallback().toString());
				if(jsonObject.getString("response_message").equalsIgnoreCase("Success")) {
					System.out.println("Inside success"+ jsonObject.getString("payer_number") +"  amount is :" + jsonObject.getString("amount") );
					service.getRequest(jsonObject.getString("payer_number"), jsonObject.getString("amount"), "airtime");
				}
				airtime.setStatus("Done");		
				repo.save(airtime);
			}
		}
		
		
		
	}
	
	@Scheduled(cron ="0 40 13 * * * ", zone="IST")
	public void sendCashSms() {
		List<AirtimeCallBackEntity> airtimeList = repo.findByStatusAndType("0","Cash");
		if(!airtimeList.isEmpty())
		{
			for (AirtimeCallBackEntity airtime : airtimeList) {
				JSONObject jsonObject = new JSONObject(airtime.getCallback().toString());
				if(jsonObject.getString("response_message").equalsIgnoreCase("Success")) {
					System.out.println("Inside success"+ jsonObject.getString("payer_number") +"  amount is :" + jsonObject.getString("amount") );
					
					service.getRequest(jsonObject.getString("payer_number"), jsonObject.getString("amount"), "Cash");
				}
				airtime.setStatus("Done");		
				repo.save(airtime);
			}
		}
		
		
		
	}

}
