package com.comcast.technucleus.application.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Component;

import com.comcast.technucleus.application.configuration.SpringMongoConfig;
import com.comcast.technucleus.application.dao.UserProfileMongoDAO;
import com.comcast.technucleus.application.exception.DataLoadException;
import com.comcast.technucleus.application.model.internalservice.UserDetails;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * The Class UserProfileMongoDAOImpl.
 * CRUD operations into User Profile collection in mongoDB
 */
@Component
public class UserProfileMongoDAOImpl implements UserProfileMongoDAO {

	/** The Constant log. */
	private final static Logger log = LoggerFactory.getLogger(UserProfileMongoDAOImpl.class.getName());
	/** The Constant USER_PROFILE_DATA_BASE. */
	public static final String USER_PROFILE_DATA_BASE = "userProfileDatabase";
	/** The Constant USER_PROFILE_COLL_NAME. */
	public static final String USER_PROFILE_COLL_NAME = "user_profile_data";
	public static final String DATA_ARRAY = "Data";
	public static final String NT_ID = "_id";

	/** The mongo db factory. */
	@Autowired
	public SpringMongoConfig mongoconfig;

	private static final String TECH_ID = "techID";
	private static final String FFC = "fulfillmentCenter";
	private static final String ALTERNATE_PHONE = "alternatePhone";
	private static final String COMCAST_GU_ID = "comcast_guid";
	private static final String ACCOUNT_NAME = "accountName";
	private static final String PERNR = "pernr";
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String MIDDLE_NAME = "middleName";
	private static final String POSITION_DESCRIPTION = "positionDescription";
	private static final String WORK_LOCATION_CODE = "workLocationCode";
	private static final String COMPANY_CODE = "companyCode";
	private static final String ORG_LEVEL_1_CODE = "orgLevel1Code";
	private static final String ORG_LEVEL_2_CODE = "orgLevel2Code";
	private static final String ORG_LEVEL_3_CODE = "orgLevel3Code";
	private static final String ORG_LEVEL_4_CODE = "orgLevel4Code";
	private static final String EMAIL = "emailAddress";
	private static final String WORK_PHONE = "workPhone";
	private static final String WORK_MOBILE = "workMobile";
	private static final String ABOUT_ME = "aboutMe";
	private static final String SERVICE_DATE = "serviceDate";
	private static final String POSITION_START_DATE = "positionStartDate";
	private static final String MANAGER_PERN = "managerPernr";
	private static final String COST_CENTER_CODE = "costCenterCode";
	private static final String LANGUAGES = "languages";
	private static final String SCHOOLS = "schools";
	private static final String INTERESTS = "interests";
	private static final String BIRTH_DAY = "birthday";
	private static final String ASK_ME_ABOUT = "askmeAbout";
	private static final String PAST_PROJECTS = "pastProjects";
	private static final String SEAT_LOCATION = "seatLocation";
	private static final String PROFILE_IMAGE_URL = "profileImageURL";
	private static final String PICTURE_BINARY = "pictureBinary";
	private static final String PICTURE_FILE_TYPE = "pictureFileType";
	private static final String SUPERVISOR = "supervisor";
	private static final String ADDR_LINE_1 = "addressLine1";
	private static final String ADDR_LINE_2 = "addressLine2";
	private static final String CITY = "city";
	private static final String STATE = "state";
	private static final String POSTAL_CODE = "postalCode";
	private static final String COUNTRY = "Country";

	/**
	 * Retrieve User Profile.
	 *
	 * @param ntId
	 *            the ntId
	 * @return the DB object
	 * @throws DataLoadException
	 *             the data load exception
	 */
	public DBObject retrieveUserProfileData(String ntId) throws DataLoadException {
		DBObject obj = null;
		DBCollection userProfileCollection = getUserProfileCollection();
		BasicDBObject queryObj = new BasicDBObject();
		obj = evaluateCollection(userProfileCollection, queryObj);
		return obj;
	}

	/**
	 * Persist User Profile Data.
	 *
	 * @param userProfileData
	 *            the User Profile Cache
	 * @throws DataLoadException
	 *             the data load exception
	 */
	public void saveUserProfileData(DBObject userProfileData) throws DataLoadException {
		if (userProfileData != null) {
			log.debug("Before Saving UserProfile data to Mongo - {}", userProfileData);
			getUserProfileCollection().save(userProfileData);
			log.debug("After Saving UserProfile data to Mongo - {}", userProfileData);
		}

	}

