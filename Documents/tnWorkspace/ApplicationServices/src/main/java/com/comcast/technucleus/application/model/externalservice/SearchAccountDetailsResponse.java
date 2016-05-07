package com.comcast.technucleus.application.model.externalservice;

/******************************************************************************************************
 * File Name : SearchAccountDetailsResponse.java 
 * 
 * Description :This class is response for Search Account Details
 *
 *****************************************************************************************************/

public class SearchAccountDetailsResponse 
{
	private String requestID;
	
	private String response;

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
