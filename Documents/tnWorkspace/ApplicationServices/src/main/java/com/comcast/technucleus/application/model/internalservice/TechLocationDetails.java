package com.comcast.technucleus.application.model.internalservice;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;;

/******************************************************************************************************
 * File Name : TechLocationDetails.java 
 * 
 * Description : TechLocationDetails is collection to hold the GPS request
 *
 *****************************************************************************************************/
@JsonIgnoreProperties(ignoreUnknown=true)
public class TechLocationDetails {
	
	private String timeStamp;

	private String deviceId;

	private String gpsProviderName;

	private String latitude;

	private String longitude;
	    
	private String motionStatus;

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}



	public String getGpsProviderName() {
		return gpsProviderName;
	}

	public void setGpsProviderName(String gpsProviderName) {
		this.gpsProviderName = gpsProviderName;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getMotionStatus() {
		return motionStatus;
	}

	public void setMotionStatus(String motionStatus) {
		this.motionStatus = motionStatus;
	}

	
	
	    
}
