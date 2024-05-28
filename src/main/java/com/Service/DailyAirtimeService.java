package com.Service;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Api.AirtimeApi;
import com.Api.Api;
import com.Entity.AirtimeData;
import com.Entity.OperatorInfo;
import com.Entity.Prizes;
import com.Repository.AirtimeDataRepo;
import com.Repository.OperatorInfoRepo;
import com.Repository.PrizesRepo;

@Service
public class DailyAirtimeService 
{
	@Autowired
	private AirtimeDataRepo airtimeRepo;
	
	@Autowired
	private OperatorInfoRepo operatorInfoRepo;
	
	@Autowired
	private PrizesRepo prizeRepo;
	
	@Autowired
	private AirtimeApi api;
	
	public void dailyAirtimeService()
	{
		try
		{
//			//serviceId=11 for BigcashMTN
//			System.out.println("Handling For Bigcash MTN, ServiceId = 11");
//			List<AirtimeData> numbers = airtimeRepo.getDailyAirtimeNumbers("11");
//			sendAirtime(numbers);
//			
//			System.out.println("\nHandling For Bigcash Telkom, ServiceId = 1");
//			//serviceId=1 for BigcashTelkom
//			List<AirtimeData> number2 = airtimeRepo.getDailyAirtimeNumbers("1");
			//serviceID is 1 for BIgCashZambia
			System.out.println("Handling airtime for BigCash MTN");
			List<AirtimeData> numbers = airtimeRepo.getDailyAirtimeNumbers("1");
			
			sendAirtime(numbers);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void sendAirtime(List<AirtimeData> numbers)
	{
		try
		{
			//First check Winners List Size
			System.out.println("Winners List Size is "+numbers.size()+"\n");
			
			//Getting Prizes according to this
			List<Prizes> prizes = prizeRepo.getPrizesToPay(numbers.size());
			System.out.println("Prizes List Size is "+prizes.size()+"\n");
			
			api.hitAirtimeApi(numbers);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}