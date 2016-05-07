package com.comcast.technucleus.application.exception;

import org.apache.commons.lang3.StringUtils;

import com.comcast.technucleus.application.util.TechNucleusErrorProperties;


public class ApplicationInvalidRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String exceptionMessage;
	private String errorCode;
	private String httpStatusCode;

	public static enum IRErrorCode
	{
		DEVICE_MODEL_LIST_EMPTY("IR00001"),
		TECHNICIAN_STATUS_INVALID("IR0002"),
		GLYMPSE_SESSION_UPDATE_REQUEST_NULL("IR0003"),
		GLYMPSE_INVALID_MASTER_TICKET("IR0004"),
		GLYMPSE_INVALID_DRIVER_TICKET("IR0005"),
		GLYMPSE_INVALID_PHASE("IR0006"),
		WORK_ORDER_INVALID("IR0007"),
		INVALID_DRIVER_TICKET("IR0008"),
		ACTIVATE_DEVICE_WORK_ORDER_EMPTY("IR0009"),
		ACTIVATE_DEVICE_SCHEDULE_DT_EMPTY("IR0010"),
		ACTIVATE_DEVICE_DEVICE_SERIAL_NUMBER_EMPTY("IR0011"),
		ACTIVATE_DEVICE_DEVICE_SEQUENCE_NUMBER_EMPTY("IR0012"),
		ACTIVATE_DEVICE_WORK_ORDER_NOT_FOUND("IR0013"),
		ACTIVATE_DEVICE_SERIAL_NUMBER_NOT_FOUND("IR0014"),
		ACTIVATE_DEVICE_STATUS_WORK_ORDER_EMPTY("IR0009"),
		ACTIVATE_DEVICE_STATUS_SCHEDULE_DT_EMPTY("IR0010"),
		ACTIVATE_DEVICE_STATUS_SEQUENCE_NUMBER_EMPTY("IR0012"),
		ACTIVATE_DEVICE_STATUS_WORK_ORDER_NOT_FOUND("IR0013"),
		ACTIVATE_DEVICE_STATUS_NO_DEVICE_FOUND_WITH_SEQ_NUM("IR0013"),
		CAAP_DEVICE_ACTIVATION_INVALID_WORK_ORDER("IR0014"),
		CAAP_DEVICE_ACTIVATION_INVALID_DATE("IR0015"),
		CAAP_DEVICE_ACTIVATION_SCHEDULED_DATE_INVALID("IR0016"),
		GLYMPSE_SESSION_RETRIEVAL_WORK_ORDER_IS_EMPTY("IR0017"),
		XBO_ACCOUNT_REFERSH_EMPTY_REQUEST("IRE0018"),
		CONTENT_SEARCH_EMPTY_STRING("IRE0019"),
		DEVICE_HIT_REQUEST_IS_NULL("IRE0020"),
		DEVICE_HIT_REQUEST_CABLE_CARD_SERIAL_NUMBER_IS_EMPTY_OR_NULL("IR0021"),
		DEVICE_HIT_REQUEST_HIT_TYPE_IS_EMPTY_OR_NULL("IR0022"),
		DEVICE_HIT_REQUEST_DEVICE_TYPE_IS_EMPTY_OR_NULL("IR0023");

		private String errorCode;
		private IRErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}    
		@Override
		public String toString(){
			return errorCode;
		}
	}

	public ApplicationInvalidRequestException(String message, Throwable exception) {
		super(message,exception);
		this.exceptionMessage = message;
	}
	public ApplicationInvalidRequestException(Throwable exception) {
		super(exception);
	}

	public ApplicationInvalidRequestException(String message) {
		super(message);
		this.exceptionMessage = message;
	}

	public ApplicationInvalidRequestException(String message, String errorCode) {
		super(message);
		this.exceptionMessage = message;
		this.errorCode = errorCode;
	}
	public ApplicationInvalidRequestException(String message, IRErrorCode errorCode) {
		super(message);
		TechNucleusErrorProperties properties = TechNucleusErrorProperties.getInstance();

		String errorMessage = properties.getProperty(errorCode.toString());
		this.exceptionMessage = errorMessage;
		this.errorCode = errorCode.toString();
	}

	public ApplicationInvalidRequestException(IRErrorCode errorCode) {
		super();
		TechNucleusErrorProperties properties = TechNucleusErrorProperties.getInstance();
		String errorMessage = properties.getProperty(errorCode.toString());
		this.exceptionMessage = errorMessage;
		this.errorCode = errorCode.toString();
	}

	public ApplicationInvalidRequestException(IRErrorCode errorCode, String downStreamErrorMessage, String httpStatusCode) {
		super();
		TechNucleusErrorProperties properties = TechNucleusErrorProperties.getInstance();

		String errorMessage = properties.getProperty(errorCode.toString());
		if(!StringUtils.isEmpty(errorMessage)) {
			this.exceptionMessage = exceptionMessage.replace("{}", downStreamErrorMessage);
		}
		this.exceptionMessage = errorMessage;
		this.errorCode = errorCode.toString();
		this.httpStatusCode = httpStatusCode;
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
