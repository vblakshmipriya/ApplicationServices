package com.comcast.technucleus.application.dao;

import org.bson.BSONObject;

import com.comcast.technucleus.application.model.internalservice.GlympseSession;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/******************************************************************************************************
 * 
 *@FileName : TechnicianMongoDAOImpl.java 
 *@Description  This class Interact with MongoDB Database and performs the following tasks
 *				1. checks for the Technician Status Object if exists retrieves it,
 *      		if not builds the Technician Status Object and stores it in the DB.
 *      		2. persistGlympseSession - persist glympse session details
 *
 *****************************************************************************************************/
public interface TechnicianMongoDAO {
	
	/**
	 * @Method getTechStatus
	 * @Description checks if the Technician Status object is present in the database,
	 * if present retrieves from the DB if not builds the object and stores in the DB
	 * 
	 * @param fulfillmentCenter
	 * @param techNum
	 */
	public DBObject getTechStatus(String fulfillmentCenter, String techNum);
	
	
	/**
	 * @Method buildTechStatus
	 * @Description builds the technician Status Object and stores in the DB
	 * 
	 * @param cacheKey
	 * @param techStatusCollection
	 */
	public DBObject buildTechStatus(BSONObject cacheKey,DBCollection techStatusCollection);

	
	/**
	 * @Method getTechStatusFromDb
	 * @Description retrieves the Technician Status object from the DB
	 * 
	 * @param queryObj
	 * @param techStatusCollection
	 */
	public DBObject getTechStatusFromDb(BasicDBObject queryObj, DBCollection techStatusCollection);
	
	public void cacheTechnicianStatus(DBObject techStatus,String techNum, String fulfillmentCenter);
	
	public void updateTechnicianStatus(DBObject techStatus,String techNum, String fulfillmentCenter);
	
	public void clearTechnicianStatusCache(String techNum, String fulfillmentCenter);
	
	public GlympseSession getGlympseSession(String workOrderNumber);

	public void persistGlympseSession(GlympseSession glympseSessionDetailsToPersist);

}
