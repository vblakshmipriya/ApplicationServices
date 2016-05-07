package com.comcast.technucleus.application.model.internalservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobEquipment {
	
	private String EquipModelCd;

    private String AssocDeviceComponent;

    private String AssocDeviceSerialNum;

    private String EquipTypeCd;

    private String OwnerCd;
    
    private String HostId;

    private String UsageReturnPath;

    private String SerialNum;

    private String DeviceAddress2;

    private String AssocDeviceType;

    private String DeviceAddress;

    private String EquipCategoryCd;

    private String ComponentCd;

    private String EquipStatusCd;

    private String EquipMake;

	private String SubType;

    private String SecurityDataId;

	public String getEquipModelCd() {
		return EquipModelCd;
	}

	public void setEquipModelCd(String equipModelCd) {
		EquipModelCd = equipModelCd;
	}

	public String getAssocDeviceComponent() {
		return AssocDeviceComponent;
	}

	public void setAssocDeviceComponent(String assocDeviceComponent) {
		AssocDeviceComponent = assocDeviceComponent;
	}

	public String getAssocDeviceSerialNum() {
		return AssocDeviceSerialNum;
	}

	public void setAssocDeviceSerialNum(String assocDeviceSerialNum) {
		AssocDeviceSerialNum = assocDeviceSerialNum;
	}

	public String getEquipTypeCd() {
		return EquipTypeCd;
	}

	public void setEquipTypeCd(String equipTypeCd) {
		EquipTypeCd = equipTypeCd;
	}

	public String getOwnerCd() {
		return OwnerCd;
	}

	public void setOwnerCd(String ownerCd) {
		OwnerCd = ownerCd;
	}

	public String getHostId() {
		return HostId;
	}

	public void setHostId(String hostId) {
		HostId = hostId;
	}

	public String getUsageReturnPath() {
		return UsageReturnPath;
	}

	public void setUsageReturnPath(String usageReturnPath) {
		UsageReturnPath = usageReturnPath;
	}

	public String getSerialNum() {
		return SerialNum;
	}

	public void setSerialNum(String serialNum) {
		SerialNum = serialNum;
	}

	public String getDeviceAddress2() {
		return DeviceAddress2;
	}

	public void setDeviceAddress2(String deviceAddress2) {
		DeviceAddress2 = deviceAddress2;
	}

	public String getAssocDeviceType() {
		return AssocDeviceType;
	}

	public void setAssocDeviceType(String assocDeviceType) {
		AssocDeviceType = assocDeviceType;
	}

	public String getDeviceAddress() {
		return DeviceAddress;
	}

	public void setDeviceAddress(String deviceAddress) {
		DeviceAddress = deviceAddress;
	}

	public String getEquipCategoryCd() {
		return EquipCategoryCd;
	}

	public void setEquipCategoryCd(String equipCategoryCd) {
		EquipCategoryCd = equipCategoryCd;
	}

	public String getComponentCd() {
		return ComponentCd;
	}

	public void setComponentCd(String componentCd) {
		ComponentCd = componentCd;
	}

	public String getEquipStatusCd() {
		return EquipStatusCd;
	}

	public void setEquipStatusCd(String equipStatusCd) {
		EquipStatusCd = equipStatusCd;
	}

	public String getEquipMake() {
		return EquipMake;
	}

	public void setEquipMake(String equipMake) {
		EquipMake = equipMake;
	}

	public String getSubType() {
		return SubType;
	}

	public void setSubType(String subType) {
		SubType = subType;
	}

	public String getSecurityDataId() {
		return SecurityDataId;
	}

	public void setSecurityDataId(String securityDataId) {
		SecurityDataId = securityDataId;
	}

}
