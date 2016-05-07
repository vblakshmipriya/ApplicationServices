package com.comcast.technucleus.application.services.client.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.comcast.technucleus.application.constants.PropertiesConstants;
import com.comcast.technucleus.application.dao.UserProfileMongoDAO;
import com.comcast.technucleus.application.exception.ApplicationClientException;
import com.comcast.technucleus.application.exception.ApplicationClientException.CEErrorCode;
import com.comcast.technucleus.application.model.internalservice.TechDetails;
import com.comcast.technucleus.application.properties.Properties;
import com.comcast.technucleus.application.services.client.UserClient;
import com.comcast.technucleus.application.util.UserProfileUtil;
import com.comcast.technucleus.application.util.WorkOrderUtils;
import com.google.gson.JsonObject;
import com.mongodb.DBObject;

/******************************************************************************************************
 * File Name : UserClientImpl.java
 * 
 * Description : All the user related external calls are handled here
 * 
 *****************************************************************************************************/
@Component
public class UserClientImpl implements UserClient {

	private static final Logger log = LoggerFactory.getLogger(UserClientImpl.class.getName());

	private static final String BUSINESS_UNIT = "BusinessUnit";
	private static final String WORK_ORDER_NUM = "WorkOrderNum";
	private static final String WFX_TECH_LOGIN = "WFXTechLogin";
	private static final String JOB = "Job";
	private static final String JOB_NUM = "JobNum";
	private static final String JOB_TYPE_CD = "JobTypeCd";
	private static final String SCHEDULED_DATE = "ScheduleDate";
	private static final String TECHNICIAN_NUMBER = "TechnicianNum";
	private static final String DISPATCHER_STATUS_CODE = "DispatcherStatusCd";
	private static final String JOB_START_DATE_TIME = "JobStartDateTime";
	private static final String TECH_NUM = "TechNum";
	private static final String FULFILLMENT_CENTER = "FulfillmentCenter";
	private static final String TECH_ID = "techID";
	private static final String STATUS_CD = "StatusCd";
	private static final String ENROUTE = "ENROUTE";
	private static final String OFFLINE = "OFFLINE";
	private static final String END_OF_DAY = "EOD";
	private static final String TECHNICIAN_NUM = "TechnicianNum";
	private static final String USER_PROFILE_FFC_KEY = "fulfillmentCenter";

	private static final String ACCESS_TOKEN = "accessToken";
	private static final String SOURCE = "Source";
	private static final String CONTENT_TYPE = "Content-Type";

	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private UserProfileMongoDAO userProfileMongoDAO;

	@Autowired
	private WorkOrderUtils workOrderUtils;

	@Autowired
	private Properties properties;

	@Autowired
	private UserProfileUtil userProfileUtil;

