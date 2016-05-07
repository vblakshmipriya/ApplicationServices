package com.comcast.technucleus.application.model.externalservice;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountDetails.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDetails {
	
	/** The accounts. */
	private List<Accounts> accounts;
	
	/**
	 * Instantiates a new account details.
	 */
	public AccountDetails(){
		
	}
	
	/**
	 * Instantiates a new account details.
	 *
	 * @param accounts the accounts
	 */
	public AccountDetails(@JsonProperty("accounts") List<Accounts> accounts) {

		this.accounts = accounts;

	}

	/**
	 * Gets the accounts.
	 *
	 * @return the accounts
	 */
	public List<Accounts> getAccounts() {
		return accounts;
	}

	/**
	 * Sets the accounts.
	 *
	 * @param accounts the new accounts
	 */
	public void setAccounts(List<Accounts> accounts) {
		this.accounts = accounts;
	}

	   

	}


