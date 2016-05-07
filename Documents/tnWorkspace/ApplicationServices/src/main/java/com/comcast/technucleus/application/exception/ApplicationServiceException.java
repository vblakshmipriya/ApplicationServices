package com.comcast.technucleus.application.exception;

import org.springframework.util.StringUtils;

import com.comcast.technucleus.application.exception.ApplicationClientException.CEErrorCode;
import com.comcast.technucleus.application.exception.ApplicationServiceException.SEErrorCode;
import com.comcast.technucleus.application.util.TechNucleusErrorProperties;

public class ApplicationServiceException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String exceptionMessage;
	
	private String errorCode;
	private String httpStatusCode;

	
	public static enum SEErrorCode
	{
		SERVICE_THREAD_INTERRUPTED("SE0001"),
		SERVICE_EXECUTION_EXCEPTION("SE0002"),
		SERVICE_WFA_RETRIVE_TECH_BUFF_HTTP_CLIENT_ERROR("SE0003"),
		SERVICE_WFA_RETRIVE_TECH_BUFF_UNKNOWN_EXCEPTION("SE0004"),
		SERVICE_WFA_ADD_DEVICE_HTTP_CLIENT_ERROR("SE0005"),
		SERVICE_WFA_ADD_DEVICE_UNKNOWN_EXCEPTION("SE0006"),
		SERVICE_CAAP_ACTIVATION_WORK_ORDER_CACHE_IS_NULL("SE0007"),
		SERVICE_CAAP_ACTIVATION_HMAC_GENERATION_ERROR("SE0008"),
		SERVICE_EHH_GET_INTEGRITY_HTTP_CLIENT_ERROR("SE0009"),
		SERVICE_EHH_GET_DEVICE_HTTP_CLIENT_ERROR("SE0010"),
		SERVICE_EHH_SERACH_ACCOUNT_HTTP_CLIENT_ERROR("SE0011"),
		SERVICE_EHH_NODE_HEALTH_HTTP_CLIENT_ERROR("SE0012"),
		SERVICE_EHH_GET_INTEGRITY_UNKNOWN_ERROR("SE0013"),
		SERVICE_EHH_GET_DEVICE_UNKNOWN_ERROR("SE0014"),
		SERVICE_EHH_SERACH_ACCOUNT_UNKNOWN_ERROR("SE0015"),
		SERVICE_EHH_NODE_HEALTH_UNKNOWN_ERROR("SE0016"),
		SERVICE_WFA_INVALID_WORK_ORDER_STATUS("SE0017"),
		SERVICE_CUST_TIMELINE_UNKNOWN_ERROR("SE0019"),
		SERVICE_CUST_TIMELINE_HTTP_CLIENT_ERROR("SE0018");
		
		
		private String errorCode;
		private SEErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}    
		@Override
		public String toString(){
			return errorCode;
		}
	}

	public ApplicationServiceException(String message, Throwable exception) {
		super(message,exception);
		this.exceptionMessage = message;
	}
	
	public ApplicationServiceException(Throwable exception) {
		super(exception);
	}

	public ApplicationServiceException(SEErrorCode errorCode, String downStreamErrorMessage, String httpStatusCode, Throwable exception) {
		super(downStreamErrorMessage,exception);
		TechNucleusErrorProperties properties = TechNucleusErrorProperties.getInstance();
		String errorMessage = properties.getProperty(errorCode.toString());
		if(!StringUtils.isEmpty(downStreamErrorMessage)) {
			errorMessage = errorMessage.replace("{}", downStreamErrorMessage);
		}
		this.exceptionMessage = errorMessage;
		this.errorCode = errorCode.toString();
		this.httpStatusCode = httpStatusCode;
	}
	
	public ApplicationServiceException(SEErrorCode errorCode, String httpStatusCode) {
		super();
		TechNucleusErrorProperties properties = TechNucleusErrorProperties.getInstance();
		String errorMessage = properties.getProperty(errorCode.toString());
		this.exceptionMessage = errorMessage;
		this.httpStatusCode = httpStatusCode;
		this.errorCode = errorCode.toString();
	}
	
	public String getMessage() {
		return exceptionMessage;
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
