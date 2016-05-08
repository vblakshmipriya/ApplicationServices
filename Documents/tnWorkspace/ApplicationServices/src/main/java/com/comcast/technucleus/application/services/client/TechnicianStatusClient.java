package com.comcast.technucleus.application.services.client;

import com.comcast.technucleus.application.model.TechDetails;
import com.comcast.technucleus.application.model.TechNukeResponse;

public interface TechnicianStatusClient {
	
	public TechNukeResponse techStatusUpdate(TechDetails techStatusUpdateRequest, String workOrderNumber, String accessToken);

}
