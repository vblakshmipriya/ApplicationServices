package com.comcast.technucleus.application.services.client;

import com.comcast.technucleus.application.model.TechLocationDetails;
import com.comcast.technucleus.application.model.TechNukeResponse;

public interface TechGpsLocationUpdateClient {
	
	public TechNukeResponse updateTechLocation(TechLocationDetails techLocationDetails, String accessToken);

}
