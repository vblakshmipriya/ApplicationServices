package com.comcast.technucleus.application.domain.status;

public enum TechStatus {
	AVIALABLE,
	ENROUTE,
	ONJOB;
	public TechStatus getStatus(String status)
	{
		TechStatus result;
		switch(status)
		{
		case "AVIALABLE" : 
			result = AVIALABLE;
			break;
		case "ENROUTE" : 
			result = ENROUTE;
			break;
		case "ONJOB" : 
			result = ONJOB;
			break;
		default :
			throw new IllegalArgumentException("Invalid Tech Status: "+ status);
		}	
		return result;
	}

}