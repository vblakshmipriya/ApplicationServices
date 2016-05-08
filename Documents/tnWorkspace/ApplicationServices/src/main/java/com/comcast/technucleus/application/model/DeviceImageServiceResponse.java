package com.comcast.technucleus.application.model;

import java.util.Map;

public class DeviceImageServiceResponse  {
	
	private Map<String, DeviceImage>  deviceImageList;

	public Map<String, DeviceImage> getDevicesImageMap() {
		return deviceImageList;
	}

	public void setDeviceImageMap(Map<String, DeviceImage> deviceImageMap) {
		this.deviceImageList = deviceImageMap;
	}


}
