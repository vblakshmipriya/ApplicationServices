package com.comcast.technucleus.application.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class IntegrityScoreResponse.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IntegrityScoreResponse {

	/** The home integrity score. */
	private String homeIntegrityScore;

	/** The devices. */
	private List<Devices> devices;


	/** The health indicator. */
	private String healthIndicator;
	
	/** The health indicator. */
	private String retrieved_on_ts;
	
	public String getRetrieved_on_ts() {
		return retrieved_on_ts;
	}

	public void setRetrieved_on_ts(String retrieved_on_ts) {
		this.retrieved_on_ts = retrieved_on_ts;
	}

	/**
	 * Instantiates a new integrity score response.
	 */
	public IntegrityScoreResponse() {
		
	}

	/**
	 * Instantiates a new integrity score response.
	 *
	 * @param homeIntegrityScore the home integrity score
	 * @param devices the devices
	 * @param healthIndicator the health indicator
	 */
	public IntegrityScoreResponse(@JsonProperty("homeIntegrityScore") String homeIntegrityScore,
			@JsonProperty("devices") List<Devices> devices,
			@JsonProperty("healthIndicator") String healthIndicator) {

		this.homeIntegrityScore = homeIntegrityScore;
		this.devices = devices;
		this.healthIndicator = healthIndicator;

	}

	/**
	 * Gets the home integrity score.
	 *
	 * @return the home integrity score
	 */
	public String getHomeIntegrityScore ()
	{
		return homeIntegrityScore;
	}

	/**
	 * Sets the home integrity score.
	 *
	 * @param homeIntegrityScore the new home integrity score
	 */
	public void setHomeIntegrityScore (String homeIntegrityScore)
	{
		this.homeIntegrityScore = homeIntegrityScore;
	}



	/**
	 * Gets the health indicator.
	 *
	 * @return the health indicator
	 */
	public String getHealthIndicator ()
	{
		return healthIndicator;
	}

	/**
	 * Sets the health indicator.
	 *
	 * @param healthIndicator the new health indicator
	 */
	public void setHealthIndicator (String healthIndicator)
	{
		this.healthIndicator = healthIndicator;
	}

	/**
	 * Gets the devices.
	 *
	 * @return the devices
	 */
	public List<Devices> getDevices() {
		return devices;
	}

	/**
	 * Sets the devices.
	 *
	 * @param devices the new devices
	 */
	public void setDevices(List<Devices> devices) {
		this.devices = devices;
	}

}
