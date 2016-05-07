package com.comcast.technucleus.application.services.client;

import com.comcast.technucleus.application.response.data.Eisresultset;

public interface EnterpriseInventoryServiceClient {

	Eisresultset getTechnicianBuffer(String ntID);

}
