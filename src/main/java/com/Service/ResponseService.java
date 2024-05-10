package com.Service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.springframework.stereotype.Service;

import com.Model.XMLUtils;
import com.Response.FreeFlow;
import com.Response.Param;
import com.Response.Parameter;
import com.Response.Response;

@Service
public class ResponseService 
{

	
	public String createResponse(String msisdn,String pack1,String pack2)
	{
		String xmlString = null;
		try
		{
			Response response = new Response();
			response.setMsisdn(msisdn);
			response.setApplicationResponse("hai how are you doing ??");
			response.setAppDrivenMenuCode("menuCode");

			FreeFlow freeFlow = new FreeFlow();
			freeFlow.setFreeflowState("FC/FB");
			freeFlow.setFreeflowCharging("Y/N");
			freeFlow.setFreeflowChargingAmount(2.50);

			
			// Add parameters
            Parameter parameter = new Parameter();
            Param params = new Param();
            params.setName("1");
            params.setValue(pack1);
            
            Param param1 = new Param();
            
            param1.setName("2");
            param1.setValue(pack2);
            
//            Param param2 = new Param();
//            
//            param2.setName("3");
//            param2.setValue("Monthly");
//            
            Param param3 = new Param();
            
            param3.setName("3");
            param3.setValue("Unsubscription");
            
            List<Param> paramList = new ArrayList();
            
            paramList.add(params);
            paramList.add(param1);
            paramList.add(param3);
            parameter.setParams(paramList);
			freeFlow.setParameters(parameter);
			response.setFreeflow(freeFlow);

			// Convert Response object to XML string
            JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            xmlString = XMLUtils.marshalToString(response, marshaller);
			System.out.println("Response:" + xmlString);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return xmlString;
	}
}
