package com.Controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderController 
{

	@GetMapping("/headers")
	public ResponseEntity<?> getRequest(@RequestHeader Map<String,String> headers)
	{
	try
	{
		System.out.println("Headers is :::"+headers);
		return ResponseEntity.ok().body(headers);
	}
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseEntity.ok("Something Went Wrong");
	}
	}
}
