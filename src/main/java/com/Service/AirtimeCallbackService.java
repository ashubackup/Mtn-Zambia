package com.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.AirtimeCallBackEntity;
import com.Repository.AirtimeCallBackRepo;

@Service
public class AirtimeCallbackService 
{
	
	@Autowired
	AirtimeCallBackRepo airtimeCallBackRepo; 

	public String getAirtimeCallback(String callback)
	{
		String response = "Failed";
		try
		{
			AirtimeCallBackEntity airtimeCallBackEntity = new AirtimeCallBackEntity();
			airtimeCallBackEntity.setCallback(callback);
			airtimeCallBackEntity.setDatetime(LocalDateTime.now());
			airtimeCallBackEntity.setStatus("0");
			airtimeCallBackRepo.save(airtimeCallBackEntity);
			System.out.println("Callback saved for airtime callback");
			response = "Successfully Saved";
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response;
	}
}
