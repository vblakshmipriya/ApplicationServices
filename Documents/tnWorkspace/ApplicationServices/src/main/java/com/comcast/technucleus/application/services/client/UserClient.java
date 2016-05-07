package com.comcast.technucleus.application.services.client;

import com.comcast.technucleus.application.model.internalservice.TechDetails;

public interface UserClient {
	
	public String getTechCurrentStatus(String ntId);

	public String updateTechStatus(TechDetails techDetails, String ntId);

	public String updateWorkorderStatusToOnJob(TechDetails techDetails, String ntId);

	public String getComcastNowData(String ntId);

}
