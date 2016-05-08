package com.comcast.technucleus.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class Devices.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Devices {
	
	/** The mac address. */
	private String macAddress;

    /** The integrity score. */
    private String integrityScore;

    /** The health indicator. */
    private String healthIndicator;
    
    /**
     * Instantiates a new devices.
     */
    public Devices() {
    	
    }
    
    /**
     * Instantiates a new devices.
     *
     * @param macAddress the mac address
     * @param integrityScore the integrity score
     * @param healthIndicator the health indicator
     */
    public Devices(@JsonProperty("macAddress") String macAddress,
    		@JsonProperty("integrityScore") String integrityScore,
    		@JsonProperty("healthIndicator") String healthIndicator) {
    	
    	this.macAddress = macAddress;
    	this.integrityScore = integrityScore;
    	this.healthIndicator = healthIndicator;
    }

    /**
     * Gets the mac address.
     *
     * @return the mac address
     */
    public String getMacAddress ()
    {
        return macAddress;
    }

    /**
     * Sets the mac address.
     *
     * @param macAddress the new mac address
     */
    public void setMacAddress (String macAddress)
    {
        this.macAddress = macAddress;
    }

    /**
     * Gets the integrity score.
     *
     * @return the integrity score
     */
    public String getIntegrityScore ()
    {
        return integrityScore;
    }

    /**
     * Sets the integrity score.
     *
     * @param integrityScore the new integrity score
     */
    public void setIntegrityScore (String integrityScore)
    {
        this.integrityScore = integrityScore;
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

}
