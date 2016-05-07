/*
 * @author
 * @date Jun 9, 2015
 * @project TechNucleus
 */
package com.comcast.technucleus.application.model.internalservice;

// TODO: Auto-generated Javadoc

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthorizationDetails.
 */

public class AuthorizationDetails {

	/** The message. */
	String message;

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param name the new message
	 */
	public void setMessage(String name) {
		this.message = name;
	}

	/**
	 * Instantiates a new authorization details.
	 */
	public AuthorizationDetails() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AuthorizationDetails)) {
			return false;
		}
		AuthorizationDetails castOther = (AuthorizationDetails) other;
		return Objects.equal(message, castOther.message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("message", message)
				.toString();
	}

}