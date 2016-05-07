package com.comcast.technucleus.application.util;

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
import com.comcast.technucleus.application.properties.Properties;

/******************************************************************************************************
 * File Name : UserProfileUtil.java
 * 
 * Description : This is the entry point for the User's check within the system
 * all the user and ComcastNow related services are managed here.
 * 
 *****************************************************************************************************/

@Component
public class UserProfileUtil {

	@Autowired
	private Properties properties;

	RestTemplate restTemplate = new RestTemplate();

	/** The Constant log. */
	private final static Logger log = LoggerFactory.getLogger(UserProfileUtil.class.getName());

	public String getComcastNowResponse(String ntId) {

		String url = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.COMCAST_NOW_URL).trim()+ ntId;
		HttpHeaders headers = constructSessionHeader(properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.COMCAST_NOW_KEY).trim());

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		String comcastNowResponse = response.getBody();

		log.debug("JSON Received : {}" + comcastNowResponse);
		log.trace(comcastNowResponse);

		return comcastNowResponse;
	}

	private HttpHeaders constructSessionHeader(String headerPayload) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		headers.set("API-KEY", headerPayload);
		return headers;
	}
}
