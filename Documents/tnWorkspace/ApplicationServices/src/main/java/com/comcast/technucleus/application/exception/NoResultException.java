package com.comcast.technucleus.application.exception;

public class NoResultException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -967503775650556395L;


	private final String message;
    

	public NoResultException(String message,Throwable t) 
	{
		super(t);
		this.message = message;
	}
	public NoResultException(String message) 
	{
		this.message = message;
	}


	public String getMessage() {
		return message;
	}	
}
