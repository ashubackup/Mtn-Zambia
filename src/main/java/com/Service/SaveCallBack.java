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
			if(operationId.equalsIgnoreCase("ACI") || operationId.equalsIgnoreCase("SCI"))
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
			else if(operationId.equalsIgnoreCase("SN") )
			{
				if(result.equalsIgnoreCase("Success"))
				{
					newCallBack.setType("billing");
				}
				else
				{
					newCallBack.setType("FailedBilling");
				}
				
			}
			else if(operationId.equalsIgnoreCase("YR"))
			{
				newCallBack.setType("Ren");
			}
			else if(operationId.equalsIgnoreCase("RR"))
			{
				newCallBack.setType("FailedRen");
			}
			else
			{
				newCallBack.setType("billing");
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
