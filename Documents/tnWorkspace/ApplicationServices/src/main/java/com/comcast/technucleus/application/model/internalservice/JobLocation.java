package com.comcast.technucleus.application.model.internalservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobLocation {
	
	private String PowerSupply;
	private String RouteCriteria;
	private String Node;
	private String HouseStatus;
	private String AddrId;
	private String BldgTypeCd;
	private String MapCode;
	private String TapStatusCd;
	private String ManagementArea;
	private String CensusCd;
	private String HookUpType;
	private String DropLocationCd;
	private String HeadendCd;
	private String PoleNum;
	private String BridgerAddress;
	private String Amplifier;
	private String ServiceArea;
	private String HouseMisc;
	private String DropLocationType;
	private String Latitude;
	private String Longitude;
	private Address Address;
	
	
	public String getPowerSupply() {
		return PowerSupply;
	}
	public void setPowerSupply(String powerSupply) {
		PowerSupply = powerSupply;
	}
	public String getRouteCriteria() {
		return RouteCriteria;
	}
	public void setRouteCriteria(String routeCriteria) {
		RouteCriteria = routeCriteria;
	}
	public String getNode() {
		return Node;
	}
	public void setNode(String node) {
		Node = node;
	}
	public String getHouseStatus() {
		return HouseStatus;
	}
	public void setHouseStatus(String houseStatus) {
		HouseStatus = houseStatus;
	}
	public String getAddrId() {
		return AddrId;
	}
	public void setAddrId(String addrId) {
		AddrId = addrId;
	}
	public String getBldgTypeCd() {
		return BldgTypeCd;
	}
	public void setBldgTypeCd(String bldgTypeCd) {
		BldgTypeCd = bldgTypeCd;
	}
	public String getMapCode() {
		return MapCode;
	}
	public void setMapCode(String mapCode) {
		MapCode = mapCode;
	}
	public String getTapStatusCd() {
		return TapStatusCd;
	}
	public void setTapStatusCd(String tapStatusCd) {
		TapStatusCd = tapStatusCd;
	}
	public String getManagementArea() {
		return ManagementArea;
	}
	public void setManagementArea(String managementArea) {
		ManagementArea = managementArea;
	}
	public String getCensusCd() {
		return CensusCd;
	}
	public void setCensusCd(String censusCd) {
		CensusCd = censusCd;
	}
	public String getHookUpType() {
		return HookUpType;
	}
	public void setHookUpType(String hookUpType) {
		HookUpType = hookUpType;
	}
	public String getDropLocationCd() {
		return DropLocationCd;
	}
	public void setDropLocationCd(String dropLocationCd) {
		DropLocationCd = dropLocationCd;
	}
	public String getHeadendCd() {
		return HeadendCd;
	}
	public void setHeadendCd(String headendCd) {
		HeadendCd = headendCd;
	}
	public String getPoleNum() {
		return PoleNum;
	}
	public void setPoleNum(String poleNum) {
		PoleNum = poleNum;
	}
	public String getBridgerAddress() {
		return BridgerAddress;
	}
	public void setBridgerAddress(String bridgerAddress) {
		BridgerAddress = bridgerAddress;
	}
	public String getAmplifier() {
		return Amplifier;
	}
	public void setAmplifier(String amplifier) {
		Amplifier = amplifier;
	}
	public String getServiceArea() {
		return ServiceArea;
	}
	public void setServiceArea(String serviceArea) {
		ServiceArea = serviceArea;
	}
	public String getHouseMisc() {
		return HouseMisc;
	}
	public void setHouseMisc(String houseMisc) {
		HouseMisc = houseMisc;
	}
	public String getDropLocationType() {
		return DropLocationType;
	}
	public void setDropLocationType(String dropLocationType) {
		DropLocationType = dropLocationType;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public Address getAddress() {
		return Address;
	}
	public void setAddress(Address address) {
		Address = address;
	}
}
