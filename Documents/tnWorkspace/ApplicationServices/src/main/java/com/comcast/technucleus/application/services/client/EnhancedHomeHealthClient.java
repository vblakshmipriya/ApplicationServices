/*
 * @author
 * @date Jun 9, 2015
 * @project TechNucleus
 */
package com.comcast.technucleus.application.services.client;

import com.comcast.technucleus.application.model.AccountDetails;
import com.comcast.technucleus.application.model.DeviceHitRequest;
import com.comcast.technucleus.application.model.DevicesInfo;
import com.comcast.technucleus.application.model.IntegrityScoreResponse;
import com.comcast.technucleus.application.model.X1KnowIssues;

/******************************************************************************************************
 * File Name : DeviceService.java 
 * 
 * Description : This interface allows to 
 * 				 1. submit device hits
 * 				 2. get integrity score
 * 				 3. get node health by account number
 * 				 4. submit health to customer timeline
 * 				 5. get account information
 *
 *****************************************************************************************************/
public interface EnhancedHomeHealthClient {

	/**
	 * Gets the integrity score.
	 *
	 * @param accountNumber the account number
	 * @return the integrity score
	 */
	public IntegrityScoreResponse getIntegrityScore(String accountNumber);

	public String getNodeHealthByAccounNumber(String accountNumber);
	
	
	/**To POST HIC score to the CustomerTimeLine service/Kafka.
	 * @param acctNum
	 * @param workOrderNum
	 */
	public void  postToCustTimeLine(String acctNum,  String workOrderNum , IntegrityScoreResponse response );
	
	/**
	 * Gets the devices.
	 *
	 * @param accountNumber the account number
	 * @return the devices
	 */
	public DevicesInfo getDevices(String accountNumber, String boxSerialNumber, boolean isRetestSelected);
	

	/**
	 * Gets the account info.
	 *
	 * @param query the query
	 * @return the account info
	 */
	public AccountDetails getAccountInfo(String query);
	
	public X1KnowIssues getX1KnownIssues();
	
	public void submitDeviceHit(DeviceHitRequest deviceHitRequest);
	
}
