package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Service.AirtimeCallbackService;

@RestController
@CrossOrigin("*")
public class AirtimeCallback 
{
	
	@Autowired
	AirtimeCallbackService airtimeCallbackService;

	@PostMapping("/airtimecallback")
	public ResponseEntity<?> airitimeCallBack(@RequestBody String json)
	{
		String response = "Failed";
		try
		{
			response = airtimeCallbackService.getAirtimeCallback(json);
			return ResponseEntity.ok(response);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("Some thing Went Wrong");
		}
		
	}
}
