package com.comcast.technucleus.application.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class Nodes.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Nodes {

	private String date;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	private String id;
	private String boxSerialNumber;
	private String setTopBoxMac;
	private String manufacturer;
	private String deviceTypeIdentifier;
	private String XCompatible;
	private String deviceModel;
	private String deviceStatus;
	private String nodeType;
	private String boxIdentifier;
	private String cableModemMac;
	private String friendlyName;
	private String ipAddress;
	private String overallDeviceHealth;
	private String deviceImageURL;
	private String deviceHits;
	private String deviceTypeHitKey;
	private String cabelCardSerialNumber;
	private String outlet;
	


	/** The diagnostics. */
	private List<Diagnostics> diagnostics;
	
	/** The x1 known issues. */
	private X1KnowIssues x1KnownIssues;

	/**
	 * Instantiates a new nodes.
	 */
	public Nodes(){

	}

	/**
	 * Instantiates a new nodes.
	 *
	 * @param id the id
	 * @param boxSerialNumber the box serial number
	 * @param setTopBoxMac the set top box mac
	 * @param manufacturer the manufacturer
	 * @param deviceTypeIdentifier the device type identifier
	 * @param XCompatible the x compatible
	 * @param deviceModel the device model
	 * @param deviceStatus the device status
	 * @param nodeType the node type
	 * @param boxIdentifier the box identifier
	 * @param diagnostics the diagnostics
	 */
	public Nodes(@JsonProperty("id") String id,
			@JsonProperty("boxSerialNumber") String boxSerialNumber,
			@JsonProperty("setTopBoxMac") String setTopBoxMac,
			@JsonProperty("manufacturer") String manufacturer,
			@JsonProperty("deviceTypeIdentifier") String deviceTypeIdentifier,
			@JsonProperty("XCompatible") String XCompatible,
			@JsonProperty("deviceModel") String deviceModel,
			@JsonProperty("deviceStatus") String deviceStatus,
			@JsonProperty("nodeType") String nodeType,
			@JsonProperty("boxIdentifier") String boxIdentifier,
			@JsonProperty("friendlyName") String friendlyName,
			@JsonProperty("cableModemMac")String cableModemMac,
			@JsonProperty("ipAddress")String ipAddress,
			@JsonProperty("overallDeviceHealth")String overallDeviceHealth,
			@JsonProperty("diagnostics") List<Diagnostics> diagnostics,
			@JsonProperty("date") String date,
			@JsonProperty("deviceHits") String deviceHits,
			@JsonProperty("deviceTypeHitKey")String deviceTypeHitKey,
			@JsonProperty("cabelCardSerialNumber")String cabelCardSerialNumber,
			@JsonProperty("outlet")String outlet) {

		this.id = id;
		this.boxSerialNumber = boxSerialNumber;
		this.setTopBoxMac = setTopBoxMac;
		this.manufacturer = manufacturer;
		this.deviceTypeIdentifier = deviceTypeIdentifier;
		this.XCompatible = XCompatible;
		this.deviceModel = deviceModel;
		this.deviceStatus = deviceStatus;
		this.nodeType = nodeType;
		this.boxIdentifier = boxIdentifier;
		this.friendlyName = friendlyName;
		this.cableModemMac = cableModemMac;
		this.ipAddress = ipAddress;
		this.overallDeviceHealth = overallDeviceHealth;
		this.diagnostics = diagnostics;
		this.date = date;
		this.deviceHits = deviceHits;
		this.deviceTypeHitKey = deviceTypeHitKey;
		this.cabelCardSerialNumber = cabelCardSerialNumber;
		this.outlet = outlet;
	}

	public String getDeviceTypeHitKey() {
		return deviceTypeHitKey;
	}

	public void setDeviceTypeHitKey(String deviceTypeHitKey) {
		this.deviceTypeHitKey = deviceTypeHitKey;
	}

	public String getCabelCardSerialNumber() {
		return cabelCardSerialNumber;
	}

	public void setCabelCardSerialNumber(String cabelCardSerialNumber) {
		this.cabelCardSerialNumber = cabelCardSerialNumber;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId ()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId (String id)
	{
		this.id = id;
	}

	/**
	 * Gets the box serial number.
	 *
	 * @return the box serial number
	 */
	public String getBoxSerialNumber ()
	{
		return boxSerialNumber;
	}

	/**
	 * Sets the box serial number.
	 *
	 * @param boxSerialNumber the new box serial number
	 */
	public void setBoxSerialNumber (String boxSerialNumber)
	{
		this.boxSerialNumber = boxSerialNumber;
	}

	/**
	 * Gets the sets the top box mac.
	 *
	 * @return the sets the top box mac
	 */
	public String getSetTopBoxMac ()
	{
		return setTopBoxMac;
	}

	/**
	 * Sets the sets the top box mac.
	 *
	 * @param setTopBoxMac the new sets the top box mac
	 */
	public void setSetTopBoxMac (String setTopBoxMac)
	{
		this.setTopBoxMac = setTopBoxMac;
	}

	/**
	 * Gets the manufacturer.
	 *
	 * @return the manufacturer
	 */
	public String getManufacturer ()
	{
		return manufacturer;
	}

	/**
	 * Sets the manufacturer.
	 *
	 * @param manufacturer the new manufacturer
	 */
	public void setManufacturer (String manufacturer)
	{
		this.manufacturer = manufacturer;
	}

	/**
	 * Gets the device type identifier.
	 *
	 * @return the device type identifier
	 */
	public String getDeviceTypeIdentifier ()
	{
		return deviceTypeIdentifier;
	}

	/**
	 * Sets the device type identifier.
	 *
	 * @param deviceTypeIdentifier the new device type identifier
	 */
	public void setDeviceTypeIdentifier (String deviceTypeIdentifier)
	{
		this.deviceTypeIdentifier = deviceTypeIdentifier;
	}

	/**
	 * Gets the x compatible.
	 *
	 * @return the x compatible
	 */
	public String getXCompatible ()
	{
		return XCompatible;
	}

	/**
	 * Sets the x compatible.
	 *
	 * @param XCompatible the new x compatible
	 */
	public void setXCompatible (String XCompatible)
	{
		this.XCompatible = XCompatible;
	}

	/**
	 * Gets the device model.
	 *
	 * @return the device model
	 */
	public String getDeviceModel ()
	{
		return deviceModel;
	}

	/**
	 * Sets the device model.
	 *
	 * @param deviceModel the new device model
	 */
	public void setDeviceModel (String deviceModel)
	{
		this.deviceModel = deviceModel;
	}

	/**
	 * Gets the device status.
	 *
	 * @return the device status
	 */
	public String getDeviceStatus ()
	{
		return deviceStatus;
	}

	/**
	 * Sets the device status.
	 *
	 * @param deviceStatus the new device status
	 */
	public void setDeviceStatus (String deviceStatus)
	{
		this.deviceStatus = deviceStatus;
	}

	/**
	 * Gets the node type.
	 *
	 * @return the node type
	 */
	public String getNodeType ()
	{
		return nodeType;
	}

	/**
	 * Sets the node type.
	 *
	 * @param nodeType the new node type
	 */
	public void setNodeType (String nodeType)
	{
		this.nodeType = nodeType;
	}

	/**
	 * Gets the box identifier.
	 *
	 * @return the box identifier
	 */
	public String getBoxIdentifier ()
	{
		return boxIdentifier;
	}

	/**
	 * Sets the box identifier.
	 *
	 * @param boxIdentifier the new box identifier
	 */
	public void setBoxIdentifier (String boxIdentifier)
	{
		this.boxIdentifier = boxIdentifier;
	}


	/**
	 * Gets the x1 known issues.
	 *
	 * @return the x1 known issues
	 */
	public X1KnowIssues getX1KnownIssues() {
		return x1KnownIssues;
	}

	/**
	 * Sets the x1 known issues.
	 *
	 * @param x1KnownIssues the new x1 known issues
	 */
	public void setX1KnownIssues(X1KnowIssues x1KnownIssues) {
		this.x1KnownIssues = x1KnownIssues;
	}

	/**
	 * Gets the diagnostics.
	 *
	 * @return the diagnostics
	 */
	public List<Diagnostics> getDiagnostics() {
		return diagnostics;
	}

	/**
	 * Sets the diagnostics.
	 *
	 * @param diagnostics the new diagnostics
	 */
	public void setDiagnostics(List<Diagnostics> diagnostics) {
		this.diagnostics = diagnostics;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	public String getCableModemMac() {
		return cableModemMac;
	}

	public void setCableModemMac(String cableModemMac) {
		this.cableModemMac = cableModemMac;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public String getOverallDeviceHealth() {
		return overallDeviceHealth;
	}

	public void setOverallDeviceHealth(String overallDeviceHealth) {
		this.overallDeviceHealth = overallDeviceHealth;
	}
	
	public String getDeviceImageURL() {
		return deviceImageURL;
	}

	public void setDeviceImageURL(String deviceImageURL) {
		this.deviceImageURL = deviceImageURL;
	}
	
	public String getDeviceHits() {
		return deviceHits;
	}

	public void setDeviceHits(String deviceHits) {
		this.deviceHits = deviceHits;
	}

	public String getDeviceType() {
		return deviceTypeHitKey;
	}

	public void setDeviceType(String deviceType) {
		this.deviceTypeHitKey = deviceType;
	}
	
	public String getOutlet() {
		return outlet;
	}

	public void setOutlet(String outlet) {
		this.outlet = outlet;
	}
}
