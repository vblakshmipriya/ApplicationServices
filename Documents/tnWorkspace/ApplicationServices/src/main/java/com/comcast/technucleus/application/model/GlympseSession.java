package com.comcast.technucleus.application.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "glympse_Session_Details")
public class GlympseSession {

	@Id
	private String workOrderNum;
	private String driverTicket;
	private String masterTicket;
	private String appointmentId;
	private String sessionKey;
	private String phase;
	private boolean isGlympseSessionAvailable;
	private String matchMethod;
	private String inviteCode;
	private String geocodingPointLocation;
	private String type;
	
	public String getInviteCode() {
		return inviteCode;
	}
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
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

	public boolean isAvailable() {
		return isGlympseSessionAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isGlympseSessionAvailable = isAvailable;
	}

	public String getDriverTicket() {
		return driverTicket;
	}
	public void setDriverTicket(String driverTicket) {
		this.driverTicket = driverTicket;
	}
	public String getMasterTicket() {
		return masterTicket;
	}
	public void setMasterTicket(String masterTicket) {
		this.masterTicket = masterTicket;
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
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getId() {
		return workOrderNum;
	}
	public void setId(String id) {
		this.workOrderNum = id;
	}

	@Override
	public String toString() {
		return "GlympseSession [driverTicket=" + driverTicket 
				+ ", masterTicket=" + masterTicket 
				+ ", appointmentId=" + appointmentId 
				+ ", sessionKey=" + sessionKey 
				+ ", phase=" + phase 
				+ ", workOrderNum=" + workOrderNum 
				+ ", inviteCode=" + inviteCode
				+ ", type=" + type 
				+ ", isGlympseSessionAvailable=" + String.valueOf(isGlympseSessionAvailable) 
				+ "]";
	}

}
