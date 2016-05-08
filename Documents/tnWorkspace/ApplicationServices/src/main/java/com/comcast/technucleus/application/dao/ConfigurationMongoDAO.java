package com.comcast.technucleus.application.dao;

import com.comcast.technucleus.application.exception.ApplicationServiceException;
import com.mongodb.DBObject;

public interface ConfigurationMongoDAO {

	public DBObject getConfiguration(String groupID) throws ApplicationServiceException;	
	public DBObject createDeviceInformation	(String groupID) throws ApplicationServiceException;
	public DBObject getProperties(String propertiesName) throws ApplicationServiceException;
	
}
