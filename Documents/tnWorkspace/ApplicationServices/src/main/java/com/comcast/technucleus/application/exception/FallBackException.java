package com.comcast.technucleus.application.exception;

public class FallBackException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String message;
	private String errorCode;
	
	public static enum FallBackErrorCode
	{
        FALL_BACK_MAESTRO_TOKEN_ERROR("FB0001"),
        FALL_BACK_MAESTRO_ALERTS_ERROR("FB0002"),
        FALL_BACK_MAESTRO_DEVICE_IMAGE_RESPONSE_ERROR("FB0003"),
		FALL_BACK_EIS_TECH_BUFFER("FB0004"),
		FALL_BACK_MAESTRO_GETDOC_ERROR("FB0005"),
		FALL_BACK_MAESTRO_GETDOC_DETAIL_ERROR("FB0006"),
        FALL_BACK_XH_SUB_TECH_SIGNER_ERROR("FB0007"),
        FALL_BACK_XH_SUB_CUST_SIGNER_ERROR("FB0008"),
        FALL_BACK_REFRESH_EVENT_SERVICE_ERROR("FB0009");
        
        private String errorCode;
        private FallBackErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }    
        @Override
        public String toString(){
            return errorCode;
        }
    }
	
	public FallBackException(String message, Throwable exception) {
		super(message,exception);
		this.message = message;
	}
	public FallBackException(Throwable exception) {
		super(exception);
	}
	
	public FallBackException(String message, String errorCode) {
		super(message);
		this.message = message;
		this.errorCode = errorCode;
	}
	
	public FallBackException(String errorCode, String actualErrorMessage, Throwable t ) {
		super(actualErrorMessage, t);
		this.message = actualErrorMessage;
		this.errorCode = errorCode;
	}
	
	
	public String getMessage() {
		return message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	
}


