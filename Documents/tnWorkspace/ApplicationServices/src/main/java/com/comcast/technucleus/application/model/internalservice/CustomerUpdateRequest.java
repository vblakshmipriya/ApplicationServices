package com.comcast.technucleus.application.model.internalservice;

public class CustomerUpdateRequest {
	private String businessUnit;
	private String accountId;
	private String customerId;
	private String workOrderNum;
	private String  techNum;
	private String fulfillmentCenter;
	private String scheduleDate;
	private ChangeRequest[] changeRequest;
	public String getTechNum() {
		return techNum;
	}
	public void setTechNum(String techNum) {
		this.techNum = techNum;
	}
	public String getFulfillmentCenter() {
		return fulfillmentCenter;
	}
	public void setFulfillmentCenter(String fulfillmentCenter) {
		this.fulfillmentCenter = fulfillmentCenter;
	}
	public String getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getWorkOrderNum() {
		return workOrderNum;
	}
	public void setWorkOrderNum(String workOrderNum) {
		this.workOrderNum = workOrderNum;
	}
	public ChangeRequest[] getChangeRequest() {
		return changeRequest;
	}
	public void setChangeRequest(ChangeRequest[] changeRequest) {
		this.changeRequest = changeRequest;
	}
	public String getBusinessUnit() {
		return businessUnit;
	}
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}
}
