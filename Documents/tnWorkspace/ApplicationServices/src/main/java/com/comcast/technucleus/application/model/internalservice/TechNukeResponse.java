/*
 * @author
 * @date Jun 9, 2015
 * @project TechNucleus
 */
package com.comcast.technucleus.application.model.internalservice;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class TechNukeResponse.
 */
public class TechNukeResponse {
	
	private String errorCode;
	/** The status. */
	private String status;

	/** The message. */
	private String message;

	/** The data. */
	Object data;
	

	/**
	 * Instantiates a new tech nuke response.
	 *
	 * @param status the status
	 * @param message the message
	 * @param data the data
	 */
	public TechNukeResponse(String errorCode , String status, String message, Object data) {
		this.setErrorCode(errorCode);
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public TechNukeResponse(String status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
	

	public TechNukeResponse() {
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(Object data) {
		this.data = data;
	}

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
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof TechNukeResponse)) {
			return false;
		}
		TechNukeResponse castOther = (TechNukeResponse) other;
		return Objects.equal(status, castOther.status)
				&& Objects.equal(message, castOther.message)
				&& Objects.equal(data, castOther.data);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(status, message, data);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("status", status)
				.add("message", message).add("data", data).toString();
	}

}
