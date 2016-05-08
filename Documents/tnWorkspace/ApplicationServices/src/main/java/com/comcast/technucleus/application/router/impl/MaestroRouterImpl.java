package com.comcast.technucleus.application.router.impl;

import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.comcast.technucleus.application.exception.ApplicationClientException;
import com.comcast.technucleus.application.exception.ApplicationTimeOutException;
import com.comcast.technucleus.application.exception.ApplicationClientException.CEErrorCode;
import com.comcast.technucleus.application.exception.ApplicationServiceException.SEErrorCode;
import com.comcast.technucleus.application.exception.ApplicationTimeOutException.TOErrorCode;
import com.comcast.technucleus.application.router.MaestroRouter;
import com.comcast.technucleus.application.services.client.MaestroServiceClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;

@Component
public class MaestroRouterImpl implements MaestroRouter {

	@Autowired
	private MaestroServiceClient maestroClient;

	private final static Logger log = LoggerFactory.getLogger(MaestroRouterImpl.class.getName());

	/**
	 *@MethodName  getMaestroSessionToken
	 *@Description  This method determines the hystrix thread strategy for the network call to Maestro service
	 *				to retrieve session token
	 * 
	 * @param 
	 * @return 
	 */
	@HystrixCommand(groupKey="maestro", commandKey ="getMaestroSessionToken", fallbackMethod="getMaestroSessionTokenFallBack",
			commandProperties={ 
			@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE"),
			@HystrixProperty(name="execution.timeout.enabled", value="false")
	})
	public String getMaestroSessionToken() {

		log.debug("getMaestroSessionToken: Maestro Hystrix call initiated");
		String sessionToken = maestroClient.getJsessionId();
		log.debug("getMaestroSessionToken: sessionToken-{}",sessionToken);
		return sessionToken;
	}

	/**
	 *@MethodName  getMaestroSessionToken
	 *@Description  This method captures all the exceptions returned from a maestro service call
	 * 
	 * @param 
	 * @return 
	 */
	public String getMaestroSessionTokenFallBack(Throwable t)
	{	
		log.error("getMaestroSessionTokenFallBack: In fall back retreiving maestro session token and settin an empty string");
		return "";
//		log.error("getMaestroSessionTokenFallBack: In fall back retreiving maestro session token");
//		if(t == null) {
//			log.error("getMaestroSessionTokenFallBack: Time Out occurred-{}",t);
//			throw new TimeOutException(TOErrorCode.MAESTRO_SESSION_TOKEN_TIME_OUT_OCCURRED, HttpStatus.GATEWAY_TIMEOUT.toString(), t);
//		}else if( t instanceof ClientException)
//		{   log.error("getMaestroSessionTokenFallBack: service exeption occurred");
//		throw (ClientException) t;
//		}else {
//			log.error("submitDeviceHitFallBack: unknown exception caught in fallback {}",t);
//			throw new ClientException(CEErrorCode.MAESTRO_SERVICE_SESSION_TOKEN_UNKNOWN_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), t);
//		}
	}

