/*
 * @author
 * @date Jun 9, 2015
 * @project TechNucleus
 */
package com.comcast.technucleus.application.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class UserNotFoundException.
 */
public class UserNotFoundException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -967503775650556395L;

	/** The nt id. */
	private final String ntId;

	/**
	 * Instantiates a new user not found exception.
	 *
	 * @param ntId the nt id
	 */
	public UserNotFoundException(String ntId) {
		this.ntId = ntId;
	}

	/**
	 * Gets the nt id.
	 *
	 * @return the nt id
	 */
	public String getNtId() {
		return ntId;
	}

}
