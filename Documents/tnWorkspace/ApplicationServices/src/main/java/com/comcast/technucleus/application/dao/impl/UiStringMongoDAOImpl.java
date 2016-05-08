package com.comcast.technucleus.application.dao.impl;

import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comcast.technucleus.application.configuration.SpringMongoConfig;
import com.comcast.technucleus.application.dao.UiStringMongoDAO;
import com.comcast.technucleus.application.exception.ApplicationServiceException.SEErrorCode;
import com.comcast.technucleus.application.exception.ApplicationInvalidRequestException;
import com.comcast.technucleus.application.exception.ApplicationServiceException;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * The Class UiStringMongoDAOImpl.
 */
@Component
public class UiStringMongoDAOImpl implements UiStringMongoDAO {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(UiStringMongoDAOImpl.class.getName());

	public static final String UI_STRING_DATA_BASE = "uiStringsDatabase";
	public static final String UI_STRING_COLL_NAME = "ui_strings";
	public static final String DATA_ARRAY = "Data";
	public static final String ID = "_id";
	public static final String IOS = "ios";
	public static final String IOSDESC = "iosDescription";
	public static final String EXISTS = "$exists";
	public static final String SET = "$set";
	public static final String RENAME = "$rename";
	public static final String LAST_UPDATED = "last_updated";
	public static final String IN = "$in";

	/** The mongo db factory. */
	@Autowired
	public SpringMongoConfig mongoconfig;
	
	/**
	 * Retrieve UI Strings for IOS
	 */
	@Override
	public DBObject retriveIOSUiStrings() {
		DBCollection uiStringCollection = getUIStringCollection();

		BasicDBObject queryObj = iosDBObject();

		DBCursor cursor = uiStringCollection.find(queryObj);
		DBObject obj = cursor.next();
		log.info("Retrived IOS strings : " + obj);
		return obj;
	}

	/**
	 * Insert new screen name for IOS and IOS Description
	 */

	public String insertUiScreens(String screenName) throws ApplicationServiceException {
		DBCollection uiStringCollection = getUIStringCollection();

		BasicDBObject iosObj = iosDBObject();

		long length = uiStringCollection.count(iosObj);

		if (length == 0) {
			initialInsertion(IOS, screenName, uiStringCollection);
			initialInsertion(IOSDESC, screenName, uiStringCollection);
		} else if (length == 1) {
			BasicDBObject query = new BasicDBObject(screenName, new BasicDBObject(EXISTS, true));
			if (uiStringCollection.count(query) > 0) {
				log.info("Screen name already exists");
				throw new ApplicationServiceException(SEErrorCode.SERVICE_MONGO_DATA_LOAD_ERROR,screenName + " already exists", null, null);	
			} else {
				BasicDBList dbList = new BasicDBList();
				dbList.add(IOS);
				dbList.add(IOSDESC);

				DBObject inObj = new BasicDBObject();
				inObj.put(IN, dbList);

				DBObject multiQueryObj = new BasicDBObject();
				multiQueryObj.put(ID, inObj);
				
				BasicDBObject updateObj = new BasicDBObject();
				BasicDBObject screenObj = new BasicDBObject();
				updateObj.put(LAST_UPDATED, new Date());
				updateObj.put(screenName, screenObj);
				
				DBObject setOperationObj = new BasicDBObject();
				setOperationObj.put(SET, updateObj);
				
				uiStringCollection.updateMulti(multiQueryObj, setOperationObj);
			}
		}

		log.info("uiString data after mongo insert", uiStringCollection.find(iosObj));
		return screenName + " is created";
	}

