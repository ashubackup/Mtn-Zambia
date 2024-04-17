package com.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.AirtimeData;
import com.Entity.Prizes;
import com.Entity.ServiceInfo;
import com.Entity.TermsData;
import com.Model.LoginModal;
import com.Model.SaveScoreRequest;
import com.Model.SendScoreRequest;
import com.Model.SendWinnersRequest;
import com.Model.SubRequest;
import com.Model.TermsRequest;
import com.Repository.ServiceInfoRepo;
import com.Service.LogInService;
import com.Service.SaveScoreService;
import com.Service.SendPrizesServices;
import com.Service.SendScoreService;
import com.Service.SendTermsService;
import com.Service.SendWinnersNewService;
import com.Service.SendWinnersService;
import com.Service.SubService;

@RestController
@CrossOrigin("*")
public class WapController 
{
	@Autowired
	private SendTermsService serviceTerm;
	
	
	@CrossOrigin
	@PostMapping("/sendTerms")
	public ResponseEntity<?> sendTerms(@RequestBody TermsRequest request)
	{
		Map<String,TermsData> response=new HashMap<>();
		try
		{
			TermsData data = serviceTerm.sendTermsService(request);
			response.put("response",data);
			return ResponseEntity.ok(response);
		}catch(Exception e)
		{
			e.printStackTrace();
			response.put("response",null);
			return ResponseEntity.status(200).body(response);
		}
	}
	
	@Autowired
	private ServiceInfoRepo serviceRepo;
	
	@CrossOrigin
	@GetMapping("/sendServicesData")
	public ResponseEntity<?> sendServiceData()
	{
		Map<String,List<ServiceInfo>>response =new HashMap<>();
		try
		{
			List<ServiceInfo> services = serviceRepo.findByStatus("1");
			response.put("response",services);
			return ResponseEntity.ok(response);
		}catch(Exception e)
		{
			e.printStackTrace();
			response.put("response",null);
			return ResponseEntity.status(200).body(response);
		}
	}
	
	@Autowired
	private SendScoreService serviceScore;
	
	@CrossOrigin
	@PostMapping("/sendScore")
	public ResponseEntity<?> sendScore(@RequestBody SendScoreRequest request)
	{
		try
		{
			Map<String,AirtimeData> scores = serviceScore.sendScoreService(request);
			return ResponseEntity.ok(scores);
		}catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(200).body(null);
		}
	}
	
	@Autowired
	private SendWinnersService serviceWinner;
	
	@CrossOrigin
	@PostMapping("/sendWinners")
	public ResponseEntity<?> sendWinners(@RequestBody SendWinnersRequest request)
	{
		Map<String,List> winners=null;
		try
		{
			winners=serviceWinner.sendWinnersService(request);
			return ResponseEntity.ok(winners);
		}catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(200).body(winners);
		}
	}
	
	@Autowired
	private SendPrizesServices servicePrize;
	
	@CrossOrigin
	@GetMapping("/sendPrizes")
	public ResponseEntity<?> sendPrizes()
	{
		try
		{
			Map<String, List<Prizes>> prizes = servicePrize.sendPrizesServices();
			return ResponseEntity.ok(prizes);
		}catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(200).body(null);
		}
	}
	
	@Autowired
	private SaveScoreService saveServiceScore;
	
	@CrossOrigin
	@PostMapping("/saveScore")
	public ResponseEntity<?> saveScore(@RequestBody SaveScoreRequest request)
	{
		Map<String,String>response=new HashMap<>();
		try
		{
			saveServiceScore.saveScoreService(request);
			response.put("response","Data Saved Successfully");
			return ResponseEntity.ok(response);
		}catch(Exception e)
		{
			e.printStackTrace();
			response.put("response","Something Went Wrong");
			return ResponseEntity.status(200).body(response);
		}
	}
	
	@Autowired
	private SendWinnersNewService serviceWinerNew;
	
	@CrossOrigin
	@PostMapping("/sendWinnersNew")
	public ResponseEntity<?> sendWinnersNew(@RequestBody SendWinnersRequest request)
	{
		Map<String,List<Map<String,String>>>response=new HashMap<>();
		try
		{
			List<Map<String,String>> result = serviceWinerNew.sendWinnersNewService(request);
			response.put("response",result);
			return ResponseEntity.ok(response);
		}catch(Exception e)
		{
			e.printStackTrace();
			response.put("response",null);
			return ResponseEntity.status(200).body(response);
		}
	}
	

	@GetMapping("/test")
	public ResponseEntity<?> testController()
	{
		try
		{
			return ResponseEntity.ok().body("Thank you get your Request");
		}
		catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.badRequest().body("Thank you get your Request");
		}
	}
	
	
	@Autowired
	LogInService logInService;
	
	@CrossOrigin
	@PostMapping("/login")
	public ResponseEntity<?> getRequest(@RequestBody LoginModal login)
	{
		try
		{
			
			System.out.println("login"+login);
			String response =logInService.getRequest(login);
			return ResponseEntity.ok(response);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.badRequest().body("0");
		}
	}
	
	@Autowired
	SubService subService;
	
	@CrossOrigin
	@PostMapping("/subReq")
	public ResponseEntity<?> getSubRequest(@RequestBody SubRequest entity) {
		
		try
		{
			System.out.println("here..");
			String response=subService.getSUbRequest(entity);
			return ResponseEntity.ok(response);
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Some Thing Went Wrong");
					
		}
	}
	
}