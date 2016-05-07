package com.comcast.technucleus.application.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;

public class FallBackCustomException extends HystrixBadRequestException {
	
	private static final long serialVersionUID = 1L;
	private String message;
	private String errorCode;
	  
	public static enum FallBackErrorCode
	{
        FALL_BACK_MAESTRO_TOKEN_ERROR("FB0001"),
        FALL_BACK_MAESTRO_DEVICE_IMAGE_RESPONSE_ERROR("FB0002"),
        FALL_BACK_CSP_WORK_ORDER_RESPONSE_ERROR("FB003"),
        FALL_BACK_CSP_GLYMPSE_SESSION_RETRIEVAL_ERROR("FB004");
        
        private String errorCode;
        private FallBackErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }    
        @Override
        public String toString(){
            return errorCode;
        }
    }
	
	public FallBackCustomException(String message, Throwable exception) {
		super(message);
		this.message = message;
	}
	public FallBackCustomException(String message) {
		
		super(message);
	}
	
	public FallBackCustomException(String message, String errorCode) {
		super(message);
		this.message = message;
		this.errorCode = errorCode;
	}
	
	
    @Override
    public synchronized Throwable getCause() {
    	 return this;
    }
	
	public String getMessage() {
		return message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	
}


