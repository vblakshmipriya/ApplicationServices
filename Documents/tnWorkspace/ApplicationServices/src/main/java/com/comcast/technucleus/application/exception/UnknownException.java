package com.comcast.technucleus.application.exception;

public class UnknownException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UnknownException(UEErrorCode error ,Throwable exception)
	{
		super(error.toString(),exception);
	}
	public static enum UEErrorCode
	{
	
		UNKNOWN_EXCEPTION("UE0006");
		
		private String errorCode;
		private UEErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}    
		
		@Override
		public String toString(){
			return errorCode;
		}
	}

}
