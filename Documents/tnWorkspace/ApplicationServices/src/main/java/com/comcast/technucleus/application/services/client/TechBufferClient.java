package com.comcast.technucleus.application.services.client;

import java.util.List;

import com.comcast.technucleus.application.model.TechBufferDevice;

public interface TechBufferClient 
{
	public List<TechBufferDevice> getBuffer(String techId , String fullFillmentCenter);
	public void addDevice(String techID, String fulfillmentCenter, String workOrderNumber, String sequenceNo,
			String scheduleDate, String deviceSerialNumber, TechBufferDevice device,String businessUnit);

}
