package com.comcast.technucleus.application.exception;

public class ServerInstantiationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String message;

	public ServerInstantiationException(String message, Throwable exception) {
		super(message,exception);
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

}
