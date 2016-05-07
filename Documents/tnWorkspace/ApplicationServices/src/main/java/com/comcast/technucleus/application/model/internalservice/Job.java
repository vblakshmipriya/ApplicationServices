package com.comcast.technucleus.application.model.internalservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Job {

	private String CallFirstPhoneNum;
	private String BusinessUnit;
	private String CodAmount;
	private String InitialStartDateTime;
	private String LastChangeOper;
	private String JobTypeCd;
	private String ScheduleDate;
	private String TimeSlotEndTime;
	private String WorkOrderNum;

	public String getWorkOrderNum() {
		return WorkOrderNum;
	}

	public void setWorkOrderNum(String workOrderNum) {
		WorkOrderNum = workOrderNum;
	}

	public String getInitialStartDateTime() {
		return InitialStartDateTime;
	}

	public String getLastChangeOper() {
		return LastChangeOper;
	}

	public String getJobTypeCd() {
		return JobTypeCd;
	}

	public String getScheduleDate() {
		return ScheduleDate;
	}

	public String getTimeSlotEndTime() {
		return TimeSlotEndTime;
	}

	public void setInitialStartDateTime(String initialStartDateTime) {
		InitialStartDateTime = initialStartDateTime;
	}

	public void setLastChangeOper(String lastChangeOper) {
		LastChangeOper = lastChangeOper;
	}

	public void setJobTypeCd(String jobTypeCd) {
		JobTypeCd = jobTypeCd;
	}

	public void setScheduleDate(String scheduleDate) {
		ScheduleDate = scheduleDate;
	}

	public void setTimeSlotEndTime(String timeSlotEndTime) {
		TimeSlotEndTime = timeSlotEndTime;
	}

	public String getCodAmount() {
		return CodAmount;
	}

	public void setCodAmount(String codAmount) {
		CodAmount = codAmount;
	}

	public String getBusinessUnit() {
		return BusinessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		BusinessUnit = businessUnit;
	}

	public String getCallFirstPhoneNum() {
		return CallFirstPhoneNum;
	}

	public void setCallFirstPhoneNum(String callFirstPhoneNum) {
		CallFirstPhoneNum = callFirstPhoneNum;
	}

}
