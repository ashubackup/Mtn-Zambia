package com.Api;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.Entity.LoginInfo;
import com.Entity.ServiceInfoPrice;
import com.Entity.TblSubscription;
import com.Entity.sub_api_logs;
import com.Repository.LoginInfoRepo;
import com.Repository.ServiceInfoPriceRepo;
import com.Repository.TblLogsRepo;
import com.Repository.TblSubRepo;

@Service
public class SubApi {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Api apigenerateToken;

	@Autowired
	LoginInfoRepo infoRepo;

	@Autowired
	ServiceInfoPriceRepo infoPriceRepo;
	
	@Autowired
	TblLogsRepo logsRepo;
	
	@Autowired
	TblSubRepo tblSubRepo;

	public String hitSubscription(String ani, String pack) {

		String status="0";
		// Create HttpEntity with headers and body
		HttpEntity<String> requestEntity = null;
		sub_api_logs api_logs = new sub_api_logs();
		LocalDateTime zimbabweTime = LocalDateTime.now(ZoneId.of("Africa/Harare"));
		
		try {

			
			// Generate a random transactionId
			String randomString = generateRandomTransactionId();

			String token = apigenerateToken.genrateToken();

			ServiceInfoPrice infoPrice = infoPriceRepo.findByPack(pack);

			LoginInfo info = infoRepo.findByStatusAndType("1", "sub");
			String api = info.getApi();

			api = api.replace("<ani>", ani);
			
			System.out.println("URL"+api);
			HttpHeaders headers = new HttpHeaders();
			headers.setBearerAuth(token);
			headers.add("x-api-key", "ZShd6BjnAGLQguM5JvfSGG7CAVzpmnAh");
			headers.add("Content-Type", "application/json");
			headers.add("transactionId", randomString);
			headers.add("x-country-code", "ZMB");

			JSONObject jsonObject = new JSONObject();

			jsonObject.put("subscriptionProviderId", "CSM");
			jsonObject.put("subscriptionDescription", infoPrice.getPlan_name());
			jsonObject.put("subscriptionId", infoPrice.getPlan_id());
			jsonObject.put("registrationChannel", "WEB");

			requestEntity = new HttpEntity<>(jsonObject.toString(), headers);

			System.out.println("Request"+requestEntity);
			String response = restTemplate.postForObject(api, requestEntity, String.class);
			System.out.println("Response: " + response);


			TblSubscription subscription = new TblSubscription();
			subscription.setAmount(infoPrice.getPrice());
			subscription.setAni(ani);
			subscription.setMode("WAP");
			subscription.setSub_date_time(LocalDateTime.now());
			subscription.setPack(pack);
			tblSubRepo.save(subscription);
			
			System.out.println("Added in table subscription");
			
			api_logs.setAni(ani);
			api_logs.setDatetime(zimbabweTime);
			api_logs.setRequest(requestEntity.toString());
			api_logs.setResponse(response);
			logsRepo.save(api_logs);
			status="0";
		}catch (HttpClientErrorException.BadRequest e) 
		{
			status="4";
			System.out.println("e"+e.getMessage());
			api_logs.setAni(ani);
			api_logs.setDatetime(zimbabweTime);
			api_logs.setRequest(requestEntity.toString());
			api_logs.setResponse(e.getMessage());
			
			// TODO: handle exception
		}
		
		
				catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			api_logs.setAni(ani);
			api_logs.setDatetime(zimbabweTime);
			api_logs.setRequest(requestEntity.toString());
			api_logs.setResponse(e.getMessage());
			logsRepo.save(api_logs);
			status="3";
			
		}
		System.out.println("status"+status);
		return status;
	}

	private String generateRandomTransactionId() {
		Random random = new Random();
		long randomNumber = 10000000000000L + (long) (random.nextDouble() * 90000000000000L);
		return Long.toString(randomNumber);
	}
	
}





//catch (HttpClientErrorException.BadRequest e) {
//    // Handle 400 Bad Request response
//    // Log the error, inform the user, or take appropriate action
//	JSONObject jsonObject = new JSONObject(e.getMessage());
//	String resp= jsonObject.get("400").toString();
//	System.out.println("resp"+resp);
////	if(resp.equalsIgnoreCase("You have Already Subscribed Requested Services"))
////	{
////		status="4";
////	}
////	else {
////		status="3";
////	}
//    System.out.println("HTTP 400 Bad Request: " + e.getMessage());
//    // Extract value from error message
//    
//
//    api_logs.setAni(ani);
//	api_logs.setDatetime(zimbabweTime);
//	api_logs.setRequest(requestEntity.toString());
//	api_logs.setResponse(e.getMessage());
//	logsRepo.save(api_logs);
//    // Rest of the error handling logic...
//}