	@Override
	public String getTechCurrentStatus(String ntId) {

		String source = properties
				.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.SOURCE_NAME);
		String authorization = properties
				.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.AUTHORIZATION_KEY);

		// RestTemplate restTemplate = new RestTemplate();
		DBObject userProfileObj = userProfileMongoDAO.getUserProfileByNtIdFromDb(ntId);
		String techId = userProfileObj.get(TECH_ID).toString();
		String fulfillmentCenter = userProfileObj.get(USER_PROFILE_FFC_KEY).toString();

		String accessToken = workOrderUtils.getAccessTokenJson(authorization, source);
		log.debug("getTechCurrentStatus: accessToken retrieved successfully {}");

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(properties
				.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.WFA_TECH_STATUS_URL));
		builder.queryParam(FULFILLMENT_CENTER, fulfillmentCenter);
		builder.queryParam(TECHNICIAN_NUM, techId);
		String url = builder.build().encode().toUriString();
		log.debug("getTechCurrentStatus: Url built succesfully {}" + url);

		HttpEntity<String> entity = buildHeaders(null, accessToken);
		log.debug("getTechCurrentStatus: headers set succesfully {}");

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		String techStatusResponse = response.getBody();

		return techStatusResponse;
	}

	@Override
	public String updateTechStatus(TechDetails techDetails, String ntId) {

		String responseBodyAsString = "";
		String httpStatusCode = "";
		String source = properties
				.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.SOURCE_NAME);
		String authorization = properties
				.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.AUTHORIZATION_KEY);

		// RestTemplate restTemplate = new RestTemplate();

		// gets the userProfile information from the database
		DBObject userProfileObj = userProfileMongoDAO.getUserProfileByNtIdFromDb(ntId);
		String fulfillmentCenter = userProfileObj.get("fulfillmentCenter").toString();

		// getting AccessToken
		String accessToken = workOrderUtils.getAccessTokenJson(authorization, source);
		log.debug("updateTechStatus: accessToken retrieved successfully {}");

		// Building the URI
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(properties
				.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.WFA_TECH_STATUS_URL));
		builder.queryParam(FULFILLMENT_CENTER, fulfillmentCenter);
		builder.queryParam(TECH_NUM, techDetails.getTechnicianNum());
		String url = builder.build().encode().toUriString();
		log.debug("updateTechStatus: Url built succesfully {}" + url);

		// Building RequestBody
		JsonObject requestBody = buildTechStatusUpdateRequestBody(techDetails, fulfillmentCenter);

		// Building Headers
		HttpEntity<String> entity = buildHeaders(requestBody.toString(), accessToken);
		log.debug(
				"updateTechStatus: updating tech status update for the ntid, accountnumber, businessunit,dispatch code-{}-{}-{}-{} ",
				ntId, techDetails.getAccountNumber(), techDetails.getBusinessUnit(),
				techDetails.getDispatcherStatusCd());
		// External call
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			if (null != response) {
				log.debug(
						"updateTechStatus: successful tech status update for the ntid, accountnumber, businessunit,dispatch code-{}-{}-{}-{} ",
						ntId, techDetails.getAccountNumber(), techDetails.getBusinessUnit(),
						techDetails.getDispatcherStatusCd());
				return response.getBody();
			} else {
				log.error(
						"updateTechStatus: null response received for tech status update for the ntid, accountnumber, businessunit,dispatch code-{}-{}-{}-{} ",
						ntId, techDetails.getAccountNumber(), techDetails.getBusinessUnit(),
						techDetails.getDispatcherStatusCd());
				throw new ApplicationClientException(CEErrorCode.TECH_STATUS_UPDATE_EMTPTY_RESPONSE_RECEIVED,
						HttpStatus.INTERNAL_SERVER_ERROR.toString());
			}
		} catch (HttpStatusCodeException e) {
			responseBodyAsString = e.getResponseBodyAsString();
			httpStatusCode = e.getStatusCode().toString();
			log.error("updateTechStatus: HttpStatusCodeException - error updating the tech status-{} with status - {}",
					responseBodyAsString, httpStatusCode);
			throw new ApplicationClientException(CEErrorCode.TECH_STATUS_UPDATE_FAILED, responseBodyAsString, httpStatusCode, e);
		} catch (Exception e) {
			responseBodyAsString = e.getMessage();
			log.error("updateTechStatus: HttpStatusCodeException - error updating the tech status-{} with status - {}",
					responseBodyAsString, httpStatusCode);
			throw new ApplicationClientException(CEErrorCode.TECH_STATUS_UPDATE_UNKNOWN_ERROR, responseBodyAsString,
					httpStatusCode, e);
		}
	}

	@Override
	public String updateWorkorderStatusToOnJob(TechDetails techDetails, String ntId) {

		log.debug("updateWorkorderStatusToOnJob: updating the tech status for technician to be ONJOB with ntId-{}",
				ntId);
		String responseBodyAsString = "";
		String httpStatusCode = "";
		String source = properties
				.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.SOURCE_NAME);
		String authorization = properties
				.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.AUTHORIZATION_KEY);

		String accessToken = workOrderUtils.getAccessTokenJson(authorization, source);
		log.debug("updateTechStatus: accessToken retrieved successfully {}");

		// RestTemplate restTemplate = new RestTemplate();

		final String uri = properties.getProp(
				PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.WFA_TECH_STATUS_UPDATE_URL);
		log.debug("updateTechStatus: uri from db-{}", uri);
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("workOrderNumber", techDetails.getWorkOrderNum());

		// Building RequestBody
		JsonObject requestBody = buildOnJOBResuestBody(techDetails);

		// Building HttpHeaders
		HttpEntity<String> entity = buildHeaders(requestBody.toString(), accessToken);
		log.debug("getTechCurrentStatus: headers set succesfully {}");

		// External call
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class, pathVariables);
			if (null != response) {
				return response.getBody();
			} else {
				log.error(
						"updateTechStatus: null response received for tech status update for the ntid, accountnumber, businessunit,dispatch code-{}-{}-{}-{} ",
						ntId, techDetails.getAccountNumber(), techDetails.getBusinessUnit(),
						techDetails.getDispatcherStatusCd());
				throw new ApplicationClientException(CEErrorCode.TECH_STATUS_UPDATE_ON_JOB_EMTPTY_RESPONSE_RECEIVED,
						HttpStatus.INTERNAL_SERVER_ERROR.toString());
			}
		} catch (HttpStatusCodeException e) {
			responseBodyAsString = e.getResponseBodyAsString();
			httpStatusCode = e.getStatusCode().toString();
			log.error("updateTechStatus: HttpStatusCodeException - error updating the tech status-{} with status - {}",
					responseBodyAsString, httpStatusCode);
			throw new ApplicationClientException(CEErrorCode.TECH_STATUS_UPDATE_ON_JOB_FAILED, responseBodyAsString,
					httpStatusCode, e);
		} catch (Exception e) {
			responseBodyAsString = e.getMessage();
			log.error("updateTechStatus: HttpStatusCodeException - error updating the tech status-{} with status - {}",
					responseBodyAsString, httpStatusCode);
			throw new ApplicationClientException(CEErrorCode.TECH_STATUS_UPDATE_ON_JOB_UNKNOWN_ERROR, responseBodyAsString,
					httpStatusCode, e);
		}
	}

	@Override
	public String getComcastNowData(String ntId) {
		String comcastNowResponse = userProfileUtil.getComcastNowResponse(ntId);
		log.debug("JSON Received : {}", comcastNowResponse);
		return comcastNowResponse;
	}

	private JsonObject buildOnJOBResuestBody(TechDetails techDetails) {

		JsonObject job = new JsonObject();
		job.addProperty(JOB_NUM, techDetails.getJobNum());
		job.addProperty(JOB_TYPE_CD, techDetails.getJobTypeCd());
		job.addProperty(SCHEDULED_DATE, techDetails.getScheduleDate());
		job.addProperty(TECHNICIAN_NUMBER, techDetails.getTechnicianNum());
		job.addProperty(DISPATCHER_STATUS_CODE, techDetails.getDispatcherStatusCd());
		job.addProperty(JOB_START_DATE_TIME, techDetails.getJobStartDateTime());

		JsonObject requestBody = new JsonObject();
		requestBody.addProperty(BUSINESS_UNIT, techDetails.getBusinessUnit());
		requestBody.addProperty(WORK_ORDER_NUM, techDetails.getWorkOrderNum());
		requestBody.addProperty(WFX_TECH_LOGIN, techDetails.getTechnicianNum());
		requestBody.add(JOB, job);

		return requestBody;
	}

	private JsonObject buildTechStatusUpdateRequestBody(TechDetails techDetails, String fulfillmentCenter) {

		JsonObject obj = new JsonObject();

		if (techDetails.getTechStatus().equalsIgnoreCase(ENROUTE)) {
			obj.addProperty(FULFILLMENT_CENTER, fulfillmentCenter);
			obj.addProperty(WORK_ORDER_NUM, techDetails.getWorkOrderNum());
			obj.addProperty(STATUS_CD, techDetails.getTechStatus());
			obj.addProperty(TECHNICIAN_NUMBER, techDetails.getTechnicianNum());
		} else if (techDetails.getTechStatus().equalsIgnoreCase(OFFLINE)) {
			obj.addProperty(FULFILLMENT_CENTER, fulfillmentCenter);
			obj.addProperty(STATUS_CD, END_OF_DAY);
			obj.addProperty(TECHNICIAN_NUMBER, techDetails.getTechnicianNum());
		} else {
			obj.addProperty(FULFILLMENT_CENTER, fulfillmentCenter);
			obj.addProperty(STATUS_CD, techDetails.getTechStatus());
			obj.addProperty(TECHNICIAN_NUMBER, techDetails.getTechnicianNum());
		}
		return obj;
	}

	private HttpEntity<String> buildHeaders(String requestBody, String accessToken) {
		String source = properties
				.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.SOURCE_NAME);

		HttpHeaders headers = new HttpHeaders();
		headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		headers.set(SOURCE, source);
		headers.set(ACCESS_TOKEN, accessToken);

		HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);

		return entity;

	}
}
