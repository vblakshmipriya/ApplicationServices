package com.comcast.technucleus.application.model;

// TODO: Auto-generated Javadoc
/**
 * The Class FileFormat.
 */
public class UserProfile {

	/** The name. */
	private String name;
	
	/** The nt id. */
	private String _id;
	
	/** The tech id. */
	private String techID;
	
	/** The fulfillment center. */
	private String fulfillmentCenter;
	
	/** The address line1. */
	private String addressLine1;
	
	/** The address line2. */
	private String addressLine2;
	
	/** The city. */
	private String city;
	
	/** The state. */
	private String state;
	
	/** The postal code. */
	private String postalCode;
	
	/** The Country. */
	private String country;
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	
	/**
	 * Gets the _id.
	 *
	 * @return the _id
	 */
	public String get_id() {
		return _id;
	}


	/**
	 * Sets the _id.
	 *
	 * @param _id the new _id
	 */
	public void set_id(String _id) {
		this._id = _id;
	}


	/**
	 * Gets the tech id.
	 *
	 * @return the tech id
	 */
	public String getTechID() {
		return techID;
	}


	/**
	 * Sets the tech id.
	 *
	 * @param techID the new tech id
	 */
	public void setTechID(String techID) {
		this.techID = techID;
	}


	/**
	 * Gets the fulfillment center.
	 *
	 * @return the fulfillment center
	 */
	public String getFulfillmentCenter() {
		return fulfillmentCenter;
	}


	/**
	 * Sets the fulfillment center.
	 *
	 * @param fulfillmentCenter the new fulfillment center
	 */
	public void setFulfillmentCenter(String fulfillmentCenter) {
		this.fulfillmentCenter = fulfillmentCenter;
	}

	
	/**
	 * Gets the address line1.
	 *
	 * @return the address line1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}


	/**
	 * Sets the address line1.
	 *
	 * @param addressLine1 the new address line1
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}


	/**
	 * Gets the address line2.
	 *
	 * @return the address line2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}


	/**
	 * Sets the address line2.
	 *
	 * @param addressLine2 the new address line2
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}


	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}


	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}


	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}


	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}


	/**
	 * Gets the postal code.
	 *
	 * @return the postal code
	 */
	public String getPostalCode() {
		return postalCode;
	}


	/**
	 * Sets the postal code.
	 *
	 * @param postalCode the new postal code
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}


	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}


	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserProfile other = (UserProfile) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return "UserProfile [name=" + name + ", _id=" + _id + ", techID=" + techID + ", fulfillmentCenter="
				+ fulfillmentCenter + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", city="
				+ city + ", state=" + state + ", postalCode=" + postalCode + ", Country=" + country + "]";
	}


}
