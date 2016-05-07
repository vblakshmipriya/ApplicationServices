package com.comcast.technucleus.application.exception;

import org.springframework.util.StringUtils;

import com.comcast.technucleus.application.util.TechNucleusErrorProperties;
import com.google.gson.JsonObject;

public class ApplicationClientException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String exceptionMessage;
	private String errorCode;
	private String httpStatusCode;

	private JsonObject errorMessages;

	public static enum CEErrorCode
	{

		GLYMPSE_SESSION_RESPONSE_IS_NULL("CE0001"),
		WORK_ORDER_RESPONSE_IS_NULL("CE0002"),
		GLYMPSE_SESSION_CREATION_ERROR("CE0003"),
		UNKNOWN_ERROR_FROM_CSP_SERVER_RETRIEVING_SESSION("CE0004"),
		GLYMPSE_SESSION_UPDATE_FAILED("CE0005"),
		INVALID_MASTER_DRIVER_TICKET("CE0006"),
		GLYMPSE_SESSION_URL_IS_NULL_EMPTY("CE0007"),
		INVALID_WORKORDER_URL_FROM_DB("CE0008"),
		NULL_RESPONSE_UPDATING_GLYMPSE_SESSION("CE0009"),
		WORK_ORDER_RETRIEVAL_ERROR("CE0010"),
		GLYMPSE_SESSION_CREATION_WITH_NULL_RESPONSE("CE0011"),
		LOCATION_SERVICE_SOAP_ERROR_RESPONSE("CE0012"),
		LOCATION_SERVICE_SOAP_RESPONSE_IS_NULL("CE0013"),
		CSP_SERVICE_GLYMPSE_SESSION_UPDATE_UNKNOWN_ERROR_RESPONSE("CE0014"),
		OFFER_MGMT_SERVICE_RESPONSE_IS_NULL("CE0015"),
		OFFER_MGMT_SERVICE_SOAP_ERROR_RESPONSE("CE0016"),
		OFFER_MGMT_SERVICE_EMPTY_BILLING_CODE_MAPPING_LIST("CE0017"),
		CONTRACT_SERVICE_SOAP_FAULT_ERROR("CE0018"),
		CONTRACT_SERVICE_XH_SUB_TECH_SIGNER_ERROR("CE0019"),
		CONTRACT_SERVICE_XH_SUB_TECH_SIGNER_EXCEPTION("CE0020"),
		CONTRACT_SERVICE_XH_CUST_SOAP_ERROR_RESPONSE("CE0021"),
		GLYMPSE_WORK_ORDER_RETRIEVAL_ERROR("CE0022"),
		GLYMPSE_UNKNOWN_ERROR_RETRIEVING_WORK_ORDER("CE0023"),
		GLYMPSE_SESSION_RETRIEVAL_ERROR("CE0024"),
		GLYMPSE_EXISTING_SESSION_RETRIEVED_IS_NULL("CE0025"),
		GLYMPSE_SESSION_CREATION_SERVER_ERROR("CE0026"),
		GLYMPSE_UNKNOWN_ERROR_CREATING_GLYMPSE_SESSION("CE0027"),
		GLYMPSE_SESSION_UPDATE_ERROR("CE0028"),
		GLYMPSE_SESSION_WORK_ORDER_FALLBACK_ERROR("CE0029"),
		GLYMPSE_SESSION_RETRIVAL_FALLBACK_ERROR("CE0030"),
		GLYMPSE_SESSION_UPDATE_FALLBACK_ERROR("CE0031"),


		XBO_ACCOUNT_REFRESH_BAD_RESPONSE_RECEIVED("CE0032"),
		XBO_ACCOUNT_REFRESH_SERVER_ERROR_RECEIVED("CE0033"),
		XBO_ACCOUNT_REFRESH_ERROR("CE0034"),
		XBO_ACCOUNT_REFRESH_RESPONSE_IS_NULL("CE0035"),
		XBO_ACCOUNT_REFRESH_ACCOUNT_NOT_FOUND("CE0036"),
		XBO_ACCOUNT_REFRESH_SERVICE_UNAVAILABLE_TRY_AGAIN("CE0037"),
		XBO_ACCOUNT_REFRESH_FALL_BACK_ERROR("CE0038"),
		MAESTRO_SERVICE_EMPTY_RESPONSE("CE0039"),
		MAESTRO_SERVICE_SERVER_ERROR("CE0040"),
		MAESTRO_SERVICE_ERROR("CE0041"),
		MAESTRO_SERVICE_UNKNOWN_SERVER_ERROR("CE0042"),
		MAESTRO_CONTENT_SEARCH_FALL_BACK_ERROR("CE0043"),
		MAESTRO_SERVICE_SESSION_TOKEN_ERROR("CE0044"),
		MAESTRO_SERVICE_SESSION_TOKEN_SERVER_ERROR("CE0045"),
		MAESTRO_SERVICE_SESSION_TOKEN_UNKNOWN_SERVER_ERROR("CE0046"),
		MAESTRO_SERVICE_SESSION_TOKEN_NULL_RESPONSE("CE0047"),
		DEVICE_HIT_EHH_SERVICE_ERROR("CE0048"),
		DEVICE_HIT_EHH_UNKNOWN_SERVICE_ERROR("CE0049"),
		REFRESH_EVENT_SERVICE_RESPONSE_IS_NULL("CE0050"),
		REFRESH_EVENT_SERVICE_SOAP_ERROR_RESPONSE("CE0051"),
		DEVICE_HIT_EHH_UNKNOWN_ERROR("CE0052"),
		
		
		WFA_WORK_ORDER_RETRIEVAL_ERROR("CE0053"),
		WFA_WORK_ORDER_UNKNOWN_ERROR("CE0054"),
		WFA_WORK_ORDER_RESPONSE_IS_NULL("CE0055"),
		TECH_STATUS_UPDATE_EMTPTY_RESPONSE_RECEIVED("CE0056"),
		TECH_STATUS_UPDATE_FAILED("CE0057"),
		TECH_STATUS_UPDATE_UNKNOWN_ERROR("CE0058"),
		TECH_STATUS_UPDATE_ON_JOB_FAILED("CE0059"),
		TECH_STATUS_UPDATE_ON_JOB_UNKNOWN_ERROR("CE0060"),
		TECH_STATUS_UPDATE_ON_JOB_EMTPTY_RESPONSE_RECEIVED("CE0061"),
		
		
		MAESTRO_RELATED_DOCS_FALL_BACK_ERROR("CE0062"),
		MAESTRO_GET_DOCS_FALL_BACK_ERROR("CE0063"),
		MAESTRO_GET_DOCS_ALERTS_BACK_ERROR("CE0064"),
		
		CONTACT_SERVICE_SOAP_FAULT("CE0065"),
		CONTACT_SERVICE_DATA_TYPE_CONFIGURATION_ERROR("CE0066"),
		CONTACT_SERVICE_UNKNOWN_EXCEPTION("CE0067"),
		
		SERVICE_WFA_ADD_DEVICE_HTTP_CLIENT_ERROR("CE0068"),
		SERVICE_WFA_ADD_DEVICE_UNKNOWN_EXCEPTION("CE0069"),
		
		EIS_SERVICE_PARSING_RESPONSE_ERROR("CE0070"),
		EIS_SERVICE_UNKNOWN_FALL_BACK_EXCEPTION("CE0071"),
		EIS_SERVICE_RESPONSE_IS_NULL("CE0072"),
		EIS_SERVICE_UNKNOWN_EXCEPTION("CE0073"),
		
		LOCATION_SERVICE_UNKNOWN_ERROR("CE0074"),
		LOCATION_SERVICE_PORT_IS_NULL("CE0075"),
		
		OFFER_MGMT_SERVICE_UNKNOWN_ERROR("CE0076");
		

		private String errorCode;
		private CEErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}    
		@Override
		public String toString(){
			return errorCode;
		}
	}

	public ApplicationClientException(CEErrorCode errorCode, String downStreamErrorMessage, String httpStatusCode, Throwable exception) {
		super(downStreamErrorMessage,exception);
		TechNucleusErrorProperties properties = TechNucleusErrorProperties.getInstance();
		this.exceptionMessage = properties.getProperty(errorCode.toString());
		if(!StringUtils.isEmpty(downStreamErrorMessage)) {
			this.exceptionMessage = exceptionMessage.replace("{}", downStreamErrorMessage);
		}
		this.httpStatusCode = httpStatusCode;
		this.errorCode = errorCode.toString();
	}

	public ApplicationClientException(CEErrorCode errorCode, String httpStatusCode) {
		super();
		TechNucleusErrorProperties properties = TechNucleusErrorProperties.getInstance();
		String errorMessage = properties.getProperty(errorCode.toString());
		this.exceptionMessage = errorMessage;
		this.httpStatusCode = httpStatusCode;
		this.errorCode = errorCode.toString();
	}

	public ApplicationClientException(String errorCode, JsonObject errorMessages, Throwable t) {
		super(errorMessages.toString());
		this.errorCode = errorCode;
		this.exceptionMessage = "property message to be added for the error code";
	}

	public JsonObject getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(JsonObject errorMessages) {
		this.errorMessages = errorMessages;
	}

	public String getPropertyMessage() {
		return exceptionMessage;
	}
	public void setPropertyMessage(String propertyMessage) {
		this.exceptionMessage = propertyMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(String httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
}
