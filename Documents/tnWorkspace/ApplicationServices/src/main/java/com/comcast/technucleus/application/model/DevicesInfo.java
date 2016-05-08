package com.comcast.technucleus.application.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class DevicesInfo.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DevicesInfo {

	/** The nodes. */
	private List<Nodes> nodes;

	/** The links. */
	private List<Links> links;
	
	private DeviceImageServiceResponse deviceImageServiceResponse;


	/**
	 * Instantiates a new devices info.
	 */
	public DevicesInfo(){

	}

	/**
	 * Instantiates a new devices info.
	 *
	 * @param nodes the nodes
	 * @param links the links
	 */
	public DevicesInfo(@JsonProperty("nodes") List<Nodes> nodes,
			@JsonProperty("links") List<Links> links) {

		this.nodes = nodes;
		this.links = links;
	}

	/**
	 * Gets the nodes.
	 *
	 * @return the nodes
	 */
	public List<Nodes> getNodes() {
		return nodes;
	}

	/**
	 * Sets the nodes.
	 *
	 * @param nodes the new nodes
	 */
	public void setNodes(List<Nodes> nodes) {
		this.nodes = nodes;
	}

	/**
	 * Gets the links.
	 *
	 * @return the links
	 */
	public List<Links> getLinks() {
		return links;
	}

	/**
	 * Sets the links.
	 *
	 * @param links the new links
	 */
	public void setLinks(List<Links> links) {
		this.links = links;
	}

	public DeviceImageServiceResponse getDeviceImageServiceResponse() {
		return deviceImageServiceResponse;
	}

	public void setDeviceImageServiceResponse(
			DeviceImageServiceResponse deviceImageServiceResponse) {
		this.deviceImageServiceResponse = deviceImageServiceResponse;
	}

}
