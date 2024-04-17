//package com.Service;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.Entity.TblRequestUSSD;
//import com.Helper.HandleRequest;
//import com.Model.Request;
//import com.Model.XMLUtils;
//import com.Repository.TblUSSDRequestRepo;
//import com.Response.FreeFlow;
//import com.Response.Param;
//import com.Response.Parameter;
//import com.Response.Response;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.dataformat.xml.XmlMapper;
//
//@Service
//public class SaveRequest {
//	@Autowired
//	TblUSSDRequestRepo repo;
//
//	@Autowired
//	HandleRequest handle;
//
//	public String saveRequest(Request request) {
//		String xmlString = null;
//
//		try {
//			System.out.println("Request" + request);
//			TblRequestUSSD requestUSSD = new TblRequestUSSD();
//			requestUSSD.setAni(request.getMsisdn());
//			requestUSSD.setDatetime(LocalDateTime.now());
//			requestUSSD.setRequest(request.toString());
//			repo.save(requestUSSD);
//
//			handle.handleRequest(request);
//			Response response = new Response();
//
//			FreeFlow freeFlow = new FreeFlow();
//			
//			Param parm = new Param();
//			parm.setName("rajan");
//			parm.setValue("sharma");
//			List data = new ArrayList<>();
//			data.add(parm);
//			Parameter parameter = new Parameter();
//			parameter.setParam(data);
//			freeFlow.setParameters(parameter);
//			freeFlow.setFreeflowCharging("Y/N");
//			freeFlow.setFreeflowChargingAmount(2.50);
//			freeFlow.setFreeflowState("FC/FB");
//			response.setFreeflow(freeFlow);
//			response.setApplicationResponse("hai how are you doing ??");
//			response.setMsisdn("919611909091");
//			response.setAppDrivenMenuCode("menuCode");
//
//			XmlMapper xmlMapper = new XmlMapper();
//			xmlString = xmlMapper.writeValueAsString(response);
//
//			System.out.println("Respone" + xmlString);
//		}
//
//		catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return xmlString;
//	}
//}

package com.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.Model.Request;
import com.Response.*;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
public class SaveRequest {

	public String saveRequest(Request request) {
		String xmlString = null;

		try {
			Response response = new Response();
			response.setMsisdn("919611909091");
			response.setApplicationResponse("hai how are you doing ??");
			response.setAppDrivenMenuCode("menuCode");

			FreeFlow freeFlow = new FreeFlow();
			freeFlow.setFreeflowState("FC/FB");
			freeFlow.setFreeflowCharging("Y/N");
			freeFlow.setFreeflowChargingAmount(2.50);

			// Add parameters
			Parameter parameter = new Parameter();
			Map<String, Param> parms = new HashMap<String, Param>();
			Param tesst = new Param();
			tesst.setName("demo");
			tesst.setValue("testing");
			parms.put("name", tesst);
			parameter.setParams(parms);
			freeFlow.setParameters(parameter);
			response.setFreeflow(freeFlow);

			// Convert Response object to XML string
			XmlMapper xmlMapper = new XmlMapper();
			xmlString = xmlMapper.writeValueAsString(response);

			System.out.println("Response: " + xmlString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return xmlString;
	}
}
