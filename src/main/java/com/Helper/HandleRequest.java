package com.Helper;

import org.springframework.stereotype.Service;

import com.Model.Request;

@Service
public class HandleRequest 
{

	public void handleRequest(Request request)
	{
		try
		{
			System.out.println("Handle Request");
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
