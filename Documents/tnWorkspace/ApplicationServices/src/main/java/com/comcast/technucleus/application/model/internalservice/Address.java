package com.comcast.technucleus.application.model.internalservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

	private String City;
	private String ZipCode;
	private String AddrLine1;
	private String AddrLine2;
	private String State;
	
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getZipCode() {
		return ZipCode;
	}
	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}
	public String getAddrLine1() {
		return AddrLine1;
	}
	public void setAddrLine1(String addrLine1) {
		AddrLine1 = addrLine1;
	}
	public String getAddrLine2() {
		return AddrLine2;
	}
	public void setAddrLine2(String addrLine2) {
		AddrLine2 = addrLine2;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	
	
}
