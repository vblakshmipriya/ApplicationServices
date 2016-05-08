package com.comcast.technucleus.application.dao;

import java.text.ParseException;
import java.util.Date;

import com.comcast.technucleus.application.exception.ApplicationServiceException;
import com.mongodb.DBObject;

public interface UiStringMongoDAO {

	public DBObject retriveIOSUiStrings();

	public String insertUiScreens(String screenName) throws ApplicationServiceException;

	public String updateUiScreens(String screenName, String newScreenName) throws ApplicationServiceException;

	public String insertUiScreensString(String screenName, String id, String value, String description) throws ApplicationServiceException;

	public String updateUiScreensString(String screenName, String id, String newId, String newValue, String newDescription) throws ApplicationServiceException;

	public Date getTimeStamp(String source) throws ParseException;
}
