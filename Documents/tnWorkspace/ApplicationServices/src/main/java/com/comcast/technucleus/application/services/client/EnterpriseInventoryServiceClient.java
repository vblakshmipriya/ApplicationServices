package com.comcast.technucleus.application.services.client;

import com.comcast.technucleus.application.model.Eisresultset;

public interface EnterpriseInventoryServiceClient {

	Eisresultset getTechnicianBuffer(String ntID);

}
