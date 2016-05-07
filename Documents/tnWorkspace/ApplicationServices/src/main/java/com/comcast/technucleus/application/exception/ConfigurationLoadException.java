package com.comcast.technucleus.application.exception;

public class ConfigurationLoadException  extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -967593775650556395L;

	private final Throwable error;

	/** The nt id. */
	private final String message;

	/**
	 * Instantiates a new user not found exception.
	 *
	 * @param ntId the nt id
	 */
	public ConfigurationLoadException(String message, Throwable exception) {
		super(message,exception);
		this.message = message;
		this.error = exception;	
	}
	
	public ConfigurationLoadException(String message) 
	{
		super(message);
		this.message = message;
		this.error = null;	
	}

	public Throwable getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}



}
