package com.comcast.technucleus.application.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Component;

import com.comcast.technucleus.application.configuration.SpringMongoConfig;
import com.comcast.technucleus.application.dao.ConfigurationMongoDAO;
import com.comcast.technucleus.application.exception.ConfigurationLoadException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/******************************************************************************************************
 * 
 * File Name : ConfigurationMongoDAO.java Decription : This class Interact with
 * MongoDB Database Configuration and perform following operation 1. Search for
 * active configuration for 'groupID'
 *
 *
 *****************************************************************************************************/

@Component
public class ConfigurationMongoDAOImpl implements ConfigurationMongoDAO {

	public static final String PROPERTIES_COLL_NAME = "properties";
	
	/** The Constant CONFIGRUATION_DATA_BASE. */
	public static final String CONFIGRUATION_DATA_BASE = "configuration";
	/** The Constant CONFIGRUATION_COLL_NAME. */
	public static final String CONFIGRUATION_COLL_NAME = "configDetails";

	@Autowired
	public SpringMongoConfig mongoconfig;

	/**
	 * Method Name : getConfiguration Description : This Method interact with
	 * Mongo database and execute find query the active group configuration
	 * 
	 * @param String
	 *            groupID
	 * @return TechNukeResponse
	 */

	public DBObject getConfiguration(String groupID) throws ConfigurationLoadException {
		DBObject obj = null;
		DBCollection configCollection = getConfigurationCollection();
		BasicDBObject queryObj = new BasicDBObject();
		queryObj.put("active", true);
		queryObj.put("groupID", groupID);
		long length = configCollection.count(queryObj);
		if (length == 1) {
			DBCursor cursor = configCollection.find(queryObj);
			obj = cursor.next();

		} else {
			throw new ConfigurationLoadException("There are " + length + " active Configuration avaiable for groupID");
		}
		return obj;
	}

	public DBObject createDeviceInformation(String groupID) throws ConfigurationLoadException {
		DBObject obj = null;
		// TODO : Need to implement
		return obj;
	}

	@Override
	public DBObject getProperties(String propertieType) throws ConfigurationLoadException {

		DBObject obj = null;
		DBCollection configCollection = getConfigurationCollection();
		BasicDBObject queryObj = new BasicDBObject();
		queryObj.put("active", true);
		queryObj.put("type", propertieType);
		long length = configCollection.count(queryObj);
		if (length == 1) {
			DBCursor cursor = configCollection.find(queryObj);
			obj = cursor.next();

		} else {
			throw new ConfigurationLoadException(
					"There are No or more than one active " + "Property avaiable for group : " + propertieType);
		}
		return obj;
	}
	
	/**
	 * Gets the database collection.
	 *
	 * @return the database collection
	 */
	private DBCollection getConfigurationCollection() {
		DBCollection mongoDBCollection = getConfigurationDatabase().getCollection(CONFIGRUATION_COLL_NAME);
		return mongoDBCollection;
	}

	/**
	 * Gets the database.
	 *
	 * @return the database
	 */
	private DB getConfigurationDatabase() {
		DB mongoDB = mongoconfig.getDatabase(CONFIGRUATION_DATA_BASE);
		return mongoDB;
	}
	

}
