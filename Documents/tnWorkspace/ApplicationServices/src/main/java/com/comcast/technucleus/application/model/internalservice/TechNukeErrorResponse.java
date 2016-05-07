package com.comcast.technucleus.application.model.internalservice;

public class TechNukeErrorResponse {

	private String message;
	
	private String status;

	public TechNukeErrorResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
