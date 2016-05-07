package com.comcast.technucleus.application.services.client.impl;

import java.lang.reflect.Type;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.comcast.technucleus.application.constants.PropertiesConstants;
import com.comcast.technucleus.application.exception.ApplicationServiceException;
import com.comcast.technucleus.application.exception.ApplicationServiceException.SEErrorCode;
import com.comcast.technucleus.application.properties.Properties;
import com.comcast.technucleus.application.response.data.TechBufferDevice;
import com.comcast.technucleus.application.services.client.TechBufferClient;
import com.comcast.technucleus.application.util.TechNucleusErrorProperties;
import com.comcast.technucleus.application.util.TechNucleusUtil;
import com.comcast.technucleus.application.util.WorkOrderUtils;
import com.google.common.base.Throwables;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/******************************************************************************************************
 * File Name : WFATechBufferHandlerImpl.java 
 * 
 * Description :THis Class Will handle WFA TECH Buffer 
 *
 *****************************************************************************************************/

@Component
public class TechBufferClientImpl implements TechBufferClient
{
	public static final String TECHNICIAN_EQUIPMENT_LIST =  "TechnicianEquipmentList";
	public static final String SERIAL_NUMBER = "SerialNumber";
	public static final String EQUIPMENT_TYPE_DESC = "EquipmentTypeDesc" ;		
	public static final String  EQUIPMENT_MODEL_DESC = "EquipmentModelDesc" ;
	public static final String  EQUIPMENT_MODEL_CD = "EquipmentModelCd";
	public static final String  EQUIPMENT_TYPE_CD = "EquipmentTypeCd";
	public static final String  OWNER_DESC = "OwnerDesc";
	
	public static final String REQUEST_BUSINESS_UNIT =  "BusinessUnit";
	public static final String REQUEST_WORK_ORDER_NUM = "WorkOrderNum";
	public static final String REQUEST_WFX_DISPATCH_LOGIN = "WFXDispatchLogin";
	public static final String REQUEST_SERIAL_NUM = "SerialNum" ;		
	public static final String REQUEST_EQUIPMENT_CD= "EquipTypeCd" ;
	public static final String REQUEST_OWNER_CD = "OwnerCd";
	public static final String REQUEST_ACTION_CD= "ActionCd";
	public static final String  REQUEST_EQUIPMENT_STATUS_CD = "EquipStatusCd";
	public static final String  REQUEST_EQUIPMENT_MODEL_CD= "EquipModelCd";
	public static final String  REQUEST_JOB_EQUIPMENT_LIST = "JobEquipmentList";
	
	

	@Autowired
	private Properties properties;

	@Autowired
	private WorkOrderUtils workOrderUtils; 
	
	@Autowired
	private TechNucleusErrorProperties errorProperties;

	/** The Constant log. */
	private final static Logger log = LoggerFactory.getLogger(TechBufferClientImpl.class.getName());
	
	RestTemplate restTemplate = new RestTemplate();	


	/**
	 * Method Name : getBuffer
	 * Description : This Method retrieve TechBuffer From WFA 
	 * 
	 * 
	 * @param    String     					techID
	 * @param    String 						fulfillmentCenter
	 * @return   List<WFATechBufferDevice>    
	 * 
	 * */

	@Override
	public List<TechBufferDevice> getBuffer(String techId, String fullFillmentCenter) 
	{
		String wfaResponse =	getWFABufferService(techId,fullFillmentCenter);
		List<TechBufferDevice> response = buildBufferResponse(wfaResponse);
		return response;
	}


	/**
	 * Method Name : buildBufferResponse
	 * Description : This Method build List<WFATechBufferDevice> from response of WFA
	 * 
	 * 
	 * @param    String     					wfaResponse
	 * @return   List<WFATechBufferDevice>    
	 * */
	private List<TechBufferDevice> buildBufferResponse(String wfaResponse) 
	{
		JsonObject workOrderByAccountNum = (new JsonParser()).parse(wfaResponse).getAsJsonObject();
		JsonArray equipmentList = (JsonArray)workOrderByAccountNum.get(TECHNICIAN_EQUIPMENT_LIST);
		Gson gson = new Gson();
		Type arrayList = new TypeToken<List<TechBufferDevice>>(){}.getType();
		List<TechBufferDevice> myModelList = gson.fromJson(equipmentList, arrayList);
		return myModelList;
	}


