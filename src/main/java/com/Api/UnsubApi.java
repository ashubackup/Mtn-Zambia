package com.Api;

import java.util.Random;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.Entity.LoginInfo;
import com.Entity.TblUnsubsciption;
import com.Repository.LoginInfoRepo;

@Service
public class UnsubApi 
{

	@Autowired
	LoginInfoRepo infoRepo;
	
	@Autowired
	private Api apigenerateToken;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public String hitUnsubApi(String ani,String pack)
	{
		LoginInfo info = infoRepo.findByStatusAndType("1", "sub");
		String apiUrl = info.getApi();
		String token = apigenerateToken.genrateToken();

		apiUrl = apiUrl.replace("<ani>", ani).replace("<pack>", pack);

		System.out.println("Api is---" + apiUrl);
	    
		// Generate a random transactionId
		String transactionId = generateRandomTransactionId();
	    
	    HttpHeaders headers = new HttpHeaders();
	    //headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("Content-Type", "application/json");
	    headers.setBearerAuth(token);
	    headers.add("x-api-key", "ZShd6BjnAGLQguM5JvfSGG7CAVzpmnAh");
	    headers.add("transactionId", transactionId);

	    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

	    System.out.println("Request entity of unsub: " + requestEntity);

	    RestTemplate restTemplate = new RestTemplate();

	    try {
	        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.DELETE, requestEntity, String.class);
	        if (response.getStatusCode() == HttpStatus.OK) {
	            JSONObject jsonResponse = new JSONObject(response.getBody());
	            String status = jsonResponse.getString("status");
	            String statusCode = jsonResponse.getString("statusCode");
	            String statusMessage = jsonResponse.getString("statusMessage");

	            String responseMessage = "Status Code: " + statusCode + "\n"
	                    + "Status Message: " + statusMessage + "\n"
	                    + "Status: " + status;

	            System.out.println("Response of unsub api--" + responseMessage);
	            
	            if(statusMessage.equalsIgnoreCase("3021 Ok, Accepted"))
	            {
	            	
	            	return "Success";
	            }
	        } else {
	            System.out.println("Failed to unsub " + response.getStatusCode());
	            return "Failed";
	        }
	    } catch (HttpClientErrorException e) {
	    	e.printStackTrace();
	    	return "Failed";
	    } catch (HttpServerErrorException e) {
	    	e.printStackTrace();
	    	return "Failed";
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return "Failed";
	    }
	    return "Failed";
		
	}
	
	private String generateRandomTransactionId() {
		Random random = new Random();
		long randomNumber = 10000000000000L + (long) (random.nextDouble() * 90000000000000L);
		return Long.toString(randomNumber);
	}
	

}