	@HystrixCommand(groupKey="maestro", commandKey ="getDeviceImage", fallbackMethod="getDeviceImagesFallBack",
			commandProperties={ 
			@HystrixProperty(name="execution.timeout.enabled", value="true"),
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="1000")},
			threadPoolProperties = {
			@HystrixProperty(name = "coreSize", value = "20"),
			@HystrixProperty(name = "maxQueueSize", value = "200"),
			@HystrixProperty(name = "keepAliveTimeMinutes", value = "10"),
			@HystrixProperty(name = "queueSizeRejectionThreshold", value = "190")
	})
	public Future<JSONObject> getDeviceImage(String deviceModel, String maestroSessionToken) {
		return new AsyncResult<JSONObject>() {
			@Override 
			public JSONObject invoke() {
				log.debug("getDeviceImage: deviceimage response for model-{}",deviceModel);
				JSONObject response = maestroClient.getDeviceImage(deviceModel, maestroSessionToken);
				log.debug("getDeviceImage: successful device image response received for model-{}",deviceModel);
				return response;
			}
		};

	}

	public JSONObject getDeviceImagesFallBack(String deviceModel, String maestroSessionToken)
	{	
		log.error("getDeviceImagesFallBack: Error code- {} - error response from maestro for model {} - ", 
				SEErrorCode.SERVICE_FALL_BACK_MAESTRO_DEVICE_IMAGE_RESPONSE_ERROR.toString(), deviceModel);
		/*	throw new FallBackException("getDeviceImagesFallBack - Error retrieving maestro response for device image", 
				FallBackErrorCode.FALL_BACK_MAESTRO_DEVICE_IMAGE_RESPONSE_ERROR.toString());	*/

		return new JSONObject();
	}



	@HystrixCommand(groupKey = "maestro", commandKey = "getAlerts", fallbackMethod = "getAlertsFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="10000") })
	public String getAlerts(String maestroSessionToken) {
		log.debug("getAlerts: Maestro Hystrix call initiated");
		String maestroAlertResp = maestroClient.getAlerts(maestroSessionToken);
		log.debug("getAlerts: sessionToken-{}" ,maestroAlertResp );
		return maestroAlertResp;

	}


	public String getAlertsFallBack(String maestroSessionToken , Throwable t)
	{	
		log.error("getAlertsFallBack: error retrieving the maestro session token {}",SEErrorCode.SERVICE_FALL_BACK_MAESTRO_TOKEN_ERROR.toString() );
		if(t == null) {
			log.error("getAlertsFallBack: Time Out occurred-{}",t);
			throw new ApplicationTimeOutException(TOErrorCode.MAESTRO_GETALERTS_CONTENT_TIME_OUT_OCCURRED, HttpStatus.GATEWAY_TIMEOUT.toString(), t);
		}else if( t instanceof ApplicationClientException)
		{   log.error("getAlertsFallBack: service exeption occurred while getting Alerts with session token - {}",maestroSessionToken);
				throw (ApplicationClientException) t;
		}else {
			log.error("getAlertsFallBack: unknown exception caught in fallback {}",t);
			throw new ApplicationClientException(CEErrorCode.MAESTRO_GET_DOCS_ALERTS_BACK_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), t);
		}
	}

	@HystrixCommand(groupKey = "maestro", commandKey = "getDocument", fallbackMethod = "getDocumentFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	@Override
	public String getDocument(String docId, String maestroTokenId) {
		if (StringUtils.isNotBlank(maestroTokenId)) {
			log.debug("getDocument: Maestro Hystrix call initiated");
			String docResponse = maestroClient.getDocument(docId, maestroTokenId);
			log.debug("getDocument: response", docResponse);
			return docResponse;
		}
		return null;
	}


	public String getDocumentFallBack(String docId , String maestroSessionToken, Throwable t)
	{	
		log.error("getDocumentFallBack: error retrieving the maestro Document",SEErrorCode.SERVICE_FALL_BACK_MAESTRO_GETDOC_ERROR.toString() );
		if(t == null) {
			log.error("getRelatedDocDetailsFallBack: Time Out occurred-{}",t);
			throw new ApplicationTimeOutException(TOErrorCode.MAESTRO_GETDOCUMENT_CONTENT_TIME_OUT_OCCURRED, HttpStatus.GATEWAY_TIMEOUT.toString(), t);
		}else if( t instanceof ApplicationClientException)
		{   log.error("getRelatedDocDetailsFallBack: service exeption occurred for searchSring - {} and session token - {}", docId, maestroSessionToken);
				throw (ApplicationClientException) t;
		}else {
			log.error("getRelatedDocDetailsFallBack: unknown exception caught in fallback {}",t);
			throw new ApplicationClientException(CEErrorCode.MAESTRO_GET_DOCS_FALL_BACK_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), t);
		}

	}

	@HystrixCommand(groupKey = "maestro", commandKey = "getRelatedDocDetails", fallbackMethod = "getRelatedDocDetailsFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	@Override
	public String getRelatedDocDetails(String docId, String maestroTokenId) {
		if (StringUtils.isNotBlank(maestroTokenId)) {
			log.debug("getRelatedDocDetails: Maestro Hystrix call initiated");
			String docRelResponse = maestroClient.getRelatedDocDetails(docId, maestroTokenId);
			log.debug("getRelatedDocDetails: response", docRelResponse);
			return docRelResponse;
		}
		return null;
	}

	public String getRelatedDocDetailsFallBack(String docId , String maestroSessionToken, Throwable t)
	{	
		log.error("getRelatedDocDetailsFallBack: error retrieving the maestro Related Document",SEErrorCode.SERVICE_FALL_BACK_MAESTRO_GETDOC_DETAIL_ERROR.toString() );
		if(t == null) {
			log.error("getRelatedDocDetailsFallBack: Time Out occurred-{}",t);
			throw new ApplicationTimeOutException(TOErrorCode.MAESTRO_GETRELATED_DOCS_CONTENT_TIME_OUT_OCCURRED, HttpStatus.GATEWAY_TIMEOUT.toString(), t);
		}else if( t instanceof ApplicationClientException)
		{   log.error("getRelatedDocDetailsFallBack: service exeption occurred for searchSring - {} and session token - {}", docId, maestroSessionToken);
				throw (ApplicationClientException) t;
		}else {
			log.error("getRelatedDocDetailsFallBack: unknown exception caught in fallback {}",t);
			throw new ApplicationClientException(CEErrorCode.MAESTRO_RELATED_DOCS_FALL_BACK_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), t);
		}

	}

	/**
	 *@MethodName  getContentForSearchCriteria
	 *@Description  This method determines the hystrix thread strategy for the network call to Maestro service
	 *				to retrieve list of contents for the search critieria
	 * 
	 * @param	String 		search
	 * @param	String		sessionToken
	 * @return	String 		contentForSearchCriteria
	 */
	@HystrixCommand(groupKey="maestro", commandKey ="getContentForSearchCriteria", fallbackMethod="getContentForSearchCriteriaFallBack",
			commandProperties={ 
			@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE"),
			@HystrixProperty(name="execution.timeout.enabled", value="false"),
	})
	@Override
	public String getContentForSearchCriteria(String search, String sessionToken) {

		log.debug("getContentForSearchCriteria: Inside the hystrix command invoking the maestro service");
		String contentForSearchCriteria = maestroClient.getContentForSearchCriteria(search, sessionToken);
		log.info("getContentForSearchCriteria: successfully retrieved the response from maestro service-{}",contentForSearchCriteria);
		return contentForSearchCriteria;
	}


	/**
	 *@MethodName  getMaestroSessionToken
	 *@Description  This method captures all the exceptions returned from a maestro service call
	 * 
	 * @param	String		search
	 * @param   String 		sessionToken
	 * @param	Throwable	t
	 * @return 
	 */
	public String getContentForSearchCriteriaFallBack(String search, String sessionToken, Throwable t) {

		log.error("getContentForSearchCriteriaFallBack: In fall back retreiving list of contents from maestro service");
		if(t == null) {
			log.error("getMaestroSessionTokenFallBack: Time Out occurred-{}",t);
			throw new ApplicationTimeOutException(TOErrorCode.MAESTRO_SEARCH_CONTENT_TIME_OUT_OCCURRED, HttpStatus.GATEWAY_TIMEOUT.toString(), t);
		}else if( t instanceof ApplicationClientException)
		{   log.error("getContentForSearchCriteriaFallBack: service exeption occurred for searchSring - {} and session token - {}", search, sessionToken);
				throw (ApplicationClientException) t;
		}else {
			log.error("submitDeviceHitFallBack: unknown exception caught in fallback {}",t);
			throw new ApplicationClientException(CEErrorCode.MAESTRO_CONTENT_SEARCH_FALL_BACK_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), t);
		}
	}
}
