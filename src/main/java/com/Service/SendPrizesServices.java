package com.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Entity.Prizes;
import com.Repository.PrizesRepo;

@Service
public class SendPrizesServices 
{
	@Autowired
	private PrizesRepo repo;
	
	public Map<String,List<Prizes>> sendPrizesServices()
	{
		Map<String,List<Prizes>>result=new HashMap<>();
		try
		{
			List<Prizes> airtimePrizes = repo.findByTypeAndStatus("airtime","1");
			List<Prizes> cashPrizes = repo.findByTypeAndStatus("cash","1");
			
			result.put("airtimePrizes",airtimePrizes);
			result.put("cashPrizes",cashPrizes);
			return result;
		}catch(Exception e)
		{
			e.printStackTrace();
			result.put("airtimePrizes",null);
			result.put("cashPrizes",null);
			return result;
		}
	}
}