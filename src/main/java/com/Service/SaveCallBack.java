package com.Service;

import java.time.LocalDateTime;

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
			System.out.println("CallBack Body"+json);
			TblCallBack newCallBack = new TblCallBack();
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