	/**
	 * Insert new ui string for particular screen
	 */
	@Override
	public String insertUiScreensString(String screenName, String key, String value, String description) {
		DBCollection uiStringCollection = getUIStringCollection();
		
		
		DBObject findScreenObj = new BasicDBObject();
		findScreenObj.put(screenName, new BasicDBObject(EXISTS, true));
		
		if (uiStringCollection.count(findScreenObj) == 0) {
			log.info("Screen name doesn't exist");
			throw new ApplicationServiceException(SEErrorCode.SERVICE_MONGO_DATA_LOAD_ERROR,screenName + " doesn't exist", null, null);	
		}else{
			BasicDBObject findKeyForScreenName = iosDBObject();
			findKeyForScreenName.put(screenName + "." + key, new BasicDBObject(EXISTS, true));

			long count = uiStringCollection.count(findKeyForScreenName);

			if (count == 0) {
				
				BasicDBList dbList = new BasicDBList();
				dbList.add(IOS);
				dbList.add(IOSDESC);

				DBObject inObj = new BasicDBObject();
				inObj.put(IN, dbList);

				DBObject multiQueryObj = new BasicDBObject();
				multiQueryObj.put(ID, inObj);

				DBObject setKeyValAndDateObj = new BasicDBObject();
				setKeyValAndDateObj.put(LAST_UPDATED, new Date());
				
				DBObject setObj = new BasicDBObject();
				setObj.put(SET, setKeyValAndDateObj);
				
				uiStringCollection.updateMulti(multiQueryObj, setObj);
				
				DBObject setKeyValue = new BasicDBObject();
				setKeyValue.put(screenName + "." + key, value);
				
				DBObject setKeyDesc = new BasicDBObject();
				setKeyDesc.put(screenName + "." + key, description);
				
				DBObject iosSetObj = new BasicDBObject();
				iosSetObj.put(SET, setKeyValue);
				
				DBObject iosDescSetObj = new BasicDBObject();
				iosDescSetObj.put(SET, setKeyDesc);
				
				uiStringCollection.update(iosDBObject(), iosSetObj);
				
				uiStringCollection.update(iosDescObject(), iosDescSetObj);

			} else {
				throw new ApplicationServiceException(SEErrorCode.SERVICE_MONGO_DATA_LOAD_ERROR,"UI String " + key + " for " + screenName + " already exists", null, null);
				
			}
		}
		return "UI String " + key + " for " + screenName + " is created";
	}

	
	/**
	 * @Method     :  updateUiScreens
	 * @Description:  checks if the screenName exists, if no throws Data load exception
	 * 				  If yes, updates the old screen  name with new name in both IOS object
	 *                and IosDescription object in the mongoDB
	 *                
	 * @param		: String screenName           
	 * @param		: String newScreenName
	 * @throws		: DataLoadException
	 */
	@Override
	public String updateUiScreens(String screenName, String newScreenName) throws ApplicationServiceException {
		DBCollection uiStringCollection = getUIStringCollection();

		DBObject findScreenObj = new BasicDBObject();
		findScreenObj.put(screenName, new BasicDBObject(EXISTS, true));

		if (uiStringCollection.count(findScreenObj) == 0) {
			log.info("Screen name doesn't exist");
			throw new ApplicationServiceException(SEErrorCode.SERVICE_MONGO_DATA_LOAD_ERROR,screenName + " doesn't exist", null, null);
		} else {
			BasicDBList dbList = new BasicDBList();
			dbList.add(IOS);
			dbList.add(IOSDESC);

			DBObject inObj = new BasicDBObject();
			inObj.put(IN, dbList);

			DBObject multiQueryObj = new BasicDBObject();
			multiQueryObj.put(ID, inObj);

			DBObject setObj = new BasicDBObject();
			setObj.put(SET, new BasicDBObject(LAST_UPDATED, new Date()));

			DBObject renameObject = new BasicDBObject();
			renameObject.put(RENAME, new BasicDBObject(screenName, newScreenName));

			uiStringCollection.updateMulti(multiQueryObj, setObj);

			uiStringCollection.updateMulti(multiQueryObj, renameObject);
		}
		log.info("uiString data after mongo update ", uiStringCollection.find(findScreenObj));
		return screenName + " is updated to "+ newScreenName;
	}
		
	

