package com.comcast.technucleus.application.services.client;

import com.comcast.technucleus.application.model.internalservice.TechLocationDetails;
import com.comcast.technucleus.application.model.internalservice.TechNukeResponse;

public interface TechGpsLocationUpdateClient {
	
	public TechNukeResponse updateTechLocation(TechLocationDetails techLocationDetails, String accessToken);

}
