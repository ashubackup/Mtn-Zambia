package com.Service;
import java.time.LocalDateTime;		
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Entity.AirtimeData;
import com.Entity.CashData;
import com.Entity.OperatorInfo;
import com.Entity.ServiceInfo;
import com.Entity.UserScore;
import com.Model.SaveScoreRequest;
import com.Repository.AirtimeDataRepo;
import com.Repository.CashDataRepo;
import com.Repository.OperatorInfoRepo;
import com.Repository.ServiceInfoRepo;
import com.Repository.UserScoreRepo;

@Service
public class SaveScoreService 
{
	@Autowired
	private UserScoreRepo userScoreRepo;
	
	@Autowired
	private ServiceInfoRepo serviceInfoRepo;
	
	@Autowired
	private AirtimeDataRepo airtimeRepo;
	
	@Autowired
	private OperatorInfoRepo operatorInfoRepo;
	
	@Autowired
	private CashDataRepo cashDataRepo;
	
	//Method to get request score data from endpoint
	public void saveScoreService(SaveScoreRequest request)
	{
		try
		{
			System.out.println("Request is "+request);
			
			//First of all swapping values
			//gameId to serviceId & serviceId to gameId
			if(!request.getAni().isEmpty())
			{
			String gameId = request.getGameId();
			String serviceId = request.getServiceId();
			request.setAni(request.getAni().startsWith("0") ? request.getAni().substring("0".length()) : request.getAni());
			request.setAni(request.getAni().startsWith("260") ? request.getAni().substring("260".length()) : request.getAni());
			request.setAni("260"+request.getAni());
			request.setGameId(serviceId);
			request.setServiceId(gameId);
			
			if(request.getScore()==null)
			{
				System.out.println("Score was null so set to 0");
				request.setScore("0");
			}
			
			//Getting Service Name
			String serviceName = getServiceName(request);
			
			saveScore(request,serviceName);
			checkUser(request,serviceName);
			}
			else
			{
				System.out.println("No need to save request because get ani is null from save Score Game ");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//Method to save user score in table - user_score
	public void saveScore(SaveScoreRequest request,String serviceName)
	{
		try
		{
			String gameName="Game";
			ServiceInfo info = serviceInfoRepo.findByGameidAndStatus(request.getGameId(),"1");
			if(info!=null)
			{
				gameName=info.getGame();
			}
			
			UserScore score=new UserScore();
			
			score.setAni(request.getAni());
			score.setDateTime(LocalDateTime.now());
			score.setGame(gameName);
			score.setGameId(request.getGameId());
			score.setStatus("0");
			score.setScore(Integer.parseInt(request.getScore()));
			score.setServiceId(request.getServiceId());
			score.setService(serviceName);
			
			userScoreRepo.save(score);
			System.out.println("Data Save to user table");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//Method to save data in table - airtime_data
	public void checkUser(SaveScoreRequest request,String serviceName)
	{
		try
		{
			AirtimeData user = airtimeRepo.getAlreadyAddedUser(request.getAni(),"1");
			System.out.println("user is "+user);
			
			if(user==null)
			{
				//New User - Add Data
				System.out.println("New Entry");
				AirtimeData data=new AirtimeData();
				data.setAni(request.getAni());
				data.setDateTime(LocalDateTime.now());
				data.setStatus("0");
				data.setScore(Integer.parseInt(request.getScore()));
				data.setService(serviceName);
				data.setServiceId(request.getServiceId());
				
				airtimeRepo.save(data);
				System.out.println("New Entry: Data Save to table - airtime_data");
				cashData(request,serviceName);
				
			}
			else 
			{
				//Already Exist in Today's Date so add new score to old score
				System.out.println("Old Entry");
				Integer newScore=user.getScore()+Integer.parseInt(request.getScore());
				user.setScore(newScore);
				airtimeRepo.save(user);
				System.out.println("Old Entry: Data Save to table - airtime_data");
				cashData(request,serviceName);
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//Method to Give Service Name
	public String getServiceName(SaveScoreRequest request)
	{
		try
		{
			OperatorInfo operatorInfo = operatorInfoRepo.findByStatusAndServiceId("1",request.getServiceId());
			return operatorInfo.getService();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	//Add into cash Data
	public void cashData(SaveScoreRequest request,String serviceName)
	{
		try
		{
			CashData cashData = cashDataRepo.matchAlready(request.getAni(), "1");
			if(cashData==null)
			{
				CashData addNewDataCash = new CashData();
				addNewDataCash.setAni(request.getAni());
				addNewDataCash.setDateTime(LocalDateTime.now());
				addNewDataCash.setGame(serviceName);
				addNewDataCash.setName(serviceName);
				addNewDataCash.setScore(Integer.parseInt(request.getScore()));
				addNewDataCash.setService(serviceName);
				addNewDataCash.setStatus("0");
				addNewDataCash.setServiceId("1");
				addNewDataCash.setUpdateDateTime(LocalDateTime.now());
				cashDataRepo.save(addNewDataCash);
			}
			else
			{
				cashData.setScore(cashData.getScore()+Integer.parseInt(request.getScore()));
				cashData.setUpdateDateTime(LocalDateTime.now());
				cashDataRepo.save(cashData);
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}