package com.comcast.technucleus.application.model.internalservice;

public class IOSScreen {

	private String id;
	
	private String description;
	
	private String value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "IOSScreen [id=" + id + ", description=" + description + ", value=" + value + "]";
	}
	
	
}
