package com.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.TblRequestUSSD;
import com.Helper.HandleRequest;
import com.Model.Request;
import com.Model.XMLUtils;
import com.Repository.TblUSSDRequestRepo;
import com.Response.*;

@Service
public class SaveRequest {

	@Autowired
	TblUSSDRequestRepo repo;
	
	@Autowired
	HandleRequest handle;
	
	public String saveRequest(Request request) {
	

		String xmlString=null;
		try {
			
			System.out.println("Request" + request);
			TblRequestUSSD requestUSSD = new TblRequestUSSD();
			requestUSSD.setAni(request.getMsisdn());
			requestUSSD.setDatetime(LocalDateTime.now());
			requestUSSD.setRequest(request.toString());
			repo.save(requestUSSD);

			xmlString=handle.handleRequest(request);
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return xmlString;
	}
}
