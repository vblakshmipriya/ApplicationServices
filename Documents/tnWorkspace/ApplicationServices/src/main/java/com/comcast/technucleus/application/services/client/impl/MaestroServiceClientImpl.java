package com.comcast.technucleus.application.services.client.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth.common.signature.SharedConsumerSecret;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.comcast.technucleus.application.constants.PropertiesConstants;
import com.comcast.technucleus.application.exception.ApplicationClientException;
import com.comcast.technucleus.application.exception.ApplicationClientException.CEErrorCode;
import com.comcast.technucleus.application.properties.Properties;
import com.comcast.technucleus.application.services.client.MaestroServiceClient;

@Component
public class MaestroServiceClientImpl implements MaestroServiceClient{

	@Autowired
	private Properties properties;

	private final static Logger log = LoggerFactory.getLogger(MaestroServiceClientImpl.class.getName());

	public RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());

	private static final String MAESTRO_USER_PAGE = "requestLogin";


	/**
	 *@MethodName  getJsessionId
	 *@Description  This method makes a call to the maestro service
	 * 
	 * @param String		sessionKey 
	 * @return 
	 */
	@Override
	public String getJsessionId() {

		String errorResponseBody = "";
		String httpStatusCode = "";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.TEXT_PLAIN_VALUE);
		String url = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.MAESTRO_CODE_BIG_URL);
		log.debug("getJsessionId: url from DB-{}", url);
		String maestroUserRole =  properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES +PropertiesConstants.MAESTRO_KB_USER_ROLE);
		String maestroView =  properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.MAESTRO_KB_VIEW);
		log.debug("getJsessionId: maestroUserRole -{}, maestroview-{}", maestroUserRole, maestroView);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

		String ntUserID = SecurityContextHolder.getContext().getAuthentication().getName();
		log.debug("getJsessionId: ntUserID retrievied from session -{}", ntUserID);

		builder.queryParam("page", MAESTRO_USER_PAGE);
		builder.queryParam("roles", "Global Admin");
		URI uri = builder.build().encode().toUri();

		HttpEntity<?> entity = new HttpEntity<>(headers);
		log.debug("getJsessionId: Will be calling maestro now with {}", uri);
		OAuthRestTemplate oAuthRestTemplate = getOAuthRestTemplate();
		log.debug("getJsessionId: oAuthRestTemplate successfully c");
		ResponseEntity<String> response = null;
		try {

			response = oAuthRestTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
			if( null != response) {

				//retrieves jSessionId form the cookie
				HttpHeaders responseHeader = response.getHeaders();
				List<String> list = responseHeader.get("Set-Cookie");
				String actualString = list.get(0);
				List<String> listOfStringTokens = getNecessaryStringToken(actualString, ";");
				actualString = listOfStringTokens.get(0);
				listOfStringTokens = getNecessaryStringToken(actualString, "=");
				String sessionKey = listOfStringTokens.get(1);
				log.debug("getJsessionId: session token retrieved from response: "+sessionKey);
				return sessionKey;
			} else {
				log.error("getJsessionId: null response received for maestro session token");
				throw new ApplicationClientException(CEErrorCode.MAESTRO_SERVICE_SESSION_TOKEN_NULL_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR.toString());
			}
		} catch(HttpStatusCodeException e) {
			errorResponseBody = e.getResponseBodyAsString();
			httpStatusCode = e.getStatusCode().toString();
			log.error("getJsessionId: exception thrown with response-{} and status-{}",errorResponseBody, httpStatusCode);
			throw new ApplicationClientException(CEErrorCode.MAESTRO_SERVICE_SESSION_TOKEN_ERROR, errorResponseBody,httpStatusCode, e);
		} catch(Exception e) {
			errorResponseBody = e.getMessage();
			log.error("getJsessionId: exception thrown with response-{} and status-{}",errorResponseBody);
			throw new ApplicationClientException(CEErrorCode.MAESTRO_SERVICE_SESSION_TOKEN_UNKNOWN_SERVER_ERROR, errorResponseBody,HttpStatus.INTERNAL_SERVER_ERROR.toString(), e);
		}
	}



	private List<String> getNecessaryStringToken(String actualString, String delimiter) {

		List<String> listOfStringTokens = new ArrayList<String>();
		StringTokenizer stringTokenizer = new StringTokenizer(actualString, delimiter);

		while (stringTokenizer.hasMoreTokens()) {

			listOfStringTokens.add((String) stringTokenizer.nextToken());
		}
		return listOfStringTokens;
	}

	@Override
	public String searchMaestroKnowledgebase(String page, String searchString,Boolean jsonResponse, String jSessionId, String user) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.TEXT_PLAIN_VALUE);
		String url = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.MAESTRO_KNOWLEDGEBASE_URL)
				.trim();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		builder.queryParam("page", page);
		builder.queryParam("searchString", searchString);
		builder.queryParam("jsonResponse", jsonResponse);
		builder.queryParam("jsessionid", jSessionId);
		builder.queryParam("user", user);
		URI uri = builder.build().encode().toUri();

		HttpEntity<?> entity = new HttpEntity<>(headers);
		HttpEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

		String maestroKnowledgebaseResponse = response.getBody();

		return maestroKnowledgebaseResponse;
	}

	@Override
	public String searchMaestroKnowledgebasePagination(String page,
			String searchString, Boolean jsonResponse, String accessToken, 
			String user, String docVer, String type, String searchid,
			String region) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.TEXT_PLAIN_VALUE);
		String url = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.MAESTRO_KNOWLEDGEBASE_URL).trim();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		builder.queryParam("page", page);
		builder.queryParam("searchString", searchString);
		builder.queryParam("jsonResponse", jsonResponse);
		builder.queryParam("jsessionid", accessToken);
		builder.queryParam("user", user);
		builder.queryParam("docVer", docVer);
		builder.queryParam("type", type);
		builder.queryParam("searchid", searchid);
		builder.queryParam("region", region);
		URI uri = builder.build().encode().toUri();

		HttpEntity<?> entity = new HttpEntity<>(headers);
		HttpEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

		String maestroKnowledgebasePaginationResponse = response.getBody();

		return maestroKnowledgebasePaginationResponse;
	}

	//For TestCases
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public JSONObject getDeviceImage(String deviceModel, String maestroSessionToken) { 

		String URI = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.MAESTRO_KNOWLEDGEBASE_URL)
				.trim();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cookie", "JSESSIONID=" + maestroSessionToken);
		headers.add("Content-Type", "text/plain");
		HttpEntity<?> entity = new HttpEntity<>(headers);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URI);
		builder.queryParam("page", "diagnosticThumbnailJSON");
		builder.queryParam("make", "make");
		builder.queryParam("model", deviceModel);
		builder.queryParam("custType", "R");

		URI uri = builder.build().encode().toUri();
		HttpEntity<String> response = restTemplate.exchange(uri,
				HttpMethod.GET, entity, String.class);

		JSONObject deviceImageResponse = new JSONObject();
		if( null != response) {
			log.debug("getDeviceImage: response recieved for model-{}", response);
			deviceImageResponse = new JSONObject(response.getBody());
		}
		return deviceImageResponse;
	}

	@Override
	public String getAlerts( String maestroSessionToken) {
		
		String errorResponseBody = "";
		String httpStatusCode = "";
		
		String urlFromDB = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.MAESTRO_CODE_BIG_URL);
		log.debug("getContentForSearchCriteria: url from DB-{}",urlFromDB);
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		log.debug("getContentForSearchCriteria: userName from session-{}",userName);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cookie", "JSESSIONID=" + maestroSessionToken);
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlFromDB);

		builder.queryParam("page","contentListJSON");
		builder.queryParam("channel","ALERTS");
		builder.queryParam("sortDir", "ascending");
		//builder.queryParam("jsessionid", maestroSessionToken);
		
		String url = builder.build().encode().toUriString();
		OAuthRestTemplate oAuthRestTemplate = getOAuthRestTemplate();
		ResponseEntity<String> response = null;
		
		try {
			response = oAuthRestTemplate.exchange(url, HttpMethod.GET, entity, String.class);

			if(null != response && !StringUtils.isEmpty(response.getBody())) {
				return response.getBody();
			} else {
				log.error("getAlerts: null response received retrieving the maestro response");
				throw new ApplicationClientException(CEErrorCode.MAESTRO_SERVICE_EMPTY_RESPONSE,HttpStatus.INTERNAL_SERVER_ERROR.toString());
			}

		} catch (HttpStatusCodeException e) {
			errorResponseBody = e.getResponseBodyAsString();
			httpStatusCode = e.getStatusCode().toString();
			log.error("getAlerts: exception thrown with response - {} and status-{}", errorResponseBody,httpStatusCode);
			throw new ApplicationClientException(CEErrorCode.MAESTRO_SERVICE_ERROR, errorResponseBody, httpStatusCode, e);

		} catch (Exception ex) {
			errorResponseBody = ex.getMessage();
			log.error("getAlerts: exception thrown with response-{}", errorResponseBody);
			throw new ApplicationClientException(CEErrorCode.MAESTRO_SERVICE_UNKNOWN_SERVER_ERROR, errorResponseBody,HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex);
		}
	}

	private ClientHttpRequestFactory clientHttpRequestFactory() {
		log.debug("Creating Connection for Maestro...");
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(100);
		factory.setConnectTimeout(100);
		return factory;
	}  



	@Override
	public String getDocument(String docId, String maestroSessionToken) {
		String errorResponseBody = "";
		String httpStatusCode = "";

		String urlFromDB = properties
				.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.MAESTRO_CODE_BIG_URL);
		log.debug("getDocument: url from DB-{}", urlFromDB);
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		log.debug("getDocument: userName from session-{}", userName);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cookie", "JSESSIONID=" + maestroSessionToken);
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlFromDB);

		HttpHeaders header = new HttpHeaders();
		headers.add("Cookie", "JSESSIONID=" + maestroSessionToken);
		header.add("Content-Type", "text/plain");

		builder.queryParam("page", "viewDocumentContent");
		builder.queryParam("documentID", docId);

		String url = builder.build().encode().toUriString();
		OAuthRestTemplate oAuthRestTemplate = getOAuthRestTemplate();
		ResponseEntity<String> response = null;

		try {
			response = oAuthRestTemplate.exchange(url, HttpMethod.GET, entity, String.class);

			if (null != response && !StringUtils.isEmpty(response.getBody())) {
				return response.getBody();
			} else {
				log.error("getDocument: null response received retrieving the maestro response");
				throw new ApplicationClientException(CEErrorCode.MAESTRO_SERVICE_EMPTY_RESPONSE,
						HttpStatus.INTERNAL_SERVER_ERROR.toString());
			}

		} catch (HttpStatusCodeException e) {
			errorResponseBody = e.getResponseBodyAsString();
			httpStatusCode = e.getStatusCode().toString();
			log.error("getDocument: exception thrown with response - {} and status-{}", errorResponseBody,
					httpStatusCode);
			throw new ApplicationClientException(CEErrorCode.MAESTRO_SERVICE_ERROR, errorResponseBody, httpStatusCode, e);

		} catch (Exception ex) {
			errorResponseBody = ex.getMessage();
			log.error("getDocument: exception thrown with response-{}", errorResponseBody);
			throw new ApplicationClientException(CEErrorCode.MAESTRO_SERVICE_UNKNOWN_SERVER_ERROR, errorResponseBody,
					HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex);
		}
	}


	@Override
	public String getRelatedDocDetails(String docId, String maestroSessionToken) {
		String errorResponseBody = "";
		String httpStatusCode = "";

		String urlFromDB = properties
				.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.MAESTRO_CODE_BIG_URL);
		log.debug("getRelatedDocDetails: url from DB-{}", urlFromDB);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlFromDB);

		HttpHeaders header = new HttpHeaders();
		header.add("Cookie", "JSESSIONID=" + maestroSessionToken);
		header.add("Content-Type", "text/plain");

		HttpEntity<?> entity = new HttpEntity<>(header);
		builder.queryParam("page", "getRelatedContentPortletJSON");
		builder.queryParam("documentID", docId);

		String url = builder.build().encode().toUriString();
		OAuthRestTemplate oAuthRestTemplate = getOAuthRestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = oAuthRestTemplate.exchange(url, HttpMethod.GET, entity, String.class);

			if (null != response && !StringUtils.isEmpty(response.getBody())) {
				return response.getBody();
			} else {
				log.error("getRelatedDocDetails: null response received retrieving the maestro response");
				throw new ApplicationClientException(CEErrorCode.MAESTRO_SERVICE_EMPTY_RESPONSE,
						HttpStatus.INTERNAL_SERVER_ERROR.toString());
			}

		} catch (HttpStatusCodeException e) {
			errorResponseBody = e.getResponseBodyAsString();
			httpStatusCode = e.getStatusCode().toString();
			log.error("getRelatedDocDetails: exception thrown with response - {} and status-{}", errorResponseBody,
					httpStatusCode);
			throw new ApplicationClientException(CEErrorCode.MAESTRO_SERVICE_ERROR, errorResponseBody, httpStatusCode, e);

		} catch (Exception ex) {
			errorResponseBody = ex.getMessage();
			log.error("getRelatedDocDetails: exception thrown with response-{}", errorResponseBody);
			throw new ApplicationClientException(CEErrorCode.MAESTRO_SERVICE_UNKNOWN_SERVER_ERROR, errorResponseBody,
					HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex);
		}

	}



	@Override
	public String getContentForSearchCriteria(String searchString, String maestroSessionToken) {

		String errorResponseBody = "";
		String httpStatusCode = "";
		String urlFromDB = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.MAESTRO_CODE_BIG_URL);
		log.debug("getContentForSearchCriteria: url from DB-{}",urlFromDB);
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		log.debug("getContentForSearchCriteria: userName from session-{}",userName);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Accept",MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlFromDB);
		builder.queryParam("page", "searchAnswers");
		builder.queryParam("searchString", searchString);
		builder.queryParam("jsonResponse", "true");
		builder.queryParam("jsessionid", maestroSessionToken);
		builder.queryParam("user", userName);

		String url = builder.build().encode().toUriString();
		OAuthRestTemplate oAuthRestTemplate = getOAuthRestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = oAuthRestTemplate.exchange(url, HttpMethod.GET, entity, String.class);

			if(null != response && !StringUtils.isEmpty(response.getBody())) {
				return response.getBody();
			} else {
				log.error("getContentForSearchCriteria: null response received retrieving the maestro response");
				throw new ApplicationClientException(CEErrorCode.MAESTRO_SERVICE_EMPTY_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR.toString());
			}
		} catch(HttpStatusCodeException e) {
			errorResponseBody = e.getResponseBodyAsString();
			httpStatusCode = e.getStatusCode().toString();
			log.error("getContentForSearchCriteria: exception thrown with response - {} and status-{}",errorResponseBody,httpStatusCode);
			throw new ApplicationClientException(CEErrorCode.MAESTRO_SERVICE_ERROR, errorResponseBody,httpStatusCode, e);
		} catch(Exception e) {
			errorResponseBody = e.getMessage();
			log.error("getContentForSearchCriteria: exception thrown with response-{}",errorResponseBody);
			throw new ApplicationClientException(CEErrorCode.MAESTRO_SERVICE_UNKNOWN_SERVER_ERROR, errorResponseBody,HttpStatus.INTERNAL_SERVER_ERROR.toString(), e);
		}
	}

	public OAuthRestTemplate getOAuthRestTemplate() {

		String consumerSecret = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.MAESTRO_CODE_BIG_SECRET_KEY);
		String consumerKey = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.MAESTRO_CODE_BIG_CONSUMER_KEY);
		SharedConsumerSecret secret = new SharedConsumerSecretImpl(consumerSecret);
		BaseProtectedResourceDetails protectedResourceDetails =  new BaseProtectedResourceDetails();
		protectedResourceDetails.setConsumerKey(consumerKey);
		protectedResourceDetails.setSharedSecret(secret);

		return new OAuthRestTemplate(protectedResourceDetails);
	}


	public void getMaestroSessionToken(String response) {

		//This logic is to retrieve the jSessionId form the Response body
		JSONObject result = XML.toJSONObject(response);
		JSONObject inQuiraResponse = result.getJSONObject("InQuiraResponse");
		JSONObject parameters = inQuiraResponse.getJSONObject("Parameters");
		JSONObject parameter = parameters.getJSONObject("Parameter");
		String jSessionIdRaw = parameter.optString("Value");
		StringTokenizer stringTokenizer = new StringTokenizer(jSessionIdRaw,
				"!");
		String temp = "";
		List<String> sessionIdSplitList = new ArrayList<String>();

		while (stringTokenizer.hasMoreElements()) {

			temp = (String) stringTokenizer.nextElement();
			sessionIdSplitList.add(temp);

		}
		String jSessionIdPart1 = sessionIdSplitList.get(0);
		String jSessionIdPart2 = sessionIdSplitList.get(1);
		String jSessionId = jSessionIdPart1 + "!" + jSessionIdPart2;
	}
}