	/**
	 * Insert User Profile and ComcastNow Data.
	 *
	 * @param userProfileData
	 *            the User Profile Cache
	 * @throws DataLoadException
	 *             the data load exception
	 */
	public void insertUserProfileAndComcastNowData(DBObject userProfileAndComcastNowData, String ntId)
			throws DataLoadException {

		BasicDBObject queryObj = new BasicDBObject();
		queryObj.put(NT_ID, ntId);
		log.info("queryObject is built successfully" + queryObj);
		DB configDatabase = getUserProfileDatabase();
		log.debug("using database {} " + configDatabase.getName());
		DBCollection userProfileCollection = configDatabase.getCollection(USER_PROFILE_COLL_NAME);
		log.debug("using collection {} " + userProfileCollection.getName());
		long length = userProfileCollection.count(queryObj);
		if (length == 1) {
			log.debug("One matching record found for the query provided - {} " + queryObj);
			log.debug("user data in mongo before updating- {}", userProfileCollection.find(queryObj));
			BasicDBObject setOperationObj = new BasicDBObject();
			setOperationObj.put("$set", userProfileAndComcastNowData);
			userProfileCollection.update(queryObj, setOperationObj);
			log.debug("user data in mongo after adding comcastNow data- {}", userProfileCollection.find(queryObj));
		} else {
			log.error("There is either no data or more than one result for the query provided" + queryObj);
		}
	}

	/**
	 * Method Name: getUserProfileByNtIdFromDb
	 * 
	 * Description: Searches the userProfile list in the MongoDb and gets the
	 * userProfile associated with the given ntId
	 * 
	 * @param ntId
	 * @return String response
	 */
	@Override
	public DBObject getUserProfileByNtIdFromDb(String ntId) {

		DBObject obj = null;
		DB configDatabase = mongoconfig.getDatabase("userProfileDatabase");
		DBCollection configCollection = configDatabase.getCollection(USER_PROFILE_COLL_NAME);
		BasicDBObject queryData = new BasicDBObject();
		queryData.put(NT_ID, ntId);
		long length = configCollection.count(queryData);
		if (length == 1) {
			DBCursor cursor = configCollection.find(queryData);
			obj = cursor.next();
		}
		return obj;
	}

	@Override
	public void updateUserProfiledata(String ntId, String key, String value) {
		DBCollection userProfileCollection = getUserProfileCollection();
		// query object
		BasicDBObject queryObj = new BasicDBObject();
		queryObj.put("_id", ntId);
		// object to update
		BasicDBObject objToUpdate = new BasicDBObject();
		objToUpdate.put(key, value);
		// $set operator
		BasicDBObject setOperationObj = new BasicDBObject();
		setOperationObj.put("$set", objToUpdate);
		// update the userProfile data
		userProfileCollection.update(queryObj, setOperationObj);
	}

