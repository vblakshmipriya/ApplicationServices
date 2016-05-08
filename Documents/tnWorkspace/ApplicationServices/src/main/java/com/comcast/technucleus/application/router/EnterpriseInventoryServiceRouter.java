package com.comcast.technucleus.application.router;

import com.comcast.technucleus.application.model.Eisresultset;

public interface EnterpriseInventoryServiceRouter 
{
	public Eisresultset getTechnicianBuffer(String ntID);
}
