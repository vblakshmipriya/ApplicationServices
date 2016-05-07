package com.comcast.technucleus.application.model.internalservice;

import java.util.List;

/**
 * @author TechNuke
 * 
 *         This will be internalstorage map for the actionCode and productCode
 *
 */
public class InternalBillingMapList {

	public enum SERVICES {
		NEW, EXISTING
	};

	private List<String> billingCodes;

	public List<String> getProductCode() {
		return billingCodes;
	}

	public void setProductCode(List<String> productCode) {
		this.billingCodes = productCode;
	}


}
