package com.comcast.technucleus.application.exception;

import com.comcast.technucleus.application.util.TechNucleusErrorProperties;

public class DBException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;
	private String errorCode;

	public static enum DBErrorCode
	{
		GLYPMSE_SESSION_DATA_LOAD_ERROR("DLE0001"),
		GLYPMSE_SESSION_DATA_RETRIEVAL_ERROR("DLE0002");
		private String errorCode;
		private DBErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}    
		@Override
		public String toString(){
			return errorCode;
		}
	}

	public DBException(String message, Throwable exception) {
		super(message,exception);
		this.message = message;
	}
	public DBException(Throwable exception) {
		super(exception);
	}

	public DBException(String message) {
		super(message);
		this.message = message;
	}

	public DBException(String message, String errorCode) {
		super(message);
		this.message = message;
		this.errorCode = errorCode;
	}
	
	public DBException(String message, DBErrorCode errorCode) {
		super(message);
		this.message = message;
		this.errorCode = errorCode.toString();
	}
	public DBException(String message, DBErrorCode errorCode,Throwable exception) {
		super(message,exception);
		TechNucleusErrorProperties properties = TechNucleusErrorProperties.getInstance();
		String errorMessage = properties.getProperty(errorCode.toString());
		this.message = errorMessage;
		this.errorCode = errorCode.toString();
	}
	
	public DBException(DBErrorCode errorCode,Throwable exception) {
		super(exception);
		TechNucleusErrorProperties properties = TechNucleusErrorProperties.getInstance();
		String errorMessage = properties.getProperty(errorCode.toString());
		this.message = errorMessage;
		this.errorCode = errorCode.toString();
	}
	public String getMessage() {
		return message;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
