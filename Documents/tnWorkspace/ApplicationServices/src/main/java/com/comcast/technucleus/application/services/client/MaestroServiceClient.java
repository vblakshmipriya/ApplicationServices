package com.comcast.technucleus.application.services.client;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;


public interface MaestroServiceClient {

	public String getJsessionId();

	public String searchMaestroKnowledgebase(String page, String searchString,
			Boolean jsonResponse, String jSessionId, String user);

	public String searchMaestroKnowledgebasePagination(String page,
			String searchString, Boolean jsonResponse, String accessToken,
			String user, String docVer, String type, String searchid,
			String region);
	
	public JSONObject getDeviceImage(String deviceModel, String maestroSessionToken);
	
	//For TestCases
	public void setRestTemplate(RestTemplate mock);

	public String getAlerts(String maestroSessionToken);

	public String getDocument(String docId, String maestroSessionToken);

	public String getRelatedDocDetails(String docId, String maestroSessionToken);

	public String getContentForSearchCriteria(String searchString, String maestroSessionToken);
}
