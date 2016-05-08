package com.comcast.technucleus.application.services.client;

import com.comcast.technucleus.application.model.TechDetails;
import com.mongodb.DBObject;

public interface UserClient {

	public String getTechCurrentStatus(String ntId);

	public String updateTechStatus(TechDetails techDetails, String ntId);

	public String updateWorkorderStatusToOnJob(TechDetails techDetails, String ntId);

	public String getComcastNowData(String ntId);

	/**
	 * Method return user details after successful login
	 * 
	 * @param ntId
	 * @return
	 */
	public DBObject getUserProfileByNtIdFromDb(String ntId);

}
