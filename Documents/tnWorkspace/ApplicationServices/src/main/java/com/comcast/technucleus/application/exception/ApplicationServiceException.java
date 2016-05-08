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
		SERVICE_THREAD_INTERRUPTED("ASE0001"),
		SERVICE_EXECUTION_EXCEPTION("ASE0002"),
		SERVICE_WFA_RETRIVE_TECH_BUFF_HTTP_CLIENT_ERROR("ASE0003"),
		SERVICE_WFA_RETRIVE_TECH_BUFF_UNKNOWN_EXCEPTION("ASE0004"),
		SERVICE_WFA_ADD_DEVICE_HTTP_CLIENT_ERROR("ASE0005"),
		SERVICE_WFA_ADD_DEVICE_UNKNOWN_EXCEPTION("ASE0006"),
		//SERVICE_CAAP_ACTIVATION_WORK_ORDER_CACHE_IS_NULL("ASE0007"),
		//SERVICE_CAAP_ACTIVATION_HMAC_GENERATION_ERROR("ASE0008"),
		//SERVICE_EHH_GET_INTEGRITY_HTTP_CLIENT_ERROR("ASE0009"),
		//SERVICE_EHH_GET_DEVICE_HTTP_CLIENT_ERROR("ASE0010"),
		//SERVICE_EHH_SERACH_ACCOUNT_HTTP_CLIENT_ERROR("ASE0011"),
		//SERVICE_EHH_NODE_HEALTH_HTTP_CLIENT_ERROR("ASE0012"),
		//SERVICE_EHH_GET_INTEGRITY_UNKNOWN_ERROR("ASE0013"),
		//SERVICE_EHH_GET_DEVICE_UNKNOWN_ERROR("ASE0014"),
		//SERVICE_EHH_SERACH_ACCOUNT_UNKNOWN_ERROR("ASE0015"),
		//SERVICE_EHH_NODE_HEALTH_UNKNOWN_ERROR("ASE0016"),
		//SERVICE_WFA_INVALID_WORK_ORDER_STATUS("ASE0017"),
		//SERVICE_CUST_TIMELINE_HTTP_CLIENT_ERROR("ASE0018"),
		//SERVICE_CUST_TIMELINE_UNKNOWN_ERROR("ASE0019"),
		SERVICE_MONGO_DATA_LOAD_ERROR("ASE0020"),
		SERVICE_CONFIG_LOAD_ERROR("ASE0021"),
		SERVICE_GLYPMSE_SESSION_DATA_LOAD_ERROR("ASE0022"),
		SERVICE_GLYPMSE_SESSION_DATA_RETRIEVAL_ERROR("ASE0023"),
		SERVICE_FALL_BACK_MAESTRO_TOKEN_ERROR("ASE0024"),
		SERVICE_FALL_BACK_MAESTRO_ALERTS_ERROR("ASE0025"),
		SERVICE_FALL_BACK_MAESTRO_DEVICE_IMAGE_RESPONSE_ERROR("ASE0026"),
		SERVICE_FALL_BACK_EIS_TECH_BUFFER("ASE0027"),
		SERVICE_FALL_BACK_MAESTRO_GETDOC_ERROR("ASE0028"),
		SERVICE_FALL_BACK_MAESTRO_GETDOC_DETAIL_ERROR("ASE0029"),
		SERVICE_FALL_BACK_XH_SUB_TECH_SIGNER_ERROR("ASE0030"),
		SERVICEFALL_BACK_XH_SUB_CUST_SIGNER_ERROR("ASE0031"),
		SERVICE_FALL_BACK_REFRESH_EVENT_SERVICE_ERROR("ASE0032"),
		SERVICE_INVALID_PROPERTY_REQUEST_ERROR("ASE0034");
		
		
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
