package com.comcast.technucleus.application.services.client.impl;

import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comcast.technucleus.application.constants.PropertiesConstants;
import com.comcast.technucleus.application.exception.ApplicationServiceException;
import com.comcast.technucleus.application.properties.Properties;
import com.comcast.technucleus.application.response.data.Eisresultset;
import com.comcast.technucleus.application.response.data.ObjectFactory;
import com.comcast.technucleus.application.services.client.EnterpriseInventoryServiceClient;

import _0.mintekeiswebservices2.TransactionsSoap;

/******************************************************************************************************
 * File Name :  EnterpriseInventoryServiceHandlerImpl.java 
 * 
 * Description : This method will call various service to build the tech buffer details
 * 
 *****************************************************************************************************/

@Component
public class EnterpriseInventoryServiceClientImpl implements EnterpriseInventoryServiceClient 
{
	@Autowired
	private Properties properties;


	/**
	 * Method Name : getTechnicianBuffer
	 * Description :  This method retrieve the Technician Buffer from EIS Service
	 * 
	 * @param  String 			ntID 
	 * @return Eisresultset 
	 */
	@Override
	public Eisresultset getTechnicianBuffer(String ntID)
	{
		TransactionsSoap soapService = getServiceClient();
		String xml = getRequestData(ntID);
		String response = soapService.transaction(properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.CXP_BUFFER_SERVICE_SID), 
				properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.CXP_BUFFER_SERVICE_SAUTH_KEY), 
				xml);
		Eisresultset resutSet = buildResponse(response);
		return resutSet;
	}


	/**
	 * Method Name : buildResponse
	 * Description :  This method convert EIS Service response(Which is a string )to  Eisresultset record
	 * 
	 * @param  String eisRescord
	 * @return Eisresultset 
	 */

	private Eisresultset buildResponse(String eisRescord) {
		Eisresultset response = null;
		try
		{
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			@SuppressWarnings("unchecked")
			JAXBElement<Eisresultset> eisResultSet = (JAXBElement<Eisresultset>) 
			jaxbUnmarshaller.unmarshal(new StringReader(eisRescord));
			response =  eisResultSet.getValue();
		}catch(Exception e)
		{
			throw new ApplicationServiceException("Error while building the service Response" ,e);
		}
		return response;	
	}


	/**
	 * Method Name : getBuffer
	 * Description : THis Method will build request XML for the EIS Service Call
	 * 
	 * @param  String ntID
	 * @return String 
	 */
	private String getRequestData(String ntID) 
	{
		String requestStringPart1 = "<transaction><header><trantype>SIV</trantype><options>InventoryDetailWithBilling</options><fromsiteid>";
		String requestStringPart2 = "</fromsiteid><startid>1</startid></header></transaction>";	
		return requestStringPart1+ntID+requestStringPart2;
	}


	/**
	 * Method Name : getServiceClient
	 * Description : This Method will build the Service Client to invoke Service
	 * 
	 * @return TransactionsSoap 
	 */

	private TransactionsSoap getServiceClient() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(TransactionsSoap.class);
		factory.setAddress(properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.CXP_BUFFER_SERVICE));
		TransactionsSoap soapService = (TransactionsSoap) factory.create();
		return soapService;
	}

}
