package com.comcast.technucleus.application.model;

/******************************************************************************************************
 * File Name : DeviceHitRequest.java 
 * 
 * Description : This class is a request object to submit device hit
 * 
 *****************************************************************************************************/
public class DeviceHitRequest {
	
	private String cableCardSerialNumber;
	private String hitType;
	private String accountNumber;
	private String outlet;
	
	public String getOutlet() {
		return outlet;
	}
	public void setOutlet(String outlet) {
		this.outlet = outlet;
	}
	public String getCableCardSerialNumber() {
		return cableCardSerialNumber;
	}
	public void setCableCardSerialNumber(String cableCardSerialNumber) {
		this.cableCardSerialNumber = cableCardSerialNumber;
	}
	public String getHitType() {
		return hitType;
	}
	public void setHitType(String hitType) {
		this.hitType = hitType;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
