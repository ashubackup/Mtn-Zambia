package com.Service;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	private Api api;
	
	public void dailyAirtimeService()
	{
		try
		{
			//serviceId=11 for BigcashMTN
			System.out.println("Handling For Bigcash MTN, ServiceId = 11");
			List<AirtimeData> numbers = airtimeRepo.getDailyAirtimeNumbers("11");
			sendAirtime(numbers);
			
			System.out.println("\nHandling For Bigcash Telkom, ServiceId = 1");
			//serviceId=1 for BigcashTelkom
			List<AirtimeData> number2 = airtimeRepo.getDailyAirtimeNumbers("1");
			sendAirtime(number2);
			
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
			
			for(int i=0;i<numbers.size();i++)
			{
				//To get product_id
				 OperatorInfo operator = operatorInfoRepo.findByStatusAndServiceId("1",numbers.get(i).getServiceId());
				
				 //Generate Random Number
				 long rando = (long)Math.floor(Math.random()*9000000000000000000L);
				 String random = String.valueOf(rando);
				 
				  JSONObject jsonObj=new JSONObject();
				  jsonObj.put("smartloadId", "27784164170");
				  jsonObj.put("clientReference", "Ref" + random);
				  jsonObj.put("smsRecipientMsisdn", "27" + numbers.get(i).getAni());
				  jsonObj.put("deviceId", "1");
				  jsonObj.put("productId",operator.getProduceId());
				  jsonObj.put("amount",prizes.get(i).getPrize());
				  jsonObj.put("sendSms", true);
				  jsonObj.put("smsProviderIdentifier",operator.getMessage());
				  System.out.println("Request is "+jsonObj+"\n");
				  
				//Hit On Api for Airtime
//				  String response = api.hitRedeemInNewWay(jsonObj);
				  
				  numbers.get(i).setRequest(jsonObj.toString());
//				  numbers.get(i).setResponse(response);
				  numbers.get(i).setStatus("1");
				  
				  airtimeRepo.save(numbers.get(i));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}