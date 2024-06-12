package com.Service;
import java.util.ArrayList;
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
public class SendWinnersNewService 
{
	@Autowired
	private AirtimeDataRepo airtimeRepo;
	
	@Autowired
	private CashDataRepo cashRepo;
	
	public List<Map<String,String>> sendWinnersNewService(SendWinnersRequest request)
	{
		try
		{
			//Getting List's from DB
			List<AirtimeData> airtimeWinners = airtimeRepo.getAirtimeWinners(request.getServiceId());
			List<CashData> cashWinners = cashRepo.getCashWinners(request.getServiceId());
			
			System.out.println("Airtime Winners List Size is "+airtimeWinners.size());
			System.out.println("Cash Winners List Size is "+cashWinners.size());
						
			//Adding Data to Single String Lists
			List<String> airtimeScore=new ArrayList<>();
			for(AirtimeData airtime:airtimeWinners)
			{
//				System.out.println("airtimeList"+airtime);
				airtimeScore.add(airtime.getScore().toString());
//				airtimeScore.add(airtime);
			}
			
			List<String> cashScore=new ArrayList<>();
			for(CashData cash:cashWinners)
			{
//				System.out.println("cashDataList"+cash);
				cashScore.add(cash.getScore().toString());
//				cashScore.add(cash);
			}
			
			//Managing empty data for length 10
			int pending=0;
			if(airtimeScore.size()!=10)
			{
				pending=10-airtimeScore.size();
				System.out.println("Pending in airitme is "+pending);

				for(int i=0;i<pending;i++)
				{
					airtimeScore.add("0");
				}
			}
			
			if(cashScore.size()!=10)
			{
				pending=10-cashScore.size();
				System.out.println("Pending in cash is "+pending);

				for(int i=0;i<pending;i++)
				{
					cashScore.add("0");
				}
			}
			
			//Printing Data
//			System.out.println("Airtime Data");
//			for(int i=0;i<airtimeScore.size();i++)
//			{
//				System.out.println(airtimeScore.get(i));
//			}
//			
//			System.out.println("Cash Data");
//			for(int i=0;i<cashScore.size();i++)
//			{
//				System.out.println(cashScore.get(i));
//			}
			
			//Building Final List of Map
			List<Map<String,String>>list=new ArrayList<>();
			for(int i=0;i<airtimeScore.size();i++)
			{
				Map<String,String>map=new HashMap<>();
				for(int j=0;j<cashScore.size();j++)
				{
					map.put("airtime",airtimeScore.get(i));
					map.put("cash",cashScore.get(i));
					break;
				}
				list.add(map);
			}
			
//			for(Map<String,String> map:list)
//			{
//				System.out.println(map);
//			}
			return list;
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}