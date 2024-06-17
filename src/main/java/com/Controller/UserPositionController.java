package com.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Model.AirtimePositionResponse;
import com.Service.UserPositionSendService;

@RestController
@CrossOrigin("*")
public class UserPositionController {
	@Autowired
	private UserPositionSendService service;
	
	@GetMapping("/ani/{ani}")
	public ResponseEntity<?> sendUserPosition(@PathVariable String ani)
	{
		Map<String, AirtimePositionResponse> response = new HashMap<>();
		response.put("Daily", service.sendUserPositionForDaily(ani));
		response.put("Monthly", service.sendUserPositionForMonthly(ani));
		return ResponseEntity.ok(response);
	}

}