	/**
	 * Method Name : getWFABufferService
	 * Description : This Method WFA Buffer Service Response
	 * 
	 * 
	 * @param    String 					techId
	 * @param    String 					fullFillmentCenter
	 * @return   List<WFATechBufferDevice>    
	 * */
	private String getWFABufferService(String techId, String fullFillmentCenter) {
		String errorResponseBodyAsString = "";
		String httpStatusCode = "";
		String workOrderListByTechNumResponse = null;
		String url = null;
		try{
			String authorization = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.AUTHORIZATION_KEY);
			String source = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.SOURCE_NAME);
			String sourceId = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.WORKORDER_SOURCE_ID);
			String accessToken = workOrderUtils.getAccessTokenJson(authorization, sourceId);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			headers.set("Source", source);
			headers.set("accessToken", accessToken);
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(properties
					.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.WFA_TECH_BUFFER_URL).trim());
			builder.queryParam("FulfillmentCenter", fullFillmentCenter);
			builder.queryParam("TechNum", techId);		
			url = builder.build().encode().toUriString();
			HttpEntity<?> entity = new HttpEntity<>(headers);
			HttpEntity<String> restResponse = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			log.debug("WFA GetWorkOrderBy TechNum Response : {}" + restResponse);
			workOrderListByTechNumResponse = restResponse.getBody();
		}
		catch(HttpStatusCodeException e)
		{
			errorResponseBodyAsString = e.getResponseBodyAsString();
			httpStatusCode = e.getStatusCode().toString();
			log.error("getWFABufferService: Exception while retriving device with request {} and response{}" , url, e.getResponseBodyAsString() , e);
			throw new ApplicationServiceException(SEErrorCode.SERVICE_WFA_RETRIVE_TECH_BUFF_HTTP_CLIENT_ERROR, errorResponseBodyAsString, httpStatusCode, e);
		}
		catch(Exception e)
		{
			errorResponseBodyAsString = e.getMessage();
			log.error("getWFABufferService: Exception while adding device with request {}" ,url,e);
			throw new ApplicationServiceException(SEErrorCode.SERVICE_WFA_RETRIVE_TECH_BUFF_UNKNOWN_EXCEPTION,errorResponseBodyAsString, 
						HttpStatus.INTERNAL_SERVER_ERROR.toString(),e);	
		}
		return workOrderListByTechNumResponse;
	}

	/**
	 * Method Name : activateDevice
	 * Description : This Method will start the process of activation of the Device
	 *               1. Retrieve WorkOrder
	 *               2. Retrieve Device Details
	 *               3. Invoke Activation Process
	 * 
	 * 
	 * @param    String     		techID
	 * @param    String 			fulfillmentCenter
	 * @param	 String 			workOrderNumber
	 * @param	 String 			sequenceNo
	 * @param	 String 			scheduleDate 
	 * @param	 String 			deviceSerialNumber
	 * @param    TechBufferDevice device
	 * 
	 * */

	@Override
	public void addDevice(String techID, String fulfillmentCenter, String workOrderNumber, String sequenceNo,
			String scheduleDate, String deviceSerialNumber, TechBufferDevice device,String businessUnit) 
	{
		String errorResponseBodyAsString = "";
		String httpStatusCode = "";
		String request = null;
		try
		{
			String authorization = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.AUTHORIZATION_KEY);
			String source = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.SOURCE_NAME);
			String sourceId = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.WORKORDER_SOURCE_ID);
			String accessToken = workOrderUtils.getAccessTokenJson(authorization, sourceId);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			headers.set("Source", source);
			headers.set("accessToken", accessToken);
			headers.set("Authorization", authorization);
			String propURL = properties
					.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.WFA_TECH_DEVICE_ADD);
			String endPointURL = propURL.replaceAll("<WorkOrderNumber>" ,workOrderNumber);
			request = getRequest( techID,  fulfillmentCenter,  workOrderNumber,  sequenceNo,
					scheduleDate,  deviceSerialNumber, device,businessUnit);
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endPointURL);
			String url = builder.build().encode().toUriString();
			HttpEntity<String> entity = new HttpEntity<String>(request,headers);
			HttpEntity<String> restResponse  = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			String workOrderListByTechNumResponse = restResponse.getBody();
			log.info("Sucessfully Added : {} to {}" + deviceSerialNumber ,workOrderNumber);
			log.debug("WFA GetWorkOrderBy TechNum Response : {}" + workOrderListByTechNumResponse);
		}catch(HttpStatusCodeException e)
		{
			errorResponseBodyAsString = e.getResponseBodyAsString();
			httpStatusCode = e.getStatusCode().toString();
			JsonObject errorJson = TechNucleusUtil.convertStringToJSON(errorResponseBodyAsString);
			JsonElement responseJsonElement = errorJson.get("Response");
			String errorMessage = responseJsonElement.getAsString();
			log.error("addDevice:Exception while adding device with request {} and response{}" ,request,errorResponseBodyAsString,e);
			throw new ApplicationServiceException(SEErrorCode.SERVICE_WFA_ADD_DEVICE_HTTP_CLIENT_ERROR,errorMessage, httpStatusCode, e);	
			
		}catch(Exception e)
		{
			String errorCode = SEErrorCode.SERVICE_WFA_ADD_DEVICE_UNKNOWN_EXCEPTION.toString();
			String errorMessage = errorProperties.getProperty(errorCode);
			log.error("addDevice:Exception while adding device with request {}" ,request,Throwables.getStackTraceAsString(e));
			throw new ApplicationServiceException(SEErrorCode.SERVICE_WFA_ADD_DEVICE_UNKNOWN_EXCEPTION,errorMessage, HttpStatus.INTERNAL_SERVER_ERROR.toString(), e);	
		}	
	}
	
	/**
	 * Method Name : getRequest
	 * Description : This Method will build Request For Adding Device
	 * 
	 * 
	 * @param    String     		techID
	 * @param    String 			fulfillmentCenter
	 * @param	 String 			workOrderNumber
	 * @param	 String 			sequenceNo
	 * @param	 String 			scheduleDate 
	 * @param	 String 			deviceSerialNumber
	 * @param    TechBufferDevice device
	 * @return   String   
	 * */
	private String getRequest(String techID, String fulfillmentCenter, String workOrderNumber, String sequenceNo,
			String scheduleDate, String deviceSerialNumber, TechBufferDevice device,String businessUnit) {
		JsonObject request = new JsonObject();
		request.addProperty(REQUEST_BUSINESS_UNIT, businessUnit);
		request.addProperty(REQUEST_WORK_ORDER_NUM, workOrderNumber);
		request.addProperty(REQUEST_WFX_DISPATCH_LOGIN, techID);
		JsonArray deviceArray = new JsonArray();
		JsonObject addDevicedetail = new JsonObject();
		addDevicedetail.addProperty(REQUEST_SERIAL_NUM, deviceSerialNumber);
		addDevicedetail.addProperty(REQUEST_EQUIPMENT_CD, device.getEquipmentTypeCd());
		addDevicedetail.addProperty(REQUEST_OWNER_CD, "R");
		addDevicedetail.addProperty(REQUEST_ACTION_CD, "ADD");
		addDevicedetail.addProperty(REQUEST_EQUIPMENT_STATUS_CD, "H");
		addDevicedetail.addProperty(REQUEST_EQUIPMENT_MODEL_CD, device.getEquipmentModelCd());
		deviceArray.add(addDevicedetail);
		request.add(REQUEST_JOB_EQUIPMENT_LIST, deviceArray);
		return request.toString();
	}


}
