package com.comcast.technucleus.application.constants;

public interface PropertiesConstants {

	public static final String SERVICE_END_POINT_PROPERTIES = "ENDPOINT.";
	public static final String REDIS_PROPERTIES = "REDIS.";
	public static final String MESSAGE_PROPERTIES = "MESSAGE.";
	public static final String KAFKA_PROPERTIES = "KAFKA.";
	public static final String CORS_PROPERTIES = "CORS.";
	public static final String SAML_PROPERTIES = "SAML.";

	public static final String WORK_ORDER_NOT_FOUND = "WORK_ORDER_NOT_FOUND";
	public static final String WORK_ORDER_FOUND = "WORK_ORDER_FOUND";
	public static final String LOGIN_SUCCESS = "LOGIN_SUCCESS";
	public static final String LOGIN_FAILURE = "LOGIN_FAILURE";
	public static final String EXCEPTION = "EXCEPTION";
	public static final String SEARCH_SUCCESSFUL = "SEARCH_SUCCESSFUL";
	public static final String UNSUCCESSFUL_SEARCH = "UNSUCCESSFUL_SEARCH";
	public static final String URL_ERROR = "URL_ERROR";
	public static final String EHH_INTEGRITY_NOT_FOUND = "EHH_INTEGRITY_NOT_FOUND";
	public static final String EHH_INTEGRITY_FOUND = "EHH_INTEGRITY_FOUND";
	public static final String EHH_DEVICES_FOUND = "EHH_DEVICES_FOUND";
	public static final String EHH_DEVICES_NOT_FOUND = "EHH_DEVICES_NOT_FOUND";
	public static final String EHH_ACCOUNT_FOUND = "EHH_ACCOUNT_FOUND";
	public static final String EHH_ACCOUNT_NOT_FOUND = "EHH_ACCOUNT_NOT_FOUND";
	public static final String USERS_DETAILS = "USERS_DETAILS";
	public static final String USERS_DETAILS_NOT_FOUND = "USERS_DETAILS_NOT_FOUND";
	public static final String CUSTOMER_TIMELINE_INFO_FOUND = "CUSTOMER_TIMELINE_INFO_FOUND";
	public static final String CUSTOMER_TIMELINE_EVENTTYPE_INFO_FOUND = "CUSTOMER_TIMELINE_EVENTTYPE_INFO_FOUND";
	public static final String CUSTOMER_TIMELINE_INFO_NOT_FOUND = "CUSTOMER_TIMELINE_INFO_NOT_FOUND";
	public static final String CUSTOMER_TIMELINE_EVENTTYPE_INFO_NOT_FOUND = "CUSTOMER_TIMELINE_EVENTTYPE_INFO_NOT_FOUND";
	public static final String CUSTOMER_TIMELINE_DATA_NOT_FOUND = "CUSTOMER_TIMELINE_DATA_NOT_FOUND";
	public static final String OUTAGE_FOUND = "OUTAGE_FOUND";
	public static final String NO_OUTAGE_FOUND = "NO_OUTAGE_FOUND";
	public static final String OUTAGE_ERROR = "OUTAGE_ERROR";
	public static final String MAESTRO_SEARCH_RESULT_SUCCESS = "MAESTRO_SEARCH_RESULT_SUCCESS";
	public static final String MAESTRO_SEARCH_RESULT_FAILURE = "MAESTRO_SEARCH_RESULT_FAILURE";
	public static final String CUSTOMER_TIMELINE_EVENT_DETAILS_NOT_FOUND = "CUSTOMER_TIMELINE_EVENT_DETAILS_NOT_FOUND";
	public static final String CUSTOMER_TIMELINE_EVENT_DETAILS_FOUND = "CUSTOMER_TIMELINE_EVENT_DETAILS_FOUND";
	public static final String CUSTOMER_TIMELINE_EVENT_NOT_FOUND = "CUSTOMER_TIMELINE_EVENT_NOT_FOUND";
	public static final String CUSTOMER_TIMELINE_EVENT_FOUND = "CUSTOMER_TIMELINE_EVENT_FOUND";
	public static final String AUTHORIZATION_KEY = "AUTHORIZATION_KEY";
	public static final String WORKORDER_SOURCE_ID = "WORKORDER_SOURCE_ID";
	public static final String SOURCE_NAME = "SOURCE_NAME";
	public static final String WORK_ORDER_LIST_FIREBASE_URL = "WORK_ORDER_LIST_FIREBASE_URL";
	public static final String WORKORDER_ACCOUNT_NUM_SEARCH_FIREBASE_URL = "WORKORDER_ACCOUNT_NUM_SEARCH_FIREBASE_URL";
	public static final String WFX_AUTHORIZATION_FIREBASE_URL = "WFX_AUTHORIZATION_FIREBASE_URL";
	public static final String INTEGRITY_SCORES_URL = "INTEGRITY_SCORES_URL";

