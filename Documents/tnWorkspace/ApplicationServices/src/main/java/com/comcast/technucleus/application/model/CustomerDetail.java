package com.comcast.technucleus.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerDetail.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDetail {
	
	/** The last name. */
	private String lastName;

    /** The account number. */
    private String accountNumber;

    /** The display account number. */
    private String displayAccountNumber;

    /** The telephone number. */
    private String telephoneNumber;

    /** The zip code. */
    private String zipCode;

    /** The state. */
    private String state;

    /** The postal address. */
    private String postalAddress;

    /** The first name. */
    private String firstName;

    /** The city. */
    private String city;
    
    /**
     * Instantiates a new customer detail.
     */
    public CustomerDetail(){
    	
    }
    
	/**
	 * Instantiates a new customer detail.
	 *
	 * @param lastName the last name
	 * @param accountNumber the account number
	 * @param displayAccountNumber the display account number
	 * @param telephoneNumber the telephone number
	 * @param zipCode the zip code
	 * @param state the state
	 * @param postalAddress the postal address
	 * @param firstName the first name
	 * @param city the city
	 */
	public CustomerDetail(@JsonProperty("lastName") String lastName,
			@JsonProperty("accountNumber") String accountNumber,
			@JsonProperty("displayAccountNumber") String displayAccountNumber,
			@JsonProperty("telephoneNumber") String telephoneNumber,
			@JsonProperty("zipCode") String zipCode,
			@JsonProperty("state") String state,
			@JsonProperty("postalAddress") String postalAddress,
			@JsonProperty("firstName") String firstName,
			@JsonProperty("city") String city) {

		this.accountNumber = accountNumber;
		this.lastName = lastName;
		this.displayAccountNumber = accountNumber;
		this.telephoneNumber = telephoneNumber;
		this.zipCode = zipCode;
		this.state = state;
		this.postalAddress = postalAddress;
		this.firstName = firstName;
		this.city = city;

	} 

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName ()
    {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * Gets the account number.
     *
     * @return the account number
     */
    public String getAccountNumber ()
    {
        return accountNumber;
    }

    /**
     * Sets the account number.
     *
     * @param accountNumber the new account number
     */
    public void setAccountNumber (String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets the display account number.
     *
     * @return the display account number
     */
    public String getDisplayAccountNumber ()
    {
        return displayAccountNumber;
    }

    /**
     * Sets the display account number.
     *
     * @param displayAccountNumber the new display account number
     */
    public void setDisplayAccountNumber (String displayAccountNumber)
    {
        this.displayAccountNumber = displayAccountNumber;
    }

    /**
     * Gets the telephone number.
     *
     * @return the telephone number
     */
    public String getTelephoneNumber ()
    {
        return telephoneNumber;
    }

    /**
     * Sets the telephone number.
     *
     * @param telephoneNumber the new telephone number
     */
    public void setTelephoneNumber (String telephoneNumber)
    {
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * Gets the zip code.
     *
     * @return the zip code
     */
    public String getZipCode ()
    {
        return zipCode;
    }

    /**
     * Sets the zip code.
     *
     * @param zipCode the new zip code
     */
    public void setZipCode (String zipCode)
    {
        this.zipCode = zipCode;
    }

    /**
     * Gets the state.
     *
     * @return the state
     */
    public String getState ()
    {
        return state;
    }

    /**
     * Sets the state.
     *
     * @param state the new state
     */
    public void setState (String state)
    {
        this.state = state;
    }

    /**
     * Gets the postal address.
     *
     * @return the postal address
     */
    public String getPostalAddress ()
    {
        return postalAddress;
    }

    /**
     * Sets the postal address.
     *
     * @param postalAddress the new postal address
     */
    public void setPostalAddress (String postalAddress)
    {
        this.postalAddress = postalAddress;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName ()
    {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * Gets the city.
     *
     * @return the city
     */
    public String getCity ()
    {
        return city;
    }

    /**
     * Sets the city.
     *
     * @param city the new city
     */
    public void setCity (String city)
    {
        this.city = city;
    }
}
