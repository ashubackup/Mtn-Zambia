package com.Api;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.Entity.AirtimeData;
import com.Entity.Prizes;
import com.Repository.AirtimeDataRepo;

@Service
public class AirtimeApi 
{
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	AirtimeDataRepo airtimeDataRepo;
	
	public void hitAirtimeApi(List<AirtimeData> numbers,List<Prizes> prizes)
	{
		try
		{
			
			for(int i=0;i<numbers.size();i++)
			{
				String url = "http://api.primenetpay.com:9110/api/v1/billers/airtime/purchase";
				
				 String randomString = new Random().ints(24, 0, 10).mapToObj(String::valueOf).collect(Collectors.joining());
				 
				  JSONObject jsonObj=new JSONObject();
				  jsonObj.put("receiverNumber", numbers.get(i).getAni());
				  jsonObj.put("externalReference",randomString);
				  jsonObj.put("amount",prizes.get(i).getPrize());
				  jsonObj.put("accountNumber", numbers.get(i).getAni());
				  
				  HttpHeaders headers = new HttpHeaders();
				  headers.set("Content-Type","application/json");
				  headers.set("X-AUTHORIZATION","7kJt7EpgfWrZccWpi7jzGOp0gWqEJquK9sbt5D2sstjkJXRJMbbVpEaMeVZAmBw9");
				  HttpEntity<String> entity = new HttpEntity<>(jsonObj.toString(),headers);
				  
				  
				  
				  System.out.println("Request is "+entity+"\n");
				  
				  ResponseEntity<String> response = restTemplate.postForEntity(url,entity, String.class);
				  
				  System.out.println("Response from ani api "+response);
				  numbers.get(i).setRequest(entity.toString());
				  numbers.get(i).setResponse(response.toString());
				  numbers.get(i).setStatus("1");
				  airtimeDataRepo.save(numbers.get(i));
			}
			
		}catch (Exception e) {
			// TODO: handle exce ption
			e.printStackTrace();
		}
	}
	
}
