package com.comcast.technucleus.application.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.comcast.technucleus.application.exception.PropertyException;

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
			throw new PropertyException("getProperty : Invalid property requested :"+propetyName);
		}
		return value;
	}

}
