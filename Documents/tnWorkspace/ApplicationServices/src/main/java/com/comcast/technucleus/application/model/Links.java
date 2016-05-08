package com.comcast.technucleus.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class Links.
 */
public class Links {
	
	/** The rate. */
	private String rate;
	
	/** The source. */
	private String source;
	
	/** The target. */
	private String target;
	
	/** The type. */
	private String type;
	
	/**
	 * Instantiates a new links.
	 */
	public Links(){
		
	}
	
	/**
	 * Instantiates a new links.
	 *
	 * @param rate the rate
	 * @param source the source
	 * @param target the target
	 * @param type the type
	 */
	public Links(@JsonProperty("rate") String rate,
			@JsonProperty("source") String source,
			@JsonProperty("target") String target,
			@JsonProperty("type") String type){
		
		this.rate = rate;
		this.source = source;
		this.target = target;
		this.type = type;
	}
	
	
	/**
	 * Gets the rate.
	 *
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}

	/**
	 * Sets the rate.
	 *
	 * @param rate the new rate
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}

	/**
	 * Gets the source.
	 *
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Sets the source.
	 *
	 * @param source the new source
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * Gets the target.
	 *
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * Sets the target.
	 *
	 * @param target the new target
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}
}
