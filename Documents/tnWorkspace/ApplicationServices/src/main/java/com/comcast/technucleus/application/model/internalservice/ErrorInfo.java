/*
 * @author
 * @date Jun 9, 2015
 * @project TechNucleus
 */
package com.comcast.technucleus.application.model.internalservice;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class ErrorInfo.
 */
public class ErrorInfo {

	/** The error message. */
	private String errorMessage;

	/** The error uri. */
	private String errorURI;

	/**
	 * Instantiates a new error info.
	 *
	 * @param errorMessage the error message
	 * @param errorURI the error uri
	 */
	@JsonCreator
	public ErrorInfo(@JsonProperty("ErrorMessage") String errorMessage,
			@JsonProperty("ErrorURI") String errorURI) {
		super();
		this.errorMessage = errorMessage;
		this.errorURI = errorURI;
	}

	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Sets the error message.
	 *
	 * @param errorMessage the new error message
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Gets the error uri.
	 *
	 * @return the error uri
	 */
	public String getErrorURI() {
		return errorURI;
	}

	/**
	 * Sets the error uri.
	 *
	 * @param errorURI the new error uri
	 */
	public void setErrorURI(String errorURI) {
		this.errorURI = errorURI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof ErrorInfo)) {
			return false;
		}
		ErrorInfo castOther = (ErrorInfo) other;
		return Objects.equal(errorMessage, castOther.errorMessage)
				&& Objects.equal(errorURI, castOther.errorURI);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(errorMessage, errorURI);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("errorMessage", errorMessage).add("errorURI", errorURI)
				.toString();
	}
}
