package com.Service;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Entity.AirtimeData;
import com.Entity.CashData;
import com.Repository.AirtimeDataRepo;
import com.Repository.CashDataRepo;

@Service
public class AddToCashDataService 
{
	@Autowired
	private AirtimeDataRepo airtimeRepo;
	
	@Autowired
	private CashDataRepo cashRepo;
	
	public void addToCashDataService() //i.e Add to Monthly Data 
	{
		try
		{
			List<AirtimeData> monthlyData = airtimeRepo.getMonthlyData();//First Getting Data
			
			for(AirtimeData winners:monthlyData)
			{
				//Now Match for already exist in cash_table
				
				CashData already = cashRepo.matchAlready(winners.getAni(),winners.getServiceId());
				if(already==null)
				{
					System.out.println("New");
					addNewData(winners);
				}
				else
				{
					//Already Exist - so Adding Score
					System.out.println("Already");
					
					Integer sum=already.getScore()+winners.getScore();
					already.setScore(sum);
					already.setUpdateDateTime(LocalDateTime.now());
					
					cashRepo.save(already); //Saving
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//Method to Add Data in cash table
	public void addNewData(AirtimeData winners)
	{
		try
		{
			//So New - Add New Entry 
			CashData newCash=new CashData();
			newCash.setAni(winners.getAni());
			newCash.setDateTime(LocalDateTime.now().minusDays(1));
			newCash.setUpdateDateTime(LocalDateTime.now());
			newCash.setGame(winners.getGame());
			newCash.setGameId(winners.getGameId());
			newCash.setName(winners.getName());
			newCash.setScore(winners.getScore());
			newCash.setService(winners.getService());	
			newCash.setServiceId(winners.getServiceId());
			newCash.setStatus("0");
			
			cashRepo.save(newCash); //Saving
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}