package com.comcast.technucleus.application.router;

import java.util.concurrent.Future;

import org.json.JSONObject;

public interface MaestroRouter {

	public Future<JSONObject> getDeviceImage(String deviceModel, String maestroSessionToken);

	public String getMaestroSessionToken();

	public String getAlerts(String maestroSessionToken);

	public String getDocument(String docId, String maestroSessionToken);

	public String getRelatedDocDetails(String docId, String maestroSessionToken);
	
	public String getContentForSearchCriteria(String search, String sessionToken);

}
