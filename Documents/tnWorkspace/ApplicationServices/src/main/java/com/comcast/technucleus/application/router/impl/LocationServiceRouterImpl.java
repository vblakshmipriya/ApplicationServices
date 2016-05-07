package com.comcast.technucleus.application.router.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comcast.technucleus.application.router.LocationServiceRouter;
import com.comcast.technucleus.application.services.client.LocationServiceClient;
import com.google.common.base.Throwables;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/******************************************************************************************************
 * File Name : LocationServiceImpl.java 
 * 
 * Description : This class determines the hystrix thread strategy and return empty response when exception
 * 				Occurs
 * 
 *****************************************************************************************************/
@Component
public class LocationServiceRouterImpl implements LocationServiceRouter {
	
	@Autowired
	private LocationServiceClient locationServiceClient;

	private final static Logger log = LoggerFactory
			.getLogger(LocationServiceRouterImpl.class.getName());
	
	
	/**
	 * Method Name : getMarketReferenceId
	 * Description : This method returns the response after executing the hystrix command
	 * 
	 * @param  String 	city
	 * @param  String 	state
	 * @param  String 	zipCode
	 * @return String 	marketId 
	 */
	@HystrixCommand(groupKey="locationService", commandKey ="getMarketReferenceId", fallbackMethod="getMarketReferenceIdFallBack",
			commandProperties={ 
			@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE"),
			@HystrixProperty(name="execution.timeout.enabled", value="false")
	})
	@Override
	public String getMarketReferenceId(String city, String state, String zipCode, String businessUnit) {

		log.debug("getMarketReferenceId: Inside hystrix command - ready to fire the network call with request params-city-{},-state-{},-zipCode-{}",city, state, zipCode);
		String marketId = locationServiceClient.getMarketReferenceId(city, state, zipCode, businessUnit);
		log.debug("getMarketReferenceId: Hystrix command successfully executed and marketId is {}", marketId);
		return marketId;
		
	}
	
	/**
	 * Method Name : getMarketReferenceId
	 * Description : This method is a fallback method when hystrix command execution fails and return an empty response
	 * 
	 * @param  String 	city
	 * @param  String 	state
	 * @param  String 	zipCode
	 * @return String "" 
	 */
	public String getMarketReferenceIdFallBack(String city, String state, String zipCode, String businessUnit, Throwable t) {
		
		String emptyResponse = "";
		if(null != t) {
			log.error("getMarketReferenceIdFallBack: Hystrix command execution failed with error - {}", Throwables.getStackTraceAsString(t));
			return emptyResponse;
		} 
		log.error("getMarketReferenceIdFallBack: Timeout occurred while executing hystrix command");
		return "";
	}
}