	@Override
	public String updateUiScreensString(String screenName, String key, String newId, String newDescription,
			String newValue) {
		/* db.ui_strings.find({"LoginScreen.Login": {$exists: true}, "LoginScreen": {$size: 1}});*/

		DBCollection uiStringCollection = getUIStringCollection();

		BasicDBObject query = iosDBObject();
		query.put(screenName + "." + key, new BasicDBObject(EXISTS, true));
		long count = uiStringCollection.count(query);

		if (count == 0) {
			throw new ApplicationServiceException(SEErrorCode.SERVICE_MONGO_DATA_LOAD_ERROR,"UI String " + key + " for " + screenName + " doesn't exist", null, null);
		} else {
			BasicDBObject iosObj = iosDBObject();

			iosObj.put(screenName, new BasicDBObject(EXISTS, true));

			if (!key.equals(newId)) {
				uiStringCollection.update(iosObj, new BasicDBObject(RENAME, new BasicDBObject(screenName + "." + key, screenName + "." + newId)));
			}
			uiStringCollection.update(iosObj,new BasicDBObject(SET, new BasicDBObject(screenName + "." + newId, newValue)));
			uiStringCollection.update(iosDBObject(),new BasicDBObject(SET, new BasicDBObject(LAST_UPDATED, new Date())));
			
			BasicDBObject descObj = iosDescObject();

			descObj.put(screenName, new BasicDBObject(EXISTS, true));

			if (!key.equals(newId)) {
				uiStringCollection.update(descObj, new BasicDBObject(RENAME,new BasicDBObject(screenName + "." + key, screenName + "." + newId)));
			}
			uiStringCollection.update(descObj,new BasicDBObject(SET, new BasicDBObject(screenName + "." + newId, newDescription)));
			uiStringCollection.update(iosDescObject(),new BasicDBObject(SET, new BasicDBObject(LAST_UPDATED, new Date())));
		}
		return "UI String " + newId + " for " + screenName + " is updated";
	}

	/**
	 * Insert ios and iosDescrion documents
	 * 
	 * @param id
	 * @param screenName
	 * @param uiStringCollection
	 */
	private void initialInsertion(String id, String screenName, DBCollection uiStringCollection) {
		BasicDBObject initialObj = new BasicDBObject();
		BasicDBObject obj = new BasicDBObject();

		initialObj.put(ID, id);
		initialObj.put(LAST_UPDATED, new Date());
		initialObj.put(screenName, obj);
		uiStringCollection.insert(initialObj);
	}
	private BasicDBObject iosDescObject() {
		BasicDBObject iosDecObj = new BasicDBObject();
		iosDecObj.put(ID, IOSDESC);
		return iosDecObj;
	}

	private BasicDBObject iosDBObject() {
		BasicDBObject iosObj = new BasicDBObject();
		iosObj.put(ID, IOS);
		return iosObj;
	}
	
	public Date getTimeStamp(String source) throws ParseException{
		DBCollection dbCollection = getUIStringCollection();
		
		DBObject query = new BasicDBObject();
		query.put(ID, source);
		DBCursor cursor = dbCollection.find(query);
		if (cursor.hasNext()) {
			DBObject object = cursor.next();
			Date mongoDate = (Date)object.get("last_updated");
			return mongoDate;
		} else {
			throw new ApplicationInvalidRequestException("The source "+source+" doesn't exist in the data store");
		}
	}

	/**
	 * Gets the database collection.
	 *
	 * @return the database collection
	 */
	private DBCollection getUIStringCollection() {
		DBCollection mongoDBCollection = getUIStringDatabase().getCollection(UI_STRING_COLL_NAME);
		return mongoDBCollection;
	}

	/**
	 * Gets the database.
	 *
	 * @return the database
	 */
	private DB getUIStringDatabase() {
		DB mongoDB = mongoconfig.getDatabase(UI_STRING_DATA_BASE);
		return mongoDB;
	}
	
}
