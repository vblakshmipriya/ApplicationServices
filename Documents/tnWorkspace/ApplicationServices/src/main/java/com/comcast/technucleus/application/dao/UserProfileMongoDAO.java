package com.comcast.technucleus.application.dao;

import com.comcast.technucleus.application.exception.DataLoadException;
import com.comcast.technucleus.application.model.internalservice.UserDetails;
import com.mongodb.DBObject;

public interface UserProfileMongoDAO {

	public DBObject retrieveUserProfileData(String techNum) throws DataLoadException;

	public void saveUserProfileData(DBObject userProfileData) throws DataLoadException;

	public void insertUserProfileAndComcastNowData(DBObject userAndComcastNowProfileData, String ntId)
			throws DataLoadException;

	public DBObject getUserProfileByNtIdFromDb(String ntId);

	public void deleteProfileDatabaseCollection();

	public UserDetails buildUserProfileResponse(DBObject userDataFromMongo);

	public void updateUserProfiledata(String ntId,String key, String value);
}