	@Override
	public UserDetails buildUserProfileResponse(DBObject userDataFromMongo) {
		UserDetails userDetails = new UserDetails();

		if (userDataFromMongo.containsField(ALTERNATE_PHONE)) {
			Object object = userDataFromMongo.get(ALTERNATE_PHONE);
			if (object != null) {
				userDetails.setAlternatePhone(object.toString());
			}
		}
		if (userDataFromMongo.containsField(TECH_ID)) {
			Object object = userDataFromMongo.get(TECH_ID);
			if (object != null) {
				userDetails.setTechID(object.toString());
			}
		}
		if (userDataFromMongo.containsField(FFC)) {
			Object object = userDataFromMongo.get(FFC);
			if (object != null) {
				userDetails.setFulfillmentCenter(object.toString());
			}
		}
		if (userDataFromMongo.containsField(COMCAST_GU_ID)) {
			Object object = userDataFromMongo.get(COMCAST_GU_ID);
			if (object != null) {
				userDetails.setComcast_guid(object.toString());
			}
		}
		if (userDataFromMongo.containsField(ACCOUNT_NAME)) {
			Object object = userDataFromMongo.get(ACCOUNT_NAME);
			if (object != null) {
				userDetails.setAccountName(object.toString());
			}
		}
		if (userDataFromMongo.containsField(PERNR)) {
			Object object = userDataFromMongo.get(PERNR);
			if (object != null) {
				userDetails.setPernr(object.toString());
			}
		}
		if (userDataFromMongo.containsField(FIRST_NAME)) {
			Object object = userDataFromMongo.get(FIRST_NAME);
			if (object != null) {
				userDetails.setFirstName(object.toString());
			}
		}
		if (userDataFromMongo.containsField(LAST_NAME)) {
			Object object = userDataFromMongo.get(LAST_NAME);
			if (object != null) {
				userDetails.setLastName(object.toString());
			}
		}
		if (userDataFromMongo.containsField(MIDDLE_NAME)) {
			Object object = userDataFromMongo.get(MIDDLE_NAME);
			if (object != null) {
				userDetails.setMiddleName(object.toString());
			}
		}

		if (userDataFromMongo.containsField(POSITION_DESCRIPTION)) {
			Object object = userDataFromMongo.get(POSITION_DESCRIPTION);
			if (object != null) {
				userDetails.setPositionDescription(object.toString());
			}

		}

		if (userDataFromMongo.containsField(WORK_LOCATION_CODE)) {
			Object object = userDataFromMongo.get(WORK_LOCATION_CODE);
			if (object != null) {
				userDetails.setWorkLocationCode(object.toString());
			}
		}

		if (userDataFromMongo.containsField(COMPANY_CODE)) {
			Object object = userDataFromMongo.get(COMPANY_CODE);
			if (object != null) {
				userDetails.setCompanyCode(object.toString());
			}
		}

		if (userDataFromMongo.containsField(ORG_LEVEL_1_CODE)) {
			Object object = userDataFromMongo.get(ORG_LEVEL_1_CODE);
			if (object != null) {
				userDetails.setOrgLevel1Code(object.toString());
			}
		}

		if (userDataFromMongo.containsField(ORG_LEVEL_2_CODE)) {
			Object object = userDataFromMongo.get(ORG_LEVEL_2_CODE);
			if (object != null) {
				userDetails.setOrgLevel2Code(object.toString());
			}
		}

		if (userDataFromMongo.containsField(ORG_LEVEL_3_CODE)) {
			Object object = userDataFromMongo.get(ORG_LEVEL_3_CODE);
			if (object != null) {
				userDetails.setOrgLevel3Code(object.toString());
			}
		}
		if (userDataFromMongo.containsField(ORG_LEVEL_4_CODE)) {
			Object object = userDataFromMongo.get(ORG_LEVEL_4_CODE);
			if (object != null) {
				userDetails.setOrgLevel4Code(object.toString());
			}

		}
		if (userDataFromMongo.containsField(EMAIL)) {
			Object object = userDataFromMongo.get(EMAIL);
			if (object != null) {
				userDetails.setEmailAddress(object.toString());
			}

		}
		if (userDataFromMongo.containsField(WORK_PHONE)) {
			Object object = userDataFromMongo.get(WORK_PHONE);
			if (object != null) {
				userDetails.setWorkPhone(object.toString());
			}
		}
		if (userDataFromMongo.containsField(WORK_MOBILE)) {
			Object object = userDataFromMongo.get(WORK_MOBILE);
			if (object != null) {
				userDetails.setWorkMobile(object.toString());
			}
		}
		if (userDataFromMongo.containsField(ABOUT_ME)) {
			Object object = userDataFromMongo.get(ABOUT_ME);
			if (object != null) {
				userDetails.setAboutMe(object.toString());
			}

		}
		if (userDataFromMongo.containsField(SERVICE_DATE)) {
			Object object = userDataFromMongo.get(SERVICE_DATE);
			if (object != null) {
				userDetails.setServiceDate(object.toString());
			}
		}
		if (userDataFromMongo.containsField(POSITION_START_DATE)) {
			Object object = userDataFromMongo.get(POSITION_START_DATE);
			if (object != null) {
				userDetails.setPositionStartDate(object.toString());
			}
		}
		if (userDataFromMongo.containsField(MANAGER_PERN)) {
			Object object = userDataFromMongo.get(MANAGER_PERN);
			if (object != null) {
				userDetails.setManagerPernr(object.toString());
			}
		}
		if (userDataFromMongo.containsField(COST_CENTER_CODE)) {
			Object object = userDataFromMongo.get(COST_CENTER_CODE);
			if (object != null) {
				userDetails.setCostCenterCode(object.toString());
			}
		}
		if (userDataFromMongo.containsField(LANGUAGES)) {
			Object object = userDataFromMongo.get(LANGUAGES);
			if (object != null) {
				userDetails.setLanguages(object.toString());
			}
		}
		if (userDataFromMongo.containsField(SCHOOLS)) {
			Object object = userDataFromMongo.get(SCHOOLS);
			if (object != null) {
				userDetails.setSchools(object.toString());
			}
		}
		if (userDataFromMongo.containsField(INTERESTS)) {
			Object object = userDataFromMongo.get(INTERESTS);
			if (object != null) {
				userDetails.setInterests(object.toString());
			}
		}
		if (userDataFromMongo.containsField(BIRTH_DAY)) {
			Object object = userDataFromMongo.get(BIRTH_DAY);
			if (object != null) {
				userDetails.setBirthday(object.toString());
			}
		}

		if (userDataFromMongo.containsField(PAST_PROJECTS)) {
			Object object = userDataFromMongo.get(PAST_PROJECTS);
			if (object != null) {
				userDetails.setPastProjects(object.toString());
			}
		}
		if (userDataFromMongo.containsField(SEAT_LOCATION)) {
			Object object = userDataFromMongo.get(SEAT_LOCATION);
			if (object != null) {
				userDetails.setSeatLocation(object.toString());
			}
		}
		if (userDataFromMongo.containsField(ASK_ME_ABOUT)) {
			Object object = userDataFromMongo.get(ASK_ME_ABOUT);
			if (object != null) {
				userDetails.setAskmeAbout(object.toString());
			}
		}
		if (userDataFromMongo.containsField(PROFILE_IMAGE_URL)) {
			Object object = userDataFromMongo.get(PROFILE_IMAGE_URL);
			if (object != null) {
				userDetails.setProfileImageURL(object.toString());
			}
		}

		if (userDataFromMongo.containsField(PICTURE_BINARY)) {
			Object object = userDataFromMongo.get(PICTURE_BINARY);
			if (object != null) {
				userDetails.setPictureBinary(object.toString());
			}
		}

		if (userDataFromMongo.containsField(PICTURE_FILE_TYPE)) {
			Object object = userDataFromMongo.get(PICTURE_FILE_TYPE);
			if (object != null) {
				userDetails.setPictureFileType(object.toString());
			}
		}
		if (userDataFromMongo.containsField(SUPERVISOR)) {
			Object object = userDataFromMongo.get(SUPERVISOR);
			if (object != null) {
				userDetails.setSupervisor(object.toString());
			}
		}
		if (userDataFromMongo.containsField(ADDR_LINE_1)) {
			Object object = userDataFromMongo.get(ADDR_LINE_1);
			if (object != null) {
				userDetails.setAddressLine1(object.toString());
			}
		}
		if (userDataFromMongo.containsField(ADDR_LINE_2)) {
			Object object = userDataFromMongo.get(ADDR_LINE_2);
			if (object != null) {
				userDetails.setAddressLine2(object.toString());
			}
		}
		if (userDataFromMongo.containsField(CITY)) {
			Object object = userDataFromMongo.get(CITY);
			if (object != null) {
				userDetails.setCity(object.toString());
			}
		}
		if (userDataFromMongo.containsField(STATE)) {
			Object object = userDataFromMongo.get(STATE);
			if (object != null) {
				userDetails.setState(object.toString());
			}
		}
		if (userDataFromMongo.containsField(POSTAL_CODE)) {
			Object object = userDataFromMongo.get(POSTAL_CODE);
			if (object != null) {
				userDetails.setPostalCode(object.toString());
			}
		}
		if (userDataFromMongo.containsField(COUNTRY)) {
			Object object = userDataFromMongo.get(COUNTRY);
			if (object != null) {
				userDetails.setCountry(object.toString());
			}
		}

		return userDetails;

	}

