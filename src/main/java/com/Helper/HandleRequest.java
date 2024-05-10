package com.Helper;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.Api.SubApi;
import com.Api.UnsubApi;
import com.Entity.TblSubscription;
import com.Entity.TblUnsubsciption;
import com.Entity.TblUssdSession;
import com.Model.Request;
import com.Repository.TblSubRepo;
import com.Repository.UssdSessionRepo;
import com.Response.NewUserResponse;
import com.Response.Response;
import com.Service.ResponseService;

@Service
public class HandleRequest 
{
	
	@Autowired
	UssdSessionRepo sessionRepo;
	
	@Autowired
	TblSubRepo subRepo;
	
	@Value("${daily}")
	private String dailyPack;
	
	@Value("${weekly}")
	private String weeklyPack;

	@Value("${monthly}")
	private String monthlyPack;
	
	@Autowired
	ResponseService responseService;
	
	@Autowired
	NewUserResponse newUserResponse;
	
	@Autowired
	UnsubApi unsubApi;
	
	
	@Autowired
	SubApi subApi;
	

	public String handleRequest(Request request)
	{
		String xmlString=null;
		try
		{
			
			System.out.println("flow in handle Request");
			String ani=request.getMsisdn();
			
			String newRequest = request.getNewRequest();
			if(newRequest.equalsIgnoreCase("1"))
			{
				List<TblSubscription> subscriptionList = subRepo.findByAni(ani);
				
				if(subscriptionList.size()!=0)
				{
					String count="1";
					
					for(TblSubscription subs : subscriptionList)
					{
						
						String packType=subs.getPack();
						if(packType.equalsIgnoreCase("Daily"))
						{
							xmlString = responseService.createResponse(ani,weeklyPack,monthlyPack);
							addSession(ani, count);
						}
						else if(packType.equalsIgnoreCase("weekly"))
						{
							xmlString = responseService.createResponse(ani,dailyPack,monthlyPack);
							addSession(ani, count);
						}
						else if(packType.equalsIgnoreCase("monthly"))
						{
							xmlString = responseService.createResponse(ani,dailyPack,weeklyPack);
							addSession(ani, count);
						}
						else
						{
							xmlString = responseService.createResponse(ani,weeklyPack,monthlyPack);
							addSession(ani, count);
						}
						
					}
				}else
				{
					String count="1";
					xmlString = newUserResponse.newUserResp(ani, dailyPack, weeklyPack, monthlyPack);
					addSession(ani, count);
				}
			}
//			else
//			{
////				TblUssdSession session = sessionRepo.findByAni(ani);
////				if(session.getCount().equalsIgnoreCase("1"))
////				{
////					subApi.hitSubscription(ani, "Daily");
////				}
////				else if(session.getCount().equalsIgnoreCase("2"))
////				{
////					subApi.hitSubscription(ani, "Weekly");
////				}
////				else if(session.getCount().equalsIgnoreCase("3"))
////				{
////					subApi.hitSubscription(ani, "Monthly");
////				}
////				else if(session.getCount().equalsIgnoreCase("4"))
////				{
////					TblSubscription subscription = subRepo.getUserByAni(ani);
////					String unsubResponse=unsubApi.hitUnsubApi(subscription.getAni(),subscription.getPack());
////					if(unsubResponse.equalsIgnoreCase("success"))
////					{
////						TblUnsubsciption tblUnsubsciption = new TblUnsubsciption();
////		            	
////		            	tblUnsubsciption.setAni(ani);
////		            	tblUnsubsciption.setAmount(subscription.getAmount());
////		            	tblUnsubsciption.setM_act(subscription.getMode());
////		            	tblUnsubsciption.setPack(unsubResponse);
////					}
////					else
////					{
////						
////					}
////				}
////				
////			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return xmlString;
	}
	
	public void addSession(String ani,String count)
	{
		TblUssdSession session= sessionRepo.findByAni(ani);
		if(session==null)
		{
			TblUssdSession tblUssdSession = new TblUssdSession();
			tblUssdSession.setAni(ani);
			tblUssdSession.setCount(count);
			tblUssdSession.setDatetime(LocalDateTime.now());
			sessionRepo.save(tblUssdSession);
		}
		else
		{
			session.setAni(ani);
			session.setCount(count);
			sessionRepo.save(session);
		}
	}
}
