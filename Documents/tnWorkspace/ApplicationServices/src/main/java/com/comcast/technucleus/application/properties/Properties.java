package com.comcast.technucleus.application.properties;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comcast.technucleus.application.dao.ConfigurationMongoDAO;
import com.comcast.technucleus.application.exception.ApplicationServiceException;
import com.mongodb.DBObject;


/******************************************************************************************************
 * 
 * File Name : Properties.java 
 * Description : This Class  load application level properties
 *
 *****************************************************************************************************/
@Component
public class Properties 
{
	
	private ConfigurationMongoDAO configurationMongoDAO = null;
	
	private final static Logger log = LoggerFactory
			.getLogger(Properties.class.getName());
	public static final String SERVICE_END_POINT_PROPERTIES ="ENDPOINT";
	public static final String VALUES_PROPERTIES = "REDIS";
	public static final String MESSAGE_PROPERTIES = "MESSAGE";
	public static final String KAFKA_PROPERTIES = "KAFKA";
	public static final String PROPERTY_CONFIG = "config";
	public static final String CORS_PROPERTIES = "CORS";
	public static final String SAML_PROPERTIES = "SAML";
	
	public static Properties instance = null;
	
	private Map<String,String> properties = null;
	
	

	/**
	 *  Constructor Name :  Properties(ConfigurationMongoDAO)
	 *  Description : 		Constructor initiate the different set of properties
	 * 
	 * @param ConfigurationMongoDAO		message
	 */
	@Autowired
	public Properties(ConfigurationMongoDAO configurationMongoDAO) 
	{
		instance = this;
		this.configurationMongoDAO = configurationMongoDAO;
		instantiate();
	}
	

	/** 
	 *  Method Name : instantiate
	 *  Description : This Method retrieved different properties set from mongo
	 *                and process then and store in (Map<String,String>) properties
	 * 
	 */
	private synchronized void instantiate() throws ApplicationServiceException
	{
		properties = new HashMap<String, String>();		
		DBObject endpointProperties = configurationMongoDAO.getProperties(SERVICE_END_POINT_PROPERTIES);
		update(endpointProperties,SERVICE_END_POINT_PROPERTIES);		
		DBObject messageProperties = configurationMongoDAO.getProperties(MESSAGE_PROPERTIES);
		update(messageProperties,MESSAGE_PROPERTIES);		
		DBObject valuesProperties = configurationMongoDAO.getProperties(VALUES_PROPERTIES);
		update(valuesProperties,VALUES_PROPERTIES);
		DBObject kafkaProperties = configurationMongoDAO.getProperties(KAFKA_PROPERTIES);
		update(kafkaProperties,KAFKA_PROPERTIES);
		DBObject corsProperties = configurationMongoDAO.getProperties(CORS_PROPERTIES);
		update(corsProperties,CORS_PROPERTIES);
		DBObject samlProperties = configurationMongoDAO.getProperties(SAML_PROPERTIES);
		update(samlProperties,SAML_PROPERTIES);
		log.info("instantiate : All the debug properties loaded succesfully");
	}
	

	/**
	 *  Method Name :  update
	 *  Description :  This method build the properties from response of propType
	 * 
	 * @param 		DBObject		propType
	 * @param 		String	    	name
	 * 
	 */
	private void update(DBObject propType,String name) {		
		DBObject dbObject = (DBObject) propType.get(PROPERTY_CONFIG);
		Set<String> keys =	dbObject.keySet();
		for(String key : keys)
			properties.put(name+"."+key,(String) dbObject.get(key));	 	
	}
	
	

	/**
	 *  Method Name : getProp
	 *  Description : This Method used to retrieve the properties file
	 * 
	 * @param  String		message
	 * @return String
	 */
	public String getProp(String name)
	{
	   if(properties==null)	  
		   throw new IllegalStateException("Property Cache Instance is not intitialalized"
		   		+ " and Application is tried to access property :" + name);	   
	   if(!properties.containsKey(name))
		   throw new IllegalArgumentException("Property : '" + name +"' is not avilable in property cache");	   
		return properties.get(name);   
	}
}



