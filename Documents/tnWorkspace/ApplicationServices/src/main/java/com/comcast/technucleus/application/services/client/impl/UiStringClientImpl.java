package com.comcast.technucleus.application.services.client.impl;

import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comcast.technucleus.application.dao.UiStringMongoDAO;
import com.comcast.technucleus.application.exception.DataLoadException;
import com.comcast.technucleus.application.services.client.UiStringClient;
import com.mongodb.DBObject;
/**
 * 
 * @author lpriya001c
 * File : UiStringClientImpl
 * Description: All IOS String management operations are done, here.
 */
@Component
public class UiStringClientImpl implements UiStringClient {
	private static final Logger log = LoggerFactory.getLogger(UiStringClientImpl.class.getName());


	@Autowired
	private UiStringMongoDAO uiStringMongoDAO;

	@Override
	public String createNewScreen(String screenName) throws DataLoadException{
		return uiStringMongoDAO.insertUiScreens(screenName);
	}

	@Override
	public String createNewScreensString(String screenName, String id, String description, String value) throws DataLoadException{
		return uiStringMongoDAO.insertUiScreensString(screenName, id, value, description);
	}

	@Override
	public String updateNewScreen(String screenName, String newScreenName) throws DataLoadException{
		return uiStringMongoDAO.updateUiScreens(screenName, newScreenName);
	}

	@Override
	public String updateUiScreensString(String screenName, String id, String newId, String newValue, String newDescription) throws DataLoadException{
		return uiStringMongoDAO.updateUiScreensString(screenName, id, newId, newValue, newDescription);
	}

	@Override
	public DBObject retriveIOSUiStrings() {
		return uiStringMongoDAO.retriveIOSUiStrings();
	}
	
	@Override
	public long getLastUpdetedTime(String source) throws ParseException {
		Date mongoDate = uiStringMongoDAO.getTimeStamp(source);
		long time = mongoDate.getTime();
		return time;
	}
}