	/**
	 * Gets the database collection.
	 *
	 * @return the database collection
	 */
	private DBCollection getUserProfileCollection() {
		DBCollection mongoDBCollection = getUserProfileDatabase().getCollection(USER_PROFILE_COLL_NAME);
		return mongoDBCollection;
	}

	/**
	 * Gets the database.
	 *
	 * @return the database
	 */
	private DB getUserProfileDatabase() {
		DB mongoDB = mongoconfig.getDatabase(USER_PROFILE_DATA_BASE);
		return mongoDB;
	}
	
	/**
	 * Remove user_profile_data table.
	 *
	 * @return the DB object
	 * @throws DataLoadException
	 *             the data load exception
	 */
	public void deleteProfileDatabaseCollection() {
		BasicDBObject dbObject = new BasicDBObject();
		DBCollection profileDatabaseCollection = getUserProfileCollection();
		profileDatabaseCollection.remove(dbObject);
		log.info("Cleaning up old EIS data {}", dbObject);
	}

	/**
	 * Evaluate collection.
	 *
	 * @param userProfileCollection
	 *            the User Profile collection
	 * @param queryObj
	 *            the query obj
	 * @return the DB object
	 */
	private DBObject evaluateCollection(DBCollection userProfileCollection, BasicDBObject queryObj) {
		DBObject obj = null;
		// Check for only one record for the given CacheKey
		if (userProfileCollection.count(queryObj) == 1) {
			DBCursor cursor = userProfileCollection.find(queryObj);
			obj = cursor.next();
		}
		return obj;
	}

}
