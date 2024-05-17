package com.Service;

import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Api.Api;
import com.Entity.LoginInfo;
import com.Entity.TblMessage;
import com.Entity.TblSmsLogs;
import com.Repository.LoginInfoRepo;
import com.Repository.MessageRepo;
import com.Repository.TblSmsLogsRepo;

@Service
public class SmsService {

	@Autowired
	MessageRepo messageRepo;

	@Autowired
	private Api apigenerateToken;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	LoginInfoRepo infoRepo;

	@Autowired
	private TblSmsLogsRepo smsLogsRepo;

	public void getRequest(String ani) {
		HttpEntity<String> requestEntity = null;
		try {
			TblMessage message = messageRepo.findByType("unsub");
			// Generate a random transactionId
			String randomString = generateRandomTransactionId();

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("senderAddress", "5555");

			JSONArray receiverAddressArray = new JSONArray();
			receiverAddressArray.put(ani);
			jsonObject.put("receiverAddress", receiverAddressArray);

			jsonObject.put("message", message);
			jsonObject.put("clientCorrelatorId", randomString);
			jsonObject.put("keyword", "Daily");
			jsonObject.put("serviceCode", "5555");
			jsonObject.put("requestDeliveryReceipt", false);

			String token = apigenerateToken.genrateToken();

			LoginInfo info = infoRepo.findByStatusAndType("1", "sub");
			String api = info.getApi();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setBearerAuth(token);

			requestEntity = new HttpEntity<>(jsonObject.toString(), headers);

			System.out.println("Request entity: " + requestEntity);

			ResponseEntity<String> response = restTemplate.exchange(api, HttpMethod.POST, requestEntity, String.class);
			System.out.println("Response: " + response);
			if (response.getStatusCode() == HttpStatus.OK) {
				JSONObject jsonResponse = new JSONObject(response.getBody());
				String statusCode = jsonResponse.getString("statusCode");
				String statusMessage = jsonResponse.getString("statusMessage");
				String transactionId = jsonResponse.getString("transactionId");
				String status = jsonResponse.getJSONObject("data").getString("status");

				String responseMessage = "Status Code: " + statusCode + "\n" + "Status Message: " + statusMessage + "\n"
						+ "Transaction ID: " + transactionId + "\n" + "Status: " + status;

				System.out.println(responseMessage);

				var user = TblSmsLogs.builder().ani(ani).request(requestEntity.toString()).response(response.toString())
						.statusCode(response.getStatusCode().toString()).build();
				smsLogsRepo.save(user);

			} else {
				var user = TblSmsLogs.builder().ani(ani).request(requestEntity.toString()).response(response.toString())
						.statusCode(response.getStatusCode().toString()).build();
				smsLogsRepo.save(user);

				System.out.println("Failed to send sms: " + response.getStatusCode());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			var user = TblSmsLogs.builder().ani(ani).request(requestEntity.toString()).response(e.getMessage())
					.statusCode("500").build();
			
		}

	}

	private String generateRandomTransactionId() {
		Random random = new Random();
		long randomNumber = 10000000000000L + (long) (random.nextDouble() * 90000000000000L);
		return Long.toString(randomNumber);
	}
}
