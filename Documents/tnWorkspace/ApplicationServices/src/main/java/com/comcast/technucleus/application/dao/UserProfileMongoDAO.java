package com.comcast.technucleus.application.dao;

import org.bson.BSONObject;

import com.comcast.technucleus.application.exception.ApplicationServiceException;
import com.comcast.technucleus.application.model.GlympseSession;
import com.comcast.technucleus.application.model.UserDetails;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public interface UserProfileMongoDAO {

	public DBObject retrieveUserProfileData(String techNum) throws ApplicationServiceException;

	public void saveUserProfileData(DBObject userProfileData) throws ApplicationServiceException;

	public void insertUserProfileAndComcastNowData(DBObject userAndComcastNowProfileData, String ntId)
			throws ApplicationServiceException;

	public DBObject getUserProfileByNtIdFromDb(String ntId);

	public void deleteProfileDatabaseCollection();

	public UserDetails buildUserProfileResponse(DBObject userDataFromMongo);

	public void updateUserProfiledata(String ntId,String key, String value);

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
