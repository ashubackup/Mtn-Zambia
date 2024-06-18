package com.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Service.UserPositionSendService;

@RestController
@CrossOrigin("*")
public class UserPositionController {
	@Autowired
	private UserPositionSendService service;
	
	@GetMapping("/ani/{ani}")
	public ResponseEntity<?> sendUserPosition(@PathVariable String ani)
	{
		
		if(ani.startsWith("0")) {
			 ani = "26" + ani;
		}
		if(!ani.startsWith("260")) {
			ani ="260"+ani;
		}
		return ResponseEntity.ok(service.sendUserPosition(ani));
	}

}
