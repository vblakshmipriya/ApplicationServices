package com.comcast.technucleus.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TechDetails {


	private String businessUnit;
	private String workOrderNum;
	private String wFXTechLogin;
	private String jobNum;
	private String jobTypeCd;
	private String scheduleDate;
	private String technicianNum;
	private String dispatcherStatusCd;
	private String jobStartDateTime;
	private String techStatus;
	private String accountNumber;
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getTechStatus() {
		return techStatus;
	}
	public void setTechStatus(String techStatus) {
		this.techStatus = techStatus;
	}
	public String getBusinessUnit() {
		return businessUnit;
	}
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}
	public String getWorkOrderNum() {
		return workOrderNum;
	}
	public void setWorkOrderNum(String workOrderNum) {
		this.workOrderNum = workOrderNum;
	}
	public String getwFXTechLogin() {
		return wFXTechLogin;
	}
	public void setwFXTechLogin(String wFXTechLogin) {
		this.wFXTechLogin = wFXTechLogin;
	}
	public String getJobNum() {
		return jobNum;
	}
	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}
	public String getJobTypeCd() {
		return jobTypeCd;
	}
	public void setJobTypeCd(String jobTypeCd) {
		this.jobTypeCd = jobTypeCd;
	}
	public String getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public String getTechnicianNum() {
		return technicianNum;
	}
	public void setTechnicianNum(String technicianNum) {
		this.technicianNum = technicianNum;
	}
	public String getDispatcherStatusCd() {
		return dispatcherStatusCd;
	}
	public void setDispatcherStatusCd(String dispatcherStatusCd) {
		this.dispatcherStatusCd = dispatcherStatusCd;
	}
	public String getJobStartDateTime() {
		return jobStartDateTime;
	}
	public void setJobStartDateTime(String jobStartDateTime) {
		this.jobStartDateTime = jobStartDateTime;
	}
}
