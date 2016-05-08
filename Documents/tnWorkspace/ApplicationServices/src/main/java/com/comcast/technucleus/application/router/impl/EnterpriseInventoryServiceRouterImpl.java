package com.comcast.technucleus.application.router.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comcast.technucleus.application.exception.ApplicationServiceException;
import com.comcast.technucleus.application.exception.ApplicationServiceException.SEErrorCode;
import com.comcast.technucleus.application.model.Eisresultset;
import com.comcast.technucleus.application.router.EnterpriseInventoryServiceRouter;
import com.comcast.technucleus.application.services.client.EnterpriseInventoryServiceClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/******************************************************************************************************
 * File Name : EnterpriseInventoryServiceRouterImpl.java
 * 
 * Description : This add the Hystrix metrix to the EIS service Call
 * 
 *****************************************************************************************************/

@Component
public class EnterpriseInventoryServiceRouterImpl implements EnterpriseInventoryServiceRouter {

	@Autowired
	private EnterpriseInventoryServiceClient eisHandler;

	private final static Logger log = LoggerFactory.getLogger(EnterpriseInventoryServiceRouterImpl.class.getName());

	/**
	 * Method Name : getTechnicianBuffer Description : This method retrieve the
	 * Technician Buffer from EIS Service
	 * 
	 * @param String
	 *            ntID
	 * @return Eisresultset
	 */

	@HystrixCommand(groupKey = "maestro", commandKey = "getEISTechBuffer", fallbackMethod = "getEISTechBufferFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public Eisresultset getTechnicianBuffer(String ntID) {
		log.debug("getTechnicianBuffer: EIS technitian buffer Call Initiated");
		Eisresultset result = eisHandler.getTechnicianBuffer(ntID);
		log.debug("getTechnicianBuffer: Successfully reterived technitian Buffer Call");
		return result;
	}

	/**
	 * Method Name : getEISTechBufferFallBack Description : This method will
	 * invoke from Hystrix if there is any error while calling
	 * getTechnicianBuffer method
	 * 
	 * @param String
	 *            ntID
	 * @return Eisresultset
	 */
	public Eisresultset getEISTechBufferFallBack(String ntID) {
		log.error("getEISTechBufferFallBack: error while reteriving TechBuffer Error",
				SEErrorCode.SERVICE_FALL_BACK_EIS_TECH_BUFFER.toString());
		throw new ApplicationServiceException(SEErrorCode.SERVICE_FALL_BACK_EIS_TECH_BUFFER,
				"getEISTechBufferFallBack - Error retrieving reteriving TechBuffer", null, null);
	}
}
