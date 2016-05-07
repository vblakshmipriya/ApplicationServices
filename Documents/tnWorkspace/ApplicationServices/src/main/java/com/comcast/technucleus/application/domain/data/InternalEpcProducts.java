package com.comcast.technucleus.application.domain.data;

import java.io.Serializable;
import java.util.List;

public class InternalEpcProducts implements Serializable {

	private static final long serialVersionUID = 1L;

	private String billingCode;

	private String producDesc;

	private String prodName;

	private String productType;

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getBillingCode() {
		return billingCode;
	}

	public void setBillingCode(String billingCode) {
		this.billingCode = billingCode;
	}

	public String getProducDesc() {
		return producDesc;
	}

	public void setProducDesc(String producDesc) {
		this.producDesc = producDesc;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

}
