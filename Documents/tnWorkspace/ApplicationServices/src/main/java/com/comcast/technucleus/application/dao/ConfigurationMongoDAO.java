package com.comcast.technucleus.application.dao;

import com.comcast.technucleus.application.exception.ConfigurationLoadException;
import com.mongodb.DBObject;

public interface ConfigurationMongoDAO {

	public DBObject getConfiguration(String groupID) throws ConfigurationLoadException;	
	public DBObject createDeviceInformation	(String groupID) throws ConfigurationLoadException;
	public DBObject getProperties(String propertiesName) throws ConfigurationLoadException;
	
}
