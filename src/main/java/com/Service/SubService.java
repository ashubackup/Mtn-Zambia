package com.Service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Api.SubApi;
import com.Entity.TblSubscription;
import com.Model.SubRequest;
import com.Repository.TblSubRepo;

@Service
public class SubService 
{
	@Autowired
	TblSubRepo subRepo;
	
	@Autowired
	SubApi subApi;

	public String getSUbRequest(SubRequest subRequest)
	{
		String status="0";
		try
		{
			subRequest.setAni(subRequest.getAni().startsWith("0")?subRequest.getAni().substring("0".length()):subRequest.getAni());
			subRequest.setAni(subRequest.getAni().startsWith("260")?subRequest.getAni().substring("260".length()):subRequest.getAni());
	        
			subRequest.setAni("260"+subRequest.getAni());
			List<TblSubscription> subscription = subRepo.findByAni(subRequest.getAni());
			if(subscription.size()!=0)
			{
//				for(TblSubscription tblSubscription :subscription)
//				{
//					if(tblSubscription.getNext_billed_date()==null)
//					{
//						//already subscribe
//						status="1";
//					}
//					else
//					{
//						
//					}
//				}
				
				//already subscribe
				status="1";
			}
			else
			{
				//hit for sub;
				status="0";
				String ani=subRequest.getAni();
				String pack = subRequest.getPack();
				
				
				//hit for sub
				subApi.hitSubscription(ani, pack);
				
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return status;
	}
	
}
