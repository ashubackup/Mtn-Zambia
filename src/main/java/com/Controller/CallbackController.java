package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Service.SaveCallBack;

@RestController
@CrossOrigin("*")
public class CallbackController 
{
	@Autowired
	SaveCallBack callBack;
	
	@PostMapping("/mocallback")
	public ResponseEntity<?> getCallBack(@RequestBody String body)
	{
		try
		{
			String response = callBack.saveCallback(body);
			return ResponseEntity.ok(response);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(500).body("Failed To Save");
		}
		
	}
	
}
