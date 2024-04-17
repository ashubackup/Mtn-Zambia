package com.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Model.Request;
import com.Service.SaveRequest;

@RestController
public class USSDController 
{
	
	@Autowired
	SaveRequest requestService;

	@PostMapping(value = "/ussd", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getUSSDRequest(@RequestBody Request request,HttpSession session)
	{
		try
		{
			String response=requestService.saveRequest(request);
			return ResponseEntity.ok(response);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.ok(e.getMessage());
		}
		
	}
}
