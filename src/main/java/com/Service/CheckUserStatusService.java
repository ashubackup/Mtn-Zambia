package com.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.TblSubscription;
import com.Repository.TblSubRepo;

@Service
public class CheckUserStatusService 
{
	
	@Autowired
	TblSubRepo subRepo;

	public String getRequestForCheckUserStaus(String msisdn)
	{
		String status ="1";
		try
		{
			LocalDateTime zimbabweTime = LocalDateTime.now(ZoneId.of("Africa/Harare"));
			List<TblSubscription> sub = subRepo.findByAni(msisdn);
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
		}
		return status;
	}
}
