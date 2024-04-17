package com.Service;
import java.util.HashMap;	
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Entity.AirtimeData;
import com.Entity.CashData;
import com.Model.SendWinnersRequest;
import com.Repository.AirtimeDataRepo;
import com.Repository.CashDataRepo;

@Service
public class SendWinnersService 
{
	@Autowired
	private AirtimeDataRepo airtimeRepo;
	
	@Autowired
	private CashDataRepo cashRepo;
	
	public Map<String,List> sendWinnersService(SendWinnersRequest request)
	{
		Map<String,List>result=new HashMap<>();
		try
		{
			List<AirtimeData> airtimeWinners = airtimeRepo.getAirtimeWinners(request.getServiceId());
			List<CashData> cashWinners = cashRepo.getCashWinners(request.getServiceId());
			
			result.put("airtimeWinners",airtimeWinners);
			result.put("cashWinners", cashWinners);
			return result;
		}catch(Exception e)
		{
			e.printStackTrace();
			result.put("airtimeWinners",null);
			result.put("cashWinners",null);
			return result;
		}
	}
}