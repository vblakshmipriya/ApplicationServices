package com.comcast.technucleus.application.exception;

import org.springframework.util.StringUtils;

import com.comcast.technucleus.application.exception.ApplicationServiceException.SEErrorCode;
import com.comcast.technucleus.application.util.TechNucleusErrorProperties;

public class ApplicationTimeOutException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String exceptionMessage;

	private String errorCode;
	private String httpStatusCode;

	public static enum TOErrorCode
	{
		SERVICE_WFA_ADD_DEVICE_TIME_OUT("TO0001"),
		GLYMPSE_SESSION_WORK_ORDER_TIME_OUT_OCCURRED("TO0002"),
		GLYMPSE_SESSION_RETRIVAL_TIME_OUT_OCCURRED("TO0003"),
		GLYMPSE_SESSION_UPDATE_TIME_OUT_OCCURRED("TO0004"),
		XBO_ACCOUNT_REFRESH_TIME_OUT_OCCURRED("TO0005"),
		MAESTRO_SEARCH_CONTENT_TIME_OUT_OCCURRED("TO0006"),
		MAESTRO_SESSION_TOKEN_TIME_OUT_OCCURRED("TO0007"),
		MAESTRO_GETALERTS_CONTENT_TIME_OUT_OCCURRED("TO0008"),
		MAESTRO_GETDOCUMENT_CONTENT_TIME_OUT_OCCURRED("TO0009"),
		MAESTRO_GETRELATED_DOCS_CONTENT_TIME_OUT_OCCURRED("TO0010");
		
		private String errorCode;
		private TOErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}    
		@Override
		public String toString(){
			return errorCode;
		}
	}
	
	public ApplicationTimeOutException(TOErrorCode errorCode, String downStreamErrorMessage, Throwable exception) {
		super(downStreamErrorMessage,exception);
		TechNucleusErrorProperties properties = TechNucleusErrorProperties.getInstance();
		this.exceptionMessage = properties.getProperty(errorCode.toString());
		if(StringUtils.isEmpty(exceptionMessage)) {
			exceptionMessage.replace("{}", downStreamErrorMessage);
		}
		this.errorCode = errorCode.toString();
	}
	
	public ApplicationTimeOutException(TOErrorCode errorCode) {
		super();
		TechNucleusErrorProperties properties = TechNucleusErrorProperties.getInstance();
		String errorMessage = properties.getProperty(errorCode.toString());
		this.exceptionMessage = errorMessage;
		this.errorCode = errorCode.toString();
	}


	public ApplicationTimeOutException(String message, Throwable exception) {
		super(message,exception);
		this.exceptionMessage = message;
	}
	public ApplicationTimeOutException(Throwable exception) {
		super(exception);
	}

	public ApplicationTimeOutException(String message) {
		super(message);
		this.exceptionMessage = message;
	}

	public ApplicationTimeOutException(String message, String errorCode) {
		super(message);
		this.exceptionMessage = message;
		this.errorCode = errorCode;
	}
	
	public ApplicationTimeOutException(String message, SEErrorCode errorCode) {
		super(message);
		this.exceptionMessage = message;
		this.errorCode = errorCode.toString();
	}
	public ApplicationTimeOutException(String message, SEErrorCode errorCode,Throwable exception) {
		super(message,exception);
		TechNucleusErrorProperties properties = TechNucleusErrorProperties.getInstance();
		String errorMessage = properties.getProperty(errorCode.toString());
		this.exceptionMessage = errorMessage;
		this.errorCode = errorCode.toString();
	}
	
	public ApplicationTimeOutException(SEErrorCode errorCode, String downStreamErrorMessage, Throwable exception) {
		super(downStreamErrorMessage,exception);
		TechNucleusErrorProperties properties = TechNucleusErrorProperties.getInstance();
		String errorMessage = properties.getProperty(errorCode.toString());
		this.exceptionMessage = errorMessage;
		this.errorCode = errorCode.toString();
	}
	
	public ApplicationTimeOutException(SEErrorCode errorCode,Throwable exception) {
		super(exception);
		TechNucleusErrorProperties properties = TechNucleusErrorProperties.getInstance();
		String errorMessage = properties.getProperty(errorCode.toString());
		this.exceptionMessage = errorMessage;
		this.errorCode = errorCode.toString();
	}
	

	public String getErrorCode() {
		return errorCode;
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
