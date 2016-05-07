package com.comcast.technucleus.application.response.data;

public class ErrorResponse {
	
	private String code;
	private String message;
	
	public String getErrorCode() {
		return code;
	}
	public void setErrorCode(String errorCode) {
		this.code = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
