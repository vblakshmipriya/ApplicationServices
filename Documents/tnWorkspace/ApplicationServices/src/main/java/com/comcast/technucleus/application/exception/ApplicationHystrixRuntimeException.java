package com.comcast.technucleus.application.exception;

import org.springframework.util.StringUtils;

import com.comcast.technucleus.application.util.TechNucleusErrorProperties;
import com.google.gson.JsonObject;

public class ApplicationHystrixRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String exceptionMessage;
	private String errorCode;
	private String httpStatusCode;

	private JsonObject errorMessages;

	public static enum HEErrorCode
	{

	}

	public ApplicationHystrixRuntimeException(HEErrorCode errorCode, String downStreamErrorMessage, String httpStatusCode, Throwable exception) {
		super(downStreamErrorMessage,exception);
		TechNucleusErrorProperties properties = TechNucleusErrorProperties.getInstance();
		this.exceptionMessage = properties.getProperty(errorCode.toString());
		if(!StringUtils.isEmpty(downStreamErrorMessage)) {
			this.exceptionMessage = exceptionMessage.replace("{}", downStreamErrorMessage);
		}
		this.httpStatusCode = httpStatusCode;
		this.errorCode = errorCode.toString();
	}

	public ApplicationHystrixRuntimeException(HEErrorCode errorCode, String httpStatusCode) {
		super();
		TechNucleusErrorProperties properties = TechNucleusErrorProperties.getInstance();
		String errorMessage = properties.getProperty(errorCode.toString());
		this.exceptionMessage = errorMessage;
		this.httpStatusCode = httpStatusCode;
		this.errorCode = errorCode.toString();
	}

	public ApplicationHystrixRuntimeException(String errorCode, JsonObject errorMessages, Throwable t) {
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
