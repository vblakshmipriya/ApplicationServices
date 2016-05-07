package com.comcast.technucleus.application.router;

/******************************************************************************************************
 * File Name : LocationServiceRouter.java 
 * 
 * Description : This interface is a router whose implementation decides the hystrix thread strategy
 * 
 *****************************************************************************************************/
public interface LocationServiceRouter {
	
	public String getMarketReferenceId(String city, String state, String zipCode, String businessUnit);

}
