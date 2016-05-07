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
import com.comcast.technucleus.application.model.internalservice.TechLocationDetails;
import com.comcast.technucleus.application.model.internalservice.TechNukeResponse;
import com.comcast.technucleus.application.properties.Properties;
import com.comcast.technucleus.application.services.client.TechGpsLocationUpdateClient;
import com.google.gson.JsonObject;

/******************************************************************************************************
 * File Name : TechGpsLocationUpdateHandlerImpl.java 
 * 
 * Description : Makes a call to Telematics API and updates the tech's location every minute
 *
 *****************************************************************************************************/
@Component
public class TechGpsLocationUpdateClientmpl implements TechGpsLocationUpdateClient{
	


	private final static Logger log = LoggerFactory
			.getLogger(TechGpsLocationUpdateClientmpl.class.getName());
	
	private static final String TECH_GPS_PROVIDER_NAME = "GPSProviderName";
	private static final String TECH_DEVICEID= "DeviceId";
	private static final String TECH_MOTION_STATUS = "MotionStatus";
	private static final String TECH_LATITUDE = "Latitude";
	private static final String TECH_LONGITUDE = "Longitude";
	private static final String TECH_TIMESTAMP = "TimeStamp";
	private static final String ACCESS_TOKEN = "accessToken";
	private static final String CONTENT_TYPE = "Content-Type";
	
	/** The properties. */
	@Autowired
	private Properties properties;
	
	
//	@Autowired
//	private SessionKeyManager sessionKeyManager;
	
	
	private RestTemplate restTemplate = new RestTemplate();
	

	/**
	 * Method Name : updateTechLocation
	 * Description : This method will update tech location in telematics
	 * 
	 * @param  TechLocationDetails 	techLocationDetailsRequest
	 * @return techLocationUpdateResponse 
	 */	
	@Override
	public TechNukeResponse updateTechLocation(TechLocationDetails techLocationDetailsRequest, String accessToken) {
		
		JsonObject techLocationDetails = buildTechLocationDetailsRequest(techLocationDetailsRequest);	
//		String accessToken = sessionKeyManager.getSessionKey(PropertiesConstants.REDIS_CACHE_KEY_TELEMETIC_CACHE_KEY);
		log.debug("Successfully retreived access token");
		String serviceUrl = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.TELEMATICS_LOCATION_UPDATE_URL);
		log.debug("Tech location update service url: "+serviceUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		headers.set(ACCESS_TOKEN, accessToken);
		HttpEntity<String> entity = new HttpEntity<String>(techLocationDetails.toString(),headers);	
		ResponseEntity<String> response = restTemplate.exchange(serviceUrl, HttpMethod.POST, entity, String.class);
		log.info("Tech Location update response from WFA:"+ response.getBody());
		TechNukeResponse techLocationUpdateResponse = new TechNukeResponse();
		techLocationUpdateResponse.setData(response.getBody());
		techLocationUpdateResponse.setMessage(response.getStatusCode().toString());	
		return techLocationUpdateResponse;
	}
	/**
	 * Builds the tech location details request.
	 *
	 * @param request the request
	 * @return the json object
	 */
	private JsonObject buildTechLocationDetailsRequest(TechLocationDetails request)
	{
		JsonObject techLocationDetailsObject = new JsonObject();
		techLocationDetailsObject.addProperty(TECH_GPS_PROVIDER_NAME, request.getGpsProviderName());
		techLocationDetailsObject.addProperty(TECH_DEVICEID, request.getDeviceId());	
		techLocationDetailsObject.addProperty(TECH_MOTION_STATUS,request.getMotionStatus());
		String latitudeString = request.getLatitude();
		double latitude = Double.parseDouble(latitudeString);
		techLocationDetailsObject.addProperty(TECH_LATITUDE,latitude);
		String longitudeString = request.getLongitude();
		double longitude = Double.parseDouble(longitudeString);
		techLocationDetailsObject.addProperty(TECH_LONGITUDE,longitude);
		techLocationDetailsObject.addProperty(TECH_TIMESTAMP,request.getTimeStamp());
		return techLocationDetailsObject;
	}

}
