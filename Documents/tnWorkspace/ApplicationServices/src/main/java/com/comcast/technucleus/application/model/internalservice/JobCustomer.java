package com.comcast.technucleus.application.model.internalservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobCustomer {
	
	private String AccountId;
	private String FirstName;
	private String CustomerId;
	private String HomePhoneNum;
	private String VipCd;
	private String LastName;
	private String EmailAddr;
	private String WorkPhoneNum;
	private String RegistrationCd;
	private Address Address;
	
	public String getAccountId() {
		return AccountId;
	}
	public String getFirstName() {
		return FirstName;
	}
	public String getCustomerId() {
		return CustomerId;
	}
	public String getHomePhoneNum() {
		return HomePhoneNum;
	}
	public String getVipCd() {
		return VipCd;
	}
	public String getLastName() {
		return LastName;
	}
	public String getEmailAddr() {
		return EmailAddr;
	}
	public String getWorkPhoneNum() {
		return WorkPhoneNum;
	}
	public String getRegistrationCd() {
		return RegistrationCd;
	}
	public Address getAddress() {
		return Address;
	}
	public void setAccountId(String accountId) {
		AccountId = accountId;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}
	public void setHomePhoneNum(String homePhoneNum) {
		HomePhoneNum = homePhoneNum;
	}
	public void setVipCd(String vipCd) {
		VipCd = vipCd;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public void setEmailAddr(String emailAddr) {
		EmailAddr = emailAddr;
	}
	public void setWorkPhoneNum(String workPhoneNum) {
		WorkPhoneNum = workPhoneNum;
	}
	public void setRegistrationCd(String registrationCd) {
		RegistrationCd = registrationCd;
	}
	public void setAddress(Address address) {
		Address = address;
	}
	


}
