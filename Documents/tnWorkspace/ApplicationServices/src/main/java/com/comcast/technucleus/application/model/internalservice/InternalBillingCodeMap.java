package com.comcast.technucleus.application.model.internalservice;

/**
 * @author TechNuke
 * 
 * This will be internalstorage map for the actionCode and productCode
 *
 */
public class InternalBillingCodeMap {

	/* This is NONE / REMOVE / ADD */
	private String actionCode;

	private String productCode;
	
	private String wfaLOB;

	public String getWfaLOB() {
		return wfaLOB;
	}

	public void setWfaLOB(String wfaLOB) {
		this.wfaLOB = wfaLOB;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

}
