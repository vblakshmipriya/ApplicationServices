package com.comcast.technucleus.application.exception;

public class PropertyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PropertyException(String message, Throwable exception)
	{
		super(message, exception);	
	}

	public PropertyException(String message) {
		super(message);
	}
}
