package com.comcast.technucleus.application.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.bson.BSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.comcast.technucleus.application.configuration.SpringMongoConfig;
import com.comcast.technucleus.application.dao.TechnicianMongoDAO;
import com.comcast.technucleus.application.exception.DBException;
import com.comcast.technucleus.application.exception.DataLoadException;
import com.comcast.technucleus.application.exception.DBException.DBErrorCode;
import com.comcast.technucleus.application.model.internalservice.GlympseSession;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/******************************************************************************************************
 * 
 *@FileName : TechnicianMongoDAOImpl.java 
 *@Description  This class Interact with MongoDB Database and performs the following tasks
 *Step1:checks for the Technician Status Object if exists retrieves it,
 *      if not builds the Technician Status Object and stores it in the DB.
 *
 *****************************************************************************************************/

@Component
public class TechnicianMongoDAOImpl implements TechnicianMongoDAO
{
	/** The Constant log. */
	private final static Logger log = LoggerFactory
			.getLogger(TechnicianMongoDAOImpl.class.getName());
    
	private final static String TECH_STATUS = "Status";
	private final static String TECHNICIAN_DATA_BASE = "technicianDatabase";
	private final static String TECHNICIAN_COLL_NAME = "techStatus";
	private final static String MONGO_OPS_TEMPLATE_NAME= "workOrderCache";
	private final static String DATA = "Data";
	private final static String FULFILLMENT_CENTER = "FulfillmentCenter";
	private final static String TECH_NUM = "TechNum";
	private final static String CACHE_KEY = "CacheKey";
	private final static String AVAILABLE = "Available";
	
	@Autowired
	private SpringMongoConfig mongoConfig;

	/**
	 * @Method getTechStatus
	 * @Description checks if the Technician Status object is present in the database,
	 * if present retrieves from the DB if not builds the object and stores in the DB
	 * 
	 * @param fulfillmentCenter
	 * @param techNum
	 * 
	 * @return DBObject data
	 * 
	 */
	@Override
	public DBObject getTechStatus(String fulfillmentCenter, String techNum){
		
		DBObject data = null;
		
		DBCollection techStatusCollection = getTechnicianCollection();
		log.debug("found the collection "+TECHNICIAN_COLL_NAME);
		
		//building query object CacheKey
		BSONObject cacheKey = new BasicDBObject();
		cacheKey.put(FULFILLMENT_CENTER, fulfillmentCenter);
		cacheKey.put(TECH_NUM, techNum);
		
		BasicDBObject queryObj = new BasicDBObject();
		queryObj.put(CACHE_KEY, cacheKey);
		log.debug("The query object built successfully "+queryObj);

		long length = techStatusCollection.count(queryObj);

		if (length == 0) {
			log.debug("The query object provided "+queryObj+" doesn't exist in the DB, hence building the Tech Status object");
			data = buildTechStatus(cacheKey,techStatusCollection);//builds the techStatus object and stores in DB
			log.debug("'Tech Status' object is built successfully and stored in the DB "+data);
		} else {
			if (length == 1) {
				log.debug("The query object provided "+queryObj+"exists in the DB, hence retrieving the Tech Status object");
				data = getTechStatusFromDb(queryObj,techStatusCollection);//retrieves the techStatus object from data base
				log.debug("'Tech Status' object is retrieved successfully "+data);
			} else {
				throw new NonUniqueResultException("there is more than one result from the query provided"+ queryObj);
			}
		}
		return data;
	}
	
	/**
	 * @Method buildTechStatus
	 * @Description builds the technician Status Object and stores in the DB
	 * 
	 * @param cacheKey
	 * @param techStatusCollection
	 * 
	 * @return DBObject dbObj
	 * 
	 */
	@Override
	public  DBObject buildTechStatus(BSONObject cacheKey, DBCollection techStatusCollection){

		DBObject dbObj = new BasicDBObject();
		dbObj.put(TECH_STATUS, AVAILABLE);

		DBObject cacheDBObject = new BasicDBObject();
		cacheDBObject.put(CACHE_KEY, cacheKey);
		cacheDBObject.put(DATA, dbObj);

		techStatusCollection.save(cacheDBObject);

		return dbObj;
	}
	

	/**
	 * @Method getTechStatusFromDb
	 * @Description retrieves the Technician Status object from the DB
	 * 
	 * @param queryObj
	 * @param techStatusCollection
	 * 
	 * @return DBObject data
	 * 
	 */
	@Override
	public DBObject getTechStatusFromDb(BasicDBObject queryObj, DBCollection techStatusCollection){
		
		DBObject data = null;
		DBObject obj = null;
		
		DBCursor cursor = techStatusCollection.find(queryObj);
		obj = cursor.next();
		data = (DBObject) obj.get(DATA);
		if (data == null) {
			throw new NoResultException("the Object "+DATA+ " does not exist in the technicianDatabase under "+TECHNICIAN_COLL_NAME+ " collection");
		} else {
			if (data.containsField(TECH_STATUS)) {
				data = (DBObject) obj.get(DATA);
			} else {
				throw new NoResultException("the element "+TECH_STATUS+ " does not exist in the technicianDatabase under "+TECHNICIAN_COLL_NAME+ " collection");
			}
		}
		return data;
	}
	
