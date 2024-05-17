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