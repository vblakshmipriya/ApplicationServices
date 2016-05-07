package com.comcast.technucleus.application.services.client;

import java.text.ParseException;

import com.comcast.technucleus.application.exception.DataLoadException;
import com.mongodb.DBObject;
/**
 * 
 * @author lpriya001c
 * UiStringClient -
 * IOS String management CRUD operations
 */
public interface UiStringClient {

	DBObject retriveIOSUiStrings();
	String createNewScreen(String screen) throws DataLoadException;
	String createNewScreensString(String screen, String id, String description, String value) throws DataLoadException;

	String updateNewScreen(String screen, String newScreenName) throws DataLoadException;
	String updateUiScreensString(String screenName, String id, String newId, String newValue, String newDescription) throws DataLoadException;

	public long getLastUpdetedTime(String source) throws ParseException;

}
