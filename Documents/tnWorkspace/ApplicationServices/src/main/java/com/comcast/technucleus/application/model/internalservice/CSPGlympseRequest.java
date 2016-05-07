package com.comcast.technucleus.application.model.internalservice;

public class CSPGlympseRequest {
	
	private String masterTicket;
	
	private String driverTicket;
	
	private String phase;
	
	private String workOrderNum;
	
	private String appointmentId;
	
	private String sessionKey;
	
	private String fullfillmentCenterId;
	
	private String accountNumber;
	
	private String matchMethod;
	
	private String geocodingPointLocation;
	
	private String type;

	public String getMatchMethod() {
		return matchMethod;
	}

	public void setMatchMethod(String matchMethod) {
		this.matchMethod = matchMethod;
	}

	public String getGeocodingPointLocation() {
		return geocodingPointLocation;
	}

	public void setGeocodingPointLocation(String geocodingPointLocation) {
		this.geocodingPointLocation = geocodingPointLocation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getFullfillmentCenterId() {
		return fullfillmentCenterId;
	}

	public void setFullfillmentCenterId(String fullfillmentCenterId) {
		this.fullfillmentCenterId = fullfillmentCenterId;
	}

	public String getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getWorkOrderNum() {
		return workOrderNum;
	}

	public void setWorkOrderNum(String workOrderNum) {
		this.workOrderNum = workOrderNum;
	}

	public String getMasterTicket() {
		return masterTicket;
	}

	public void setMasterTicket(String masterTicket) {
		this.masterTicket = masterTicket;
	}

	public String getDriverTicket() {
		return driverTicket;
	}

	public void setDriverTicket(String driverTicket) {
		this.driverTicket = driverTicket;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

}
