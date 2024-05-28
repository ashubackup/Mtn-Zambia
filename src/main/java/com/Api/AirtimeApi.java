package com.Api;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Entity.AirtimeData;

@Service
public class AirtimeApi 
{
	
	@Autowired
	private RestTemplate restTemplate;

	public void hitAirtimeApi(List<AirtimeData> numbers)
	{
		try
		{
			
			for(int i=0;i<numbers.size();i++)
			{
				String url = "https://api.primenetpay.com:9110/api/v1/billers/airtime/purchase";
				//To get product_id
				// OperatorInfo operator = operatorInfoRepo.findByStatusAndServiceId("1",numbers.get(i).getServiceId());
				
				 //Generate Random Number
				 long rando = (long)Math.floor(Math.random()*9000000000000000000L);
				 String random = String.valueOf(rando);
				 
				  JSONObject jsonObj=new JSONObject();
				  jsonObj.put("receiverNumber", numbers.get(i).getAni());
				  jsonObj.put("externalReference",random);
				  jsonObj.put("amount","1");
				  jsonObj.put("accountNumber", numbers.get(i).getAni());
				  
				  HttpHeaders headers = new HttpHeaders();
				  headers.setContentType(MediaType.APPLICATION_JSON);
				  
				  HttpEntity<String> entity = new HttpEntity<>(jsonObj.toString(),headers);
				  
				  
				  
				  System.out.println("Request is "+jsonObj+"\n");
				  
				  ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
				  
				  System.out.println("Response from ani api "+response);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
