package com.comcast.technucleus.application.response.data;

public class ContactInfo 
{
	private String type;
	private String comments;
	private String contactDate;
	private String source;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDate() {
		return contactDate;
	}
	public void setDate(String date) {
		this.contactDate = date;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	@Override
	public String toString() {
		return "ContactInfo [type=" + type + ", comments=" + comments + ", contactDate=" + contactDate + ", source="
				+ source + "]";
	}
}
