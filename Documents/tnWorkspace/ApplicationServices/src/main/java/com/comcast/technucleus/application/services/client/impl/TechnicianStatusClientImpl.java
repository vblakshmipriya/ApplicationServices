package com.comcast.technucleus.application.services.client.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.comcast.technucleus.application.constants.PropertiesConstants;
import com.comcast.technucleus.application.model.TechDetails;
import com.comcast.technucleus.application.model.TechNukeResponse;
import com.comcast.technucleus.application.properties.Properties;
import com.comcast.technucleus.application.services.client.TechnicianStatusClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/******************************************************************************************************
 * File Name : TechnicianHandlerImpl.java 
 * 
 * Description : Makes a call to WFA API and updates the tech's status
 *
 *****************************************************************************************************/
@Component
public class TechnicianStatusClientImpl implements TechnicianStatusClient{
	
	/** The Constant log. */
	private final static Logger log = LoggerFactory
			.getLogger(TechnicianStatusClientImpl.class.getName());
	
	private static final String TECH_DETAILS_BUSINESS_UNIT = "BusinessUnit";
	private static final String TECH_DETAILS_WORK_ORDER_NUM = "WorkOrderNum";
	private static final String TECH_DETAILS_WFX_TECH_LOGIN = "WFXTechLogin";
	private static final String TECH_DETAILS_JOB = "Job";
	private static final String TECH_DETAILS_JOB_START_DATE_TIME = "JobStartDateTime";
	private static final String TECH_DETAILS_TECH_NUMBER = "TechnicianNum";
	private static final String ACCESS_TOKEN = "accessToken";
	private static final String SOURCE = "Source";
	private static final String CONTENT_TYPE = "Content-Type";	
	
	
	@Autowired
	private Properties properties;
	
//	@Autowired
//	private SessionKeyManager sessionkeyManager;
	/** The rest template. */
	public RestTemplate restTemplate = new RestTemplate();
	
	/**
	 * Method Name : techStatusUpdate
	 * Description : This method will update tech status
	 * 
	 * @param  TechDetails 	techDetails
	 * @param  String 		workOrderNumber
	 * @return techStatusUpdateResponse 
	 */	
	@Override
	public TechNukeResponse techStatusUpdate(TechDetails techDetails, String workOrderNumber, String accessToken) 
	{	
	//	RestTemplate restTemplate = new RestTemplate();
		JsonObject techStatusUpdateRequest = buildTechDetailsRequest(techDetails);
		log.debug("Successfully built service request: "+ techStatusUpdateRequest.toString());	
		
		//String accessToken = sessionkeyManager.getSessionKey(PropertiesConstants.REDIS_CACHE_KEY_WFA_CACHE_KEY); 
		log.debug("Successfully retreived accessToken");
		
		String serviceUrl = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.WFA_TECH_STATUS_UPDATE_URL);
		log.debug("Update Tech status Service Url: "+serviceUrl);
		String source = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.SOURCE_NAME);
		HttpHeaders headers = new HttpHeaders();
		headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		headers.set(ACCESS_TOKEN, accessToken);
		headers.set(SOURCE, source);
		HttpEntity<String> entity = new HttpEntity<String>(techStatusUpdateRequest.toString(), headers);
		ResponseEntity<String>	response = restTemplate.exchange(serviceUrl, HttpMethod.POST, entity, String.class,workOrderNumber);	
		JsonObject serviceResponseObject = (new JsonParser()).parse(response.getBody()).getAsJsonObject();
		log.debug("Tech Status update response from WFA: "+ serviceResponseObject);
		TechNukeResponse techStatusUpdateResponse = new TechNukeResponse();
		techStatusUpdateResponse.setData(serviceResponseObject);
		techStatusUpdateResponse.setMessage(response.getStatusCode().toString());
		return techStatusUpdateResponse;
	}
	
	
	/**
	 * Method Name : buildTechDetailsRequest
	 * Description : This method build request For the WFA request
	 * 
	 * @param  TechDetails 	techDetails
	 * @return JsonObject 
	 */	
	private JsonObject buildTechDetailsRequest(TechDetails request) {
		
		JsonObject techDetailsObject = new JsonObject();
		techDetailsObject.addProperty(TECH_DETAILS_BUSINESS_UNIT, request.getBusinessUnit());
		techDetailsObject.addProperty(TECH_DETAILS_WORK_ORDER_NUM, request.getWorkOrderNum());	
		//TODO : TechId will be retrieved from the Session once we start getting techId and region associated to the NT-Id
		techDetailsObject.addProperty(TECH_DETAILS_WFX_TECH_LOGIN,request.getwFXTechLogin());			
		JsonObject job = new JsonObject();
		techDetailsObject.add(TECH_DETAILS_JOB, job);
		job.addProperty(TECH_DETAILS_JOB_START_DATE_TIME, request.getJobStartDateTime());
		job.addProperty(TECH_DETAILS_TECH_NUMBER, request.getTechnicianNum());
		techDetailsObject.add(TECH_DETAILS_JOB, job);
		return techDetailsObject;
	}
}
