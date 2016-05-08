package com.comcast.technucleus.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class Diagnostics.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Diagnostics {
	
	/** The description. */
	private String description;

    /** The name. */
    private String name;

    /** The value. */
    private String value;
    
    private String healthStatus;
    

	/**
     * Instantiates a new diagnostics.
     */
    public Diagnostics(){
    	
    }
    
    /**
     * Instantiates a new diagnostics.
     *
     * @param description the description
     * @param name the name
     * @param value the value
     */
    public Diagnostics(@JsonProperty("healthStatus") String healthStatus,
    		@JsonProperty("name") String name,
    		@JsonProperty("description") String description,
    		@JsonProperty("value") String value){
    	
    	this.healthStatus = healthStatus;
    	this.name = name;
    	this.description = description;
    	this.value = value;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription ()
    {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription (String description)
    {
        this.description = description;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName ()
    {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName (String name)
    {
        this.name = name;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue ()
    {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue (String value)
    {
        this.value = value;
    }
    
    public String getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}

}