	public void cacheTechnicianStatus(DBObject techStatus, String techNum, String fulfillmentCenter)
			throws DataLoadException {
	
		if (techStatus != null) {
			
			BSONObject cacheKey = new BasicDBObject();
			cacheKey.put(FULFILLMENT_CENTER, fulfillmentCenter);
			cacheKey.put(TECH_NUM, techNum);
			
			DBObject cacheDBObject = new BasicDBObject();
			cacheDBObject.put(CACHE_KEY, cacheKey);
			cacheDBObject.put(DATA, techStatus);
			DBCollection mongoDBCollection = getTechnicianCollection();
			mongoDBCollection.save(cacheDBObject);
		}
	}
	
	public void clearTechnicianStatusCache(String techNum, String fulfillmentCenter){
	DBCollection mongoDBCollection = getTechnicianCollection();
		
		BSONObject cacheKey = new BasicDBObject();
		cacheKey.put(FULFILLMENT_CENTER, fulfillmentCenter);
		cacheKey.put(TECH_NUM, techNum);
		
		BasicDBObject queryObj = new BasicDBObject();
		queryObj.put(CACHE_KEY, cacheKey);
		
		mongoDBCollection.remove(queryObj);
	}

	@Override
	public void updateTechnicianStatus(DBObject techStatus, String techNum, String fulfillmentCenter) 
	{
		DBCollection mongoDBCollection = getTechnicianCollection();
		BSONObject cacheKey = new BasicDBObject();
		cacheKey.put(FULFILLMENT_CENTER, fulfillmentCenter);
		cacheKey.put(TECH_NUM, techNum);
		
		BasicDBObject queryObj = new BasicDBObject();
		queryObj.put(CACHE_KEY, cacheKey);
		
		DBObject cacheDBObject = new BasicDBObject();
		cacheDBObject.put(CACHE_KEY, cacheKey);
		cacheDBObject.put(DATA, techStatus);
		mongoDBCollection.remove(queryObj);
		mongoDBCollection.save(cacheDBObject);	
	}

	/**
	 * @Method getGlympseSession
	 * @Description Retrieve existing glympse session from Cache
	 * 
	 * @param glympseSessionDetailsToPersist
	 * 
	 * @return DBObject dbObj
	 */
	@Override
	public GlympseSession getGlympseSession(String workOrderNumber) {
		try {
			
			MongoOperations mongoOps = mongoConfig.mongoOps(MONGO_OPS_TEMPLATE_NAME);
			
			if(mongoOps.collectionExists(GlympseSession.class)) { 
				GlympseSession glympseSession = mongoOps.findById(workOrderNumber, GlympseSession.class);
				
				if(null != glympseSession) {
					log.debug("getGlympseSession: glympse session details-{}",glympseSession);
					return glympseSession;
				} else {
					return new GlympseSession();
				}
			} else {
				mongoOps.createCollection(GlympseSession.class);
				return new GlympseSession();
			}
			
		} catch (Exception e) {
			log.error("persistGlympseSession: unable to persist glympse session information-{}", e.getMessage());
			throw new DBException(DBErrorCode.GLYPMSE_SESSION_DATA_RETRIEVAL_ERROR, e);
		}
	}

	/**
	 * @Method persistGlympseSession
	 * @Description persists glympse session details
	 * 
	 * @param glympseSessionDetailsToPersist
	 * 
	 * @return DBObject dbObj
	 */
	@Override
	public void persistGlympseSession(GlympseSession glympseSessionDetailsToPersist) {
		try {
			MongoOperations mongoOps = mongoConfig.mongoOps(MONGO_OPS_TEMPLATE_NAME);
			log.debug("persistGlympseSession: Mongo store initiated");
			mongoOps.save(glympseSessionDetailsToPersist);
		} catch (Exception e) {
			log.error("persistGlympseSession: unable to persist glympse session information-{}", e.getMessage());
			throw new DBException(DBErrorCode.GLYPMSE_SESSION_DATA_LOAD_ERROR, e);
		}		
	}
	
	/**
	 * Gets the database collection.
	 *
	 * @return the database collection
	 */
	private DBCollection getTechnicianCollection() {
		DBCollection mongoDBCollection = getTechnicianDatabase().getCollection(TECHNICIAN_COLL_NAME);
		return mongoDBCollection;
	}

	/**
	 * Gets the database.
	 *
	 * @return the database
	 */
	private DB getTechnicianDatabase() {
		DB mongoDB = mongoConfig.getDatabase(TECHNICIAN_DATA_BASE);
		return mongoDB;
	}
}