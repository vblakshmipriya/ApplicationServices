package com.comcast.technucleus.application.services.client;

import java.text.ParseException;

import com.comcast.technucleus.application.exception.ApplicationServiceException;
import com.mongodb.DBObject;
/**
 * 
 * @author lpriya001c
 * UiStringClient -
 * IOS String management CRUD operations
 */
public interface UiStringClient {

	DBObject retriveIOSUiStrings();
	String createNewScreen(String screen) throws ApplicationServiceException;
	String createNewScreensString(String screen, String id, String description, String value) throws ApplicationServiceException;

	String updateNewScreen(String screen, String newScreenName) throws ApplicationServiceException;
	String updateUiScreensString(String screenName, String id, String newId, String newValue, String newDescription) throws ApplicationServiceException;

	public long getLastUpdetedTime(String source) throws ParseException;

}
