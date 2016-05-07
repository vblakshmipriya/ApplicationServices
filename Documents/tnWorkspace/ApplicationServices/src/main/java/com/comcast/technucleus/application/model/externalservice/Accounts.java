package com.comcast.technucleus.application.model.externalservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;;

// TODO: Auto-generated Javadoc
/**
 * The Class Accounts.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Accounts {

	/** The Customer detail. */
	private CustomerDetail CustomerDetail;

	/**
	 * Instantiates a new accounts.
	 */
	public Accounts() {

	}

	/**
	 * Instantiates a new accounts.
	 *
	 * @param CustomerDetail the customer detail
	 */
	public Accounts(
			@JsonProperty("CustomerDetail") CustomerDetail CustomerDetail) {

		this.CustomerDetail = CustomerDetail;

	}

	/**
	 * Gets the customer detail.
	 *
	 * @return the customer detail
	 */
	public CustomerDetail getCustomerDetail() {
		return CustomerDetail;
	}

	/**
	 * Sets the customer detail.
	 *
	 * @param CustomerDetail the new customer detail
	 */
	public void setCustomerDetail(CustomerDetail CustomerDetail) {
		this.CustomerDetail = CustomerDetail;
	}

}