	public static final String GET_DEVICES = "GET_DEVICES";
	public static final String SEARCH_ACCOUNT = "SEARCH_ACCOUNT";
	public static final String X1_KNOWN_ISSUES_URL_FIREBASE = "X1_KNOWN_ISSUES_URL_FIREBASE";
	public static final String FRIENDLY_NAME_URL = "FRIENDLY_NAME_URL";
	public static final String GET_DEVICEID_BY_ECMMAC_URL = "GET_DEVICEID_BY_ECMMAC_URL";
	public static final String GET_SERVICE_ACCID_BY_ACCNUM_URL = "GET_SERVICE_ACCID_BY_ACCNUM_URL";
	public static final String EINSTEIN_CUSTOMER_TIMELINE_URL = "EINSTEIN_CUSTOMER_TIMELINE_URL";
	public static final String EINSTEIN_CUSTOMER_TIMELINE_EVENTTYPE_URL = "EINSTEIN_CUSTOMER_TIMELINE_EVENTTYPE_URL";
	public static final String SITEMINDER_ENDPOINT_CALL_URL = "SITEMINDER_ENDPOINT_CALL_URL";
	public static final String EHH_NODE_HEALTH_URL = "EHH_NODE_HEALTH_URL";
	public static final String MAESTRO_KNOWLEDGEBASE_URL = "MAESTRO_KNOWLEDGEBASE_URL";
	public static final String REDIS_SESSION_EXPIRATION_TIME = "redisSessionExpirationTime";
	public static final String EINSTEIN_CUSTOMER_TIMELINE_CODEBIG_URL = "EINSTEIN_CUSTOMER_TIMELINE_CODEBIG_URL";
	public static final String CONSUMER_KEY = "CONSUMER_KEY";
	public static final String SHARED_SECRET = "SHARED_SECRET";
	public static final String PD_CONSTANT = "PWD";
	public static final String USER = "USER";
	public static final String GRANDSLAM_ACCOUNT_SERVICES_URL = "GRANDSLAM_ACCOUNT_SERVICES_URL";
	public static final String RADIUS = "RADIUS";
	public static final String ZOOKEEPER_URL = "ZOOKEEPER_URL";
	public static final String PARTITION_NUM = "PARTITION_NUM";
	public static final String REPLICATION_FACTOR = "REPLICATION_FACTOR";
	public static final String ZOOKEEPER_SESSION_TIMEOUT_MS = "ZOOKEEPER_SESSION_TIMEOUT_MS";
	public static final String ZOOKEEPER_AUTO_COMMIT_INTERVAL_MS = "ZOOKEEPER_AUTO_COMMIT_INTERVAL_MS";
	public static final String ZOOKEEPER_SYNC_TIME_MS = "ZOOKEEPER_SYNC_TIME_MS";
	public static final String GROUP_ID = "GROUP_ID";
	public static final String KAFKA_MESSAGE_TYPE = "messageType";
	public static final String TOPIC = "topic";
	public static final String HOSTNAME = "hostname";
	public static final String REGISTER_CLIENT = "registerClient"; 
	public static final String GPS_DETAILS_PASSED = "GPS_DETAILS_PASSED";
	public static final String GPS_DETAILS_COULD_NOT_BE_PASSED = "GPS_DETAILS_COULD_NOT_BE_PASSED";
	public static final String WORK_ORDER_BY_WORKORDER_NUM_URL = "WORK_ORDER_BY_WORKORDER_NUM_URL";
	public static final String WORK_ORDER_BY_TECH_NUM_URL = "WORK_ORDER_BY_TECH_NUM_URL";
	public static final String GET_ACCESS_TOKEN_URL = "GET_ACCESS_TOKEN_URL";
	public static final String CORS_IP_ADDRESS = "corsIpAddress";
	public static final String UPDATE_JOB_CUSTOMER_INFO = "UPDATE_JOB_CUSTOMER_INFO";
	public static final String JOB_CUSTOMER = "/jobcustomer";
	public static final String CSP_WORK_ORDER_URL = "CSP_WORK_ORDER_URL";
	public static final String CSP_GLYMPSE_SESSION_DETAILS_OR_UPDATE_URL = "CSP_GLYMPSE_SESSION_DETAILS_OR_UPDATE_URL";

	public static final String SAML_STORE_FILE = "storeFile";
	public static final String SAML_STORE_PASS = "storePass";
	public static final String SAML_KEYS = "keys";
	public static final String SAML_KEYS_PASS = "keysPass";
	public static final String SAML_DEFAULT_KEY = "defaultKey";
	public static final String TECH_STATUS_NOT_UPDATED = "TECH_STATUS_NOT_UPDATED";
	public static final String TECH_STATUS_UPDATED = "TECH_STATUS_UPDATED";
	public static final String TELEMATICS_DATA_PUSHED = "TELEMATICS_DATA_PUSHED";
	public static final String TELEMATICS_DATA_NOT_PUSHED = "TELEMATICS_DATA_NOT_PUSHED";
	public static final String TELEMATICS_AUTHORIZATION_KEY = "TELEMATICS_AUTHORIZATION_KEY";
	public static final String TELEMATICS_LOCATION_UPDATE_URL = "TELEMATICS_LOCATION_UPDATE_URL";
	public static final String TELEMATICS_ACCESSTOKEN_URL = "TELEMATICS_ACCESSTOKEN_URL";
	public static final String WFA_TECH_STATUS_UPDATE_URL = "WFA_TECH_AND_WORKORDER_STATUS_UPDATE_URL";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String WORK_ORDER_BY_ACCOUNT_NUM_URL = "WORK_ORDER_BY_ACCOUNT_NUM_URL";
	public static final String WORK_ORDER_BY_TECH_ID_URL = "WORK_ORDER_BY_TECH_ID_URL";
	public static final String WFA_TECH_STATUS_URL = "WFA_TECH_STATUS_URL";


	public static final String REDIS_CACHE_KEY_WFA_CACHE_KEY = "REDIS_CACHE_KEY_WFA_CACHE_KEY";
	public static final String REDIS_CACHE_KEY_TELEMETIC_CACHE_KEY = "REDIS_CACHE_KEY_TELEMETIC_CACHE_KEY";	
	public static final String HTTPCLIENT_EXCEPTION_MESSAGE = "HttpClientException Occurred";
	public static final String INTERNAL_SERVICE_MESSAGE = "Internal Service Error";
	public static final String Y = "Y";

	public static final String METHOD_GET = "GET";
	public static final String HTTP_HEADER_ACCEPT = "Accept";
	public static final String HTTP_HEADER_APPLICATION_JSON = "application/json";

	// CXP related configuration
	public static final String CXP_OAUTH_URL = "CXP_OAUTH_URL";
	public static final String CXP_OAUTH_PAYLOAD = "CXP_OAUTH_PAYLOAD";
	public static final String CXP_SESSION_URL = "CXP_SESSION_URL";
	public static final String CXP_SERVICES_URL = "CXP_SERVICES_URL";
	public static final String SOURCE_ID = "CXP_SOURCEID";
	public static final String TRACKING_ID = "TRACKING_ID";

	// User Profile related information
	public static final String COMCAST_NOW_KEY = "COMCAST_NOW_KEY";
	public static final String COMCAST_NOW_URL = "COMCAST_NOW_URL";

	public static final String CUSTOMER_ACCOUNT_URL = "CUSTOMER_ACCOUNT_URL";
	public static final String ACCOUNT_URL = "ACCOUNT_URL";

	//Kafka Customer Timeline Info
	public static final String CXE_KAFKA_TIMELINE = "CXE_FOR_CUSTOMER_TIMELINE_URL";
	public static final String TECHNUCLEUS_PROCESS_NAME = "PROCESS_NAME_FOR_CUSTOMER_TIMELINE";
	public static final String TIMELINE_KAFKA_ACCESS_TOKEN= "CUSTOMER_TIMELINE_ACCESS_TOKEN";
	public static final String CXP_BUFFER_SERVICE = "CXP_BUFFER_SERVICE";
	public static final String CXP_BUFFER_SERVICE_SID = "CXP_BUFFER_SERVICE_SID";
	public static final String CXP_BUFFER_SERVICE_SAUTH_KEY = "CXP_BUFFER_SERVICE_SAUTH_KEY";
	
	public static final String WFA_TECH_BUFFER_URL = "WFA_TECH_BUFFER_URL";
	public static final String WFA_TECH_DEVICE_ADD = "WFA_TECH_DEVICE_ADD";

	public static final String CSP_CODE_BIG_CONSUMER_KEY = "CSP_CODE_BIG_CONSUMER_KEY";
	public static final String CSP_CODE_BIG_CONSUMER_SECRET_KEY = "CSP_CODE_BIG_CONSUMER_SECRET_KEY";

	// ESP Services user/pswd info
	public static final String ESP_SERVICE_USER_NAME = "ESP_SERVICE_USER_NAME";
	public static final String ESP_SERVICE_PASSWORD = "ESP_SERVICE_PASSWORD";
	
	
	// ESP Services user/pswd info
	public static final String REFRESH_EVENT_SERVICE_USER_NAME = "REFRESH_EVENT_SERVICE_USER_NAME";
	public static final String REFRESH_EVENT_SERVICE_PASSWORD =  "REFRESH_EVENT_SERVICE_PASSWORD";

	// OfferManagementService info
	public static final String SOAP_OFFER_MANAGEMENT_SERVICE_URL = "SOAP_OFFER_MANAGEMENT_SERVICE_URL";

	//Location Service info
	public static final String SOAP_LOCATION_SERVICE_URL = "SOAP_LOCATION_SERVICE_URL";
	
	//Maestro KB related constants
	public static final String MAESTRO_KB_USERNAME= "MAESTRO_KB_USERNAME";
	public static final String MAESTRO_KB_USER_ROLE= "MAESTRO_KB_USER_ROLE";
	public static final String MAESTRO_KB_VIEW= "MAESTRO_KB_VIEW";
	public static final String MAESTRO_CODE_BIG_URL = "MAESTRO_CODE_BIG_URL";
	public static final String MAESTRO_CODE_BIG_CONSUMER_KEY = "MAESTRO_CODE_BIG_CONSUMER_KEY";
	public static final String MAESTRO_CODE_BIG_SECRET_KEY = "MAESTRO_CODE_BIG_SECRET_KEY";
	
	public static final String SOAP_ESP_CONTRACT_SERVICE_URL= "SOAP_ESP_CONTRACT_SERVICE_URL";
	// CAAP URL
	public static final String CAAP_ACTIVATION_URL = "CAAP_ACTIVATION_URL";
	public static final String CAAP_ACTIVATION_RETURN_URL = "CAAP_ACTIVATION_RETURN_URL";
	public static final String CAAP_ACTIVATION_HMAC_KEY = "CAAP_ACTIVATION_HMAC_KEY";
	public static final String CAAP_ACTIVATION_VERIFICATION_RETURN_URL = "CAAP_ACTIVATION_VERIFICATION_RETURN_URL" ;
	
	public static final String TECHNUCLEUS_XH_SUB_RETURN_URL = "TECHNUCLEUS_XH_SUB_RETURN_URL";
	
	public static final String ESP_INT_USER_ID = "ESP_INT_USER_ID";
	public static final String ESP_INT_PASSWORD = "ESP_INT_PASSWORD";
	
	// Account Refresh Constants
	public static final String XBO_REFRESH_CODE_BIG_URL = "XBO_REFRESH_CODE_BIG_URL";
	public static final String XRAY_CODE_BIG_CONSUMER_KEY = "XRAY_CODE_BIG_CONSUMER_KEY";
	public static final String XRAY_CODE_BIG_SECRET_KEY = "XRAY_CODE_BIG_SECRET_KEY";
	
	public static final String EHH_DEVICE_HIT_URL = "EHH_DEVICE_HIT_URL";
	
	public static final String UDB_REFRESH_CONFIG_TIME = "UDB_REFRESH_CONFIG_TIME";
	public static final String SOAP_REFRESHEVENT_SERVICE_URL = "SOAP_REFRESHEVENT_SERVICE_URL";
		public static final String CONTACT_SERVICE_URL = "CONTACT_SERVICE_URL";
	public static final String SCHEDULE_SERVICE_URL = "SCHEDULE_SERVICE_URL";
	public static final String CONTACT_SERVICE_NO_OF_DAYS = "CONTACT_SERVICE_NO_OF_DAYS";
	public static final String ESP_USER = "ESP_USER";
	public static final String ESP_PASSWORD = "ESP_PASSWORD";
	
}
