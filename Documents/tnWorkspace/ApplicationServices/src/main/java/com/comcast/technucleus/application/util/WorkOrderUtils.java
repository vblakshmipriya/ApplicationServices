package com.comcast.technucleus.application.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.comcast.technucleus.application.constants.PropertiesConstants;
import com.comcast.technucleus.application.constants.ServicesConstants;
import com.comcast.technucleus.application.model.internalservice.InternalBillingCodeMap;
import com.comcast.technucleus.application.properties.Properties;
import com.comcast.technucleus.application.services.client.LocationServiceClient;
import com.google.common.base.Throwables;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Component
public class WorkOrderUtils {
	
	@Autowired
	private Properties properties;
	
	@Autowired
	private LocationServiceClient locationService;
	
	RestTemplate restTemplate = new RestTemplate();
	
	private final static Logger log = LoggerFactory
			.getLogger(WorkOrderUtils.class.getName());
	
	public String getAccessTokenJson(String authorization, String source) {

		//RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Authorization", authorization);
		headers.set("Source", source);

		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl(properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + 
						PropertiesConstants.GET_ACCESS_TOKEN_URL).trim());

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		HttpEntity<String> response = restTemplate.exchange(builder.build()
				.toUri(), HttpMethod.GET, entity, String.class);

		String accessTokenString = getAccessToken(response.getBody());

		log.debug("JSON Received : " + accessTokenString);
		log.trace(accessTokenString);

		return accessTokenString;
	}
	
	
	/**
	 * Method Name : getBillingCodesListFromWorkOrderResponse
	 * Description : This method retreives the billing code list for the newly installed devices. 
	 * 				 ParentProductCd is the billing code in WFA response
	 * 
	 * @param  JsonObject 	workOrderByWoNumResponse
	 * @return List<String> billingCodeList 
	 */
	public List<String> getBillingCodesListFromWorkOrderResponse(
			JsonObject workOrderByWoNumResponse) {

		log.debug("getBillingCodesListFromWorkOrderResponse : retreiving billing codes list");
		List<String> billingCodeList = new ArrayList<String>();
		JsonElement workOrderDetailsJsonElement = workOrderByWoNumResponse.get(ServicesConstants.JOB_PRODUCT_LIST);
		JsonArray jobProductListJsonArray = workOrderDetailsJsonElement.getAsJsonArray();

		if(null != jobProductListJsonArray && jobProductListJsonArray.size() !=0) {
			for(JsonElement jobProductJsonElement : jobProductListJsonArray) {

				JsonObject jobProductJsonObject = jobProductJsonElement.getAsJsonObject();
				JsonElement actionCdJsonElement = jobProductJsonObject.get(ServicesConstants.ACTION_CODE);
				String actionCd = actionCdJsonElement.getAsString();

				if(actionCd.equalsIgnoreCase("ADD")) {
					JsonElement jsonElement = jobProductJsonObject.get(ServicesConstants.PARENT_PRODUCT_CODE);
					String billingCode = jsonElement.getAsString();
					billingCodeList.add(billingCode);
				}
			}
		}
		log.debug("getBillingCodesListFromWorkOrderResponse: billingCodeList size-{}", billingCodeList.size());
		return billingCodeList;
	}
	
	
	/**
	 * This method parses the response from WorkOrder by WorkOrderNum and return all the billingCodes and actionCodes
	 * @param workOrderByWoNumResponse
	 * @return
	 */
	public List<InternalBillingCodeMap> getBillingCodeListforJobDetails( JsonArray jobProdList ) {
		
		log.debug("getBillingCodeListforJobDetails : retreiving billing codes list");
		
		List<InternalBillingCodeMap> intBillingMapMapList = new ArrayList<InternalBillingCodeMap>();
	
		
		if(null != jobProdList && jobProdList.size() !=0) {
			for(Iterator<?> parentProductIterator = jobProdList.iterator();parentProductIterator.hasNext() ;){
				InternalBillingCodeMap billMapp = new InternalBillingCodeMap();

				JsonObject parentproduct = (JsonObject)parentProductIterator.next();
				String productTypeCd = parentproduct.get("ParentProductCd").getAsString();
				String actionCd = parentproduct.get("ActionCd").getAsString();
				
				log.debug("ParentProduct Code {} " , productTypeCd);
				billMapp.setActionCode(actionCd);
				billMapp.setProductCode(productTypeCd);
				//Add the parent one
				intBillingMapMapList.add(billMapp);
				
				if (parentproduct.get("ChildProductList") != null) {
					JsonArray childProductList = parentproduct.get("ChildProductList").getAsJsonArray();
					for (Iterator<?> childProductIterator = childProductList.iterator(); childProductIterator.hasNext();) {
						InternalBillingCodeMap childBillMap = new InternalBillingCodeMap();
						JsonObject childProduct = (JsonObject) childProductIterator.next();

						childBillMap.setActionCode(childProduct.get("ActionCd").getAsString());
						childBillMap.setProductCode(childProduct.get("ChildProductCd").getAsString());

						// Add the child one..
						intBillingMapMapList.add(childBillMap);
					}

				}
			}
		}
		log.debug("getBillingCodeListforJobDetails - Both Child and Parent billingCodeList size-{} :: {} ", intBillingMapMapList.size() ,intBillingMapMapList );
		return intBillingMapMapList;
	}
	
	
	
	public String getMarketId(JsonObject workOrderByWoNumResponse, String workOrderNum) {

		String marketReferenceId = "";
		try {

			JsonElement jobCustomerjsonElement = workOrderByWoNumResponse.get(ServicesConstants.JOB_LOCATION);
			JsonObject jobCustomerJsonObject = jobCustomerjsonElement.getAsJsonObject();
			JsonElement AddressJsonElement = jobCustomerJsonObject.get(ServicesConstants.ADDRESS);
			JsonObject addressJsonObject = AddressJsonElement.getAsJsonObject();
			JsonElement cityJsonElement = addressJsonObject.get(ServicesConstants.CITY);
			String city = cityJsonElement.getAsString();
			JsonElement stateJsonElement = addressJsonObject.get(ServicesConstants.STATE);
			String state = stateJsonElement.getAsString();
			JsonElement zipCodeJsonElement = addressJsonObject.get(ServicesConstants.ZIP_CODE);
			String zipCode = zipCodeJsonElement.getAsString();
			
			JsonObject jobJsonObject = workOrderByWoNumResponse.get(ServicesConstants.JOB).getAsJsonObject();
			String businessUnit = jobJsonObject.get(ServicesConstants.BUSINESS_UNIT).getAsString();

			log.debug("getMarketId: Firing the location service call");
			marketReferenceId = locationService.getMarketReferenceId(city, state, zipCode, businessUnit);
			log.debug("getMarketId: marketId-{} retrieved successfully for workOrderNum -{}", marketReferenceId, workOrderNum);
		}
		catch (Exception e) {
			log.error("getMarketId- Error retrieving market id for workOrdernum -{}", workOrderNum);
			log.error("getMarketId - stacktrace -{}", Throwables.getStackTraceAsString(e));
		}
		return marketReferenceId;
	}
	

	private String getAccessToken(String json) {
		
		JSONObject object = new JSONObject(json);
		String accessToken = object.optString("token");

		return accessToken;
	}

}
