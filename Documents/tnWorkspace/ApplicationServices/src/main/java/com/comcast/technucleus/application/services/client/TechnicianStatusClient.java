package com.comcast.technucleus.application.services.client;

import com.comcast.technucleus.application.model.internalservice.TechDetails;
import com.comcast.technucleus.application.model.internalservice.TechNukeResponse;

public interface TechnicianStatusClient {
	
	public TechNukeResponse techStatusUpdate(TechDetails techStatusUpdateRequest, String workOrderNumber, String accessToken);

}
