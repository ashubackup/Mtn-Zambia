package com.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Entity.TermsData;
import com.Model.TermsRequest;
import com.Repository.TermsDataRepo;

@Service
public class SendTermsService 
{
	@Autowired
	private TermsDataRepo repo;
	
	public TermsData sendTermsService(TermsRequest request)
	{
		try
		{
			String type=request.getType()==null?"terms":request.getType();
			TermsData data = repo.findByStatusAndTypeAndServiceId("1",type,request.getServiceId());
			return data;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}