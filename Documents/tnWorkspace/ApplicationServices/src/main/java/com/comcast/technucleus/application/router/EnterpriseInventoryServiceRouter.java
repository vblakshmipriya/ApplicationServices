package com.comcast.technucleus.application.router;

import com.comcast.technucleus.application.response.data.Eisresultset;

public interface EnterpriseInventoryServiceRouter 
{
	public Eisresultset getTechnicianBuffer(String ntID);
}
