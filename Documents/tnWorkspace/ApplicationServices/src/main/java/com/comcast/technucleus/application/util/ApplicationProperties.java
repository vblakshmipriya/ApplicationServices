package com.comcast.technucleus.application.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.comcast.technucleus.application.exception.ApplicationServiceException;
import com.comcast.technucleus.application.exception.ApplicationServiceException.SEErrorCode;

@Configuration
@PropertySource("file:///opt/xtools/properties/technucleus.properties")
public class ApplicationProperties {
	
	@Autowired
	private Environment env;
	
	/**
	 * Method Name : getProperty()
	 * Description : This method return value for the Key (propetyName)
	 *             
	 * @param  String propetyName
	 * @return String
	 */	
	public String getProperty(String propetyName)
	{	
        
		String value =  env.getProperty(propetyName);
		if(value ==null)	
		{
			throw new ApplicationServiceException(SEErrorCode.SERVICE_INVALID_PROPERTY_REQUEST_ERROR,"getProperty : Invalid property requested :"+propetyName, null, null);
		}
		return value;
	}

}
