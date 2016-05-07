package com.comcast.technucleus.application.services.client;

public interface LocationServiceClient 
{
	public String getMarketReferenceId(String city, String state, String zipCode, String businessUnit);
}
