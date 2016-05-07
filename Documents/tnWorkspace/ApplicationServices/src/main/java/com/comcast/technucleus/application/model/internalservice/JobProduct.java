package com.comcast.technucleus.application.model.internalservice;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobProduct {

	private String AccountId;
	private String ActionCd;
	private String VoipPortNum;
	private String ParentProductCd;
	private String ProductLOB;
	private String Quantity;
	private String ProductTypeCd;
	private String FulfillmentJobNum;
	private String VoicePhoneNum;
	private List<ChildProduct> ChildProductList;
	
	
	public String getAccountId() {
		return AccountId;
	}
	public void setAccountId(String accountId) {
		AccountId = accountId;
	}
	public String getActionCd() {
		return ActionCd;
	}
	public void setActionCd(String actionCd) {
		ActionCd = actionCd;
	}
	public String getVoipPortNum() {
		return VoipPortNum;
	}
	public void setVoipPortNum(String voipPortNum) {
		VoipPortNum = voipPortNum;
	}
	public String getParentProductCd() {
		return ParentProductCd;
	}
	public void setParentProductCd(String parentProductCd) {
		ParentProductCd = parentProductCd;
	}
	public String getProductLOB() {
		return ProductLOB;
	}
	public void setProductLOB(String productLOB) {
		ProductLOB = productLOB;
	}
	public String getQuantity() {
		return Quantity;
	}
	public void setQuantity(String quantity) {
		Quantity = quantity;
	}
	public String getProductTypeCd() {
		return ProductTypeCd;
	}
	public void setProductTypeCd(String productTypeCd) {
		ProductTypeCd = productTypeCd;
	}
	public String getFulfillmentJobNum() {
		return FulfillmentJobNum;
	}
	public void setFulfillmentJobNum(String fulfillmentJobNum) {
		FulfillmentJobNum = fulfillmentJobNum;
	}
	public String getVoicePhoneNum() {
		return VoicePhoneNum;
	}
	public void setVoicePhoneNum(String voicePhoneNum) {
		VoicePhoneNum = voicePhoneNum;
	}
	public List<ChildProduct> getChildProductList() {
		return ChildProductList;
	}
	public void setChildProductList(List<ChildProduct> childProductList) {
		ChildProductList = childProductList;
	}
}
