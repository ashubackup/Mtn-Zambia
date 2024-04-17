package com.Service;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.TblSubscription;
import com.Model.LoginModal;
import com.Repository.TblSubRepo;

@Service
public class LogInService 
{
	
	@Autowired
	TblSubRepo subRepo;

	public String getRequest(LoginModal loginModal)
	{
		System.out.println("Control here...");
		String status="0";
		try
		{
	        LocalDateTime zimbabweTime = LocalDateTime.now(ZoneId.of("Africa/Harare"));
	        loginModal.setAni(loginModal.getAni().startsWith("0")?loginModal.getAni().substring("0".length()):loginModal.getAni());
	        loginModal.setAni(loginModal.getAni().startsWith("260")?loginModal.getAni().substring("260".length()):loginModal.getAni());
	        loginModal.setAni("260"+loginModal.getAni());
	        System.out.println("ani"+loginModal.getAni());
			List<TblSubscription> sub = subRepo.findByAni(loginModal.getAni());
			if(sub.size()!=0)
			{
				System.out.println("SubScription"+sub);
				for(TblSubscription subscription :sub)
				{
					if(subscription.getNext_billed_date()==null)
					{
						
						
						//billing failed;
						status="1";
					}
					else if(subscription.getNext_billed_date().isAfter(zimbabweTime))
					{
						//give access
						status="2";
					}
					else
					{
						//billing failed.
						status="1";
					}
						
				}
				
			}
			else	
			{
				//not sub
				status="3";
			}
				
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			status="0";
		}
		return status;
	}
}
