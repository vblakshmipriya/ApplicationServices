package com.comcast.technucleus.application.response.data;

import java.util.Map;

import com.comcast.technucleus.application.model.internalservice.DeviceImage;

public class MaestroDeviceImageResponse {

	private Map<String, DeviceImage>  deviceModelImageMap;

	public Map<String, DeviceImage> getDeviceImage() {
		return deviceModelImageMap;
	}

	public void setDeviceImageMap(Map<String, DeviceImage> deviceImageMap) {
		this.deviceModelImageMap = deviceImageMap;
	}
}
