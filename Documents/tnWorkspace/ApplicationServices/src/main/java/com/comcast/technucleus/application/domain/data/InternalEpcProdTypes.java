package com.comcast.technucleus.application.domain.data;

import java.io.Serializable;
import java.util.List;

public class InternalEpcProdTypes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ProductType;

	private List<InternalEpcProducts> producTypeList;

	public String getProductType() {
		return ProductType;
	}

	public void setProductType(String productType) {
		ProductType = productType;
	}

	public List<InternalEpcProducts> getProducTypeList() {
		return producTypeList;
	}

	public void setProducTypeList(List<InternalEpcProducts> producTypeList) {
		this.producTypeList = producTypeList;
	}

}
