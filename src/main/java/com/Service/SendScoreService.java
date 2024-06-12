package com.Service;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Entity.AirtimeData;
import com.Model.SendScoreRequest;
import com.Repository.AirtimeDataRepo;

@Service
public class SendScoreService 
{
	@Autowired
	private AirtimeDataRepo airtimeRepo;
	
	public Map<String,AirtimeData> sendScoreService(SendScoreRequest request)
	{
		Map<String,AirtimeData>result=new HashMap<>();
		try
		{
			System.out.println("Request is "+request);
			
			request.setAni(request.getAni().startsWith("0") ? request.getAni().substring("0".length()) : request.getAni());
			request.setAni(request.getAni().startsWith("260") ? request.getAni().substring("260".length()) : request.getAni());
			request.setAni("260"+request.getAni());
			
			AirtimeData topScorer = airtimeRepo.getTodaysTopScorer(request.getServiceId());
			AirtimeData currentUser = airtimeRepo.getAlreadyAddedUser(request.getAni(),request.getServiceId());
			
			if(currentUser==null)
			{
				AirtimeData emptyUser=new  AirtimeData();
				emptyUser.setScore(0);
				result.put("currentUser",emptyUser);
			}
			else
			{
				result.put("currentUser",currentUser);
			}

			result.put("topScorer",topScorer);

			return result;
		}catch(Exception e)
		{
			e.printStackTrace();
			result.put("topScorer",null);
			result.put("currentUser",null);
			return result;
		}
	}
}