package com.Service;

import java.time.LocalDateTime;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.TblCallBack;
import com.Repository.TblRepo;

@Service
public class SaveCallBack 
{
	
	@Autowired
	TblRepo tblRepo;

	public String saveCallback(String json)
	{
		try
		{
			TblCallBack newCallBack = new TblCallBack();
			JSONObject jsonObject = new JSONObject(json);
			String operationId=jsonObject.get("operationId").toString();
			String result=jsonObject.get("result").toString();
			System.out.println("OPerationId"+operationId);
			if(operationId.equalsIgnoreCase("ACI") || operationId.equalsIgnoreCase("SCI") || operationId.equalsIgnoreCase("PCI") || operationId.equalsIgnoreCase("GCI"))
			{
				if(result.equalsIgnoreCase("You deactivate the service successfully."))
				{
					newCallBack.setType("unsub");
				}
				else 
				{
					newCallBack.setType("FailedUnsub");
				}
				
			}
			else if(operationId.equalsIgnoreCase("SN") || operationId.equalsIgnoreCase("PN"))
			{
				if(result.equalsIgnoreCase("Success"))
				{
					newCallBack.setType("billing");
				}
				else
				{
					newCallBack.setType("subFailed");
				}
				
			}
			//renewal success
			else if(operationId.equalsIgnoreCase("YR") || operationId.equalsIgnoreCase("RR") || operationId.equalsIgnoreCase("GR") )
			{
				newCallBack.setType("Ren");
			}
			//renewal failed.
			else if(operationId.equalsIgnoreCase("YF") || operationId.equalsIgnoreCase("GF"))
			{
				newCallBack.setType("FailedRen");
			}
			
			//delete number whose not charged i 5 days
			else if(operationId.equalsIgnoreCase("PD"))
			{
				newCallBack.setType("expiredRen");
			}
			else if(operationId.equalsIgnoreCase("SP"))
			{
				newCallBack.setType("subFailed");
			}
			else
			{
				newCallBack.setType("subFailed");
			}
				
			
			System.out.println("CallBack Body"+json);
			
			newCallBack.setCallback(json);
			newCallBack.setDatetime(LocalDateTime.now());
			newCallBack.setStatus("0");
			
			tblRepo.save(newCallBack);
			return "Saved";
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "Failed to Saved";
		}
	}
}
