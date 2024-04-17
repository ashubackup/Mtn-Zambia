package com.Api;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.Entity.LoginInfo;
import com.Repository.LoginInfoRepo;

@Service
public class Api {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private LoginInfoRepo loginRepo;

//	 public void generateTokenNewWay()
//	 {
//		 try
//		 {
//			 String token = "";
//
//			 LoginInfo info = loginRepo.findByStatus("1");
//			 String api = info.getApi();
//			 String base64EncodeString = info.getBaseEncoded();
//			 
//			 String auth="Basic "+base64EncodeString;
//			 System.out.println("api is "+api);
//			 System.out.println("auth is "+auth);
//			 
//			 HttpHeaders headers=new HttpHeaders();
//			 headers.set("Authorization",auth);
//
//			 HttpEntity<String> entity=new HttpEntity<String>(headers);
//			 ResponseEntity<String> responseEntity 
//			 		= restTemplate.postForEntity(api, entity, String.class);
//			 
//			 System.out.println("Body: "+responseEntity.getBody());
//			 System.out.println("Status "+responseEntity.getStatusCode());
//			 
//			 JSONObject jsonObj = new JSONObject(responseEntity.getBody());
//			 
//	         token = jsonObj.get("accessToken").toString();
//	         System.out.println("Token is :  " + token);
//	       
//	         info.setToken(token);
//	         loginRepo.save(info);
//	         System.out.println("Token Updated");
//			 
//		 }catch(Exception e)
//		 {
//			 e.printStackTrace();
//		 }
//	 }
//	 

	// Hitting for redeem - to transfer Airtime Prizes
//	 public String hitRedeemInNewWay(JSONObject body)
//	 {
//		 try
//		 {
//			 LoginInfo rechargeData = loginRepo.findByStatus("1");
//			 String api = rechargeData.getRechargeApi();
//			 String token = rechargeData.getToken();
//			 
////			 System.out.println("Token is "+token);
//			 HttpHeaders headers=new HttpHeaders();
//			 headers.set("Authorization", "Bearer " + token);
//			 headers.set("Accept", "application/json");
//			 headers.set("Content-Type", "application/json; utf-8");
//			 
//			 HttpEntity<String> entity=new HttpEntity<String>(body.toString(),headers);
//			 
//			 System.out.println("Request is "+body);
//			 
//			 ResponseEntity<String> responseEntity 
//			 		= restTemplate.postForEntity(api,entity,String.class);
//			 
//			 System.out.println("Response:: "+responseEntity.getBody());
//			 
//			 return responseEntity.getBody();
//			 
//		 }catch(Exception e)
//		 {
//			 e.printStackTrace();
//			 return e.getMessage();
//		 }
//	 }

	@Autowired
	LoginInfoRepo infoRepo;

	public String genrateToken() {

		String accessToken = "0";
		try

		{
			LoginInfo info = infoRepo.findByStatusAndType("1", "login");
			String api = info.getApi();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			// Set up request body parameters
			MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
			requestBody.add("client_id", "ZShd6BjnAGLQguM5JvfSGG7CAVzpmnAh"); // Replace with actual username
			requestBody.add("client_secret", "2NSzkE9Nao1wQzez"); // Replace with actual password
//	            requestBody.add("grant_type", "client_credentials");

			// Create HTTP entity with headers and body
			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

			// Make HTTP POST request to the token endpoint
			RestTemplate restTemplate = new RestTemplate();
			String response = restTemplate.postForObject(api, requestEntity, String.class);

			// Process the response (token)
			System.out.println("Token generated: " + response);

			JSONObject resposneJson = new JSONObject(response);
			accessToken = resposneJson.get("access_token").toString();
			info.setToken(accessToken);
			infoRepo.save(info);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return accessToken;
	}
}