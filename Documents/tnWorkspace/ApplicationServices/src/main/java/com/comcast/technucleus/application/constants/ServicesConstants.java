package com.comcast.technucleus.application.constants;

public class ServicesConstants {
	
	public static final String REQUEST_HEADER = "requestHeader";
	public static final String NAMESPACE_URI = "http://xml.comcast.com/types";
	public static final String NODE_HEALTH = "NODE_HEALTH";
	public static final String NODE_HEALTH_EEH_SERVER = "NodeHealth_EHHServer";

	public static final String WORK_ORDER_DETAIL = "WorkOrderDetails";
	public static final String WORK_ORDER_LIST = "WorkOrderList";
	public static final String WORK_ORDER_STATUS = "WorkOrderStatus";
	public static final String KEY_WORK_ORDER_NUMBER = "WorkOrderNum";
	public static final String KEY_WORK_ORDER_COMMENT= "comments";
	public static final String KEY_JOB = "Job";
	public static final String KEY_TIME_SLOT_START_TIME = "TimeSlotStartTime";
	public static final String TECH_ON_JOB = "OnJob";
	public static final String TECH_STATUS = "Status";
	public static final String TECH_AVAILABLE = "Available";
	public static final String WO_DISPLAY_STATUS = "DisplayStatus";
	public static final String JOB = "Job";
	public static final String LATITUDE = "Latitude";
	public static final String LONGITUDE = "Longitude";
	public static final String JOB_LOCATION = "JobLocation";
	public static final String ACCOUNT_NUMBER = "AccountId";
	public static final String TECH_ENROUTE = "Enroute";
	public static final String JOB_CUSTOMER = "JobCustomer";
	public static final String ACTION_CODE = "ActionCd";
	public static final String ADDRESS = "Address";
	public static final String CITY = "City";
	public static final String STATE = "State";
	public static final String ZIP_CODE = "ZipCode";
	public static final String JOB_PRODUCT_LIST = "JobProductList";
	public static final String PARENT_PRODUCT_CODE = "ParentProductCd";
	public static final String CHILD_PRODUCT_CODE =  "ChildProductList";
	public static final String JOB_CLASS_CD = "JobClassCd";
	public static final String BUSINESS_UNIT = "BusinessUnit";
	
	public static final String ACTION_ENROUTE = "ENROUTE";
	public static final String ACTION_START= "START";
	public static final String ACTION_COMPLETED = "COMPLETED";
	
	public static final String WORK_ORDER_STATUS_ENROUTE_DISABLED = "ENROUTE_DISABLED";
	public static final String WORK_ORDER_STATUS_ENROUTE_ENABLED =  "ENROUTE_ENABLED";
	public static final String WORK_ORDER_STATUS_START_JOB_ENABLED = "START_JOB_ENABLED";
	public static final String WORK_ORDER_STATUS_START_JOB_DISABLED = "START_JOB_DISABLED";
	public static final String WORK_ORDER_STATUS_START_JOB = "START_JOB";
	public static final String WORK_ORDER_STATUS_COMPLETED_JOB = "COMPLETE_JOB";
	public static final String WORK_ORDER_STATUS_COMPLETED_DISABLED= "COMPLETED_DISABLED";
	
	
	
	public static final String WORKORDER_COLL_NAME = "work_order_details";
	public static final String WORKORDER_PACKAGE_DETAIL_COLL_NAME= "work_order_package_details";
	
	public static final String UDB_COLL_NAME = "udb_refresh_details";
	
	public static final String DATA_ARRAY = "Data";
	public static final String TECH_NUM = "techNum";
	public static final String FULFILLMENT_CENTER = "fulfillmentCenter";
	public static final String SCHEDULE_DATE = "scheduledDate";
	public static final String WORK_ORDER_NUMBER = "workorderNumber";
	public static final String CACHE_KEY = "_id";
	public static final String KEY_SEQUENCE_NUMBER = "SeqNo";
	
	public static final String  WFA_DEVICES =  "devices";
	public static final String  WFA_DEVICES_TO_BE_INSTALLED =  "deviceToBeInstalled";
	public static final String  WFA_DEVICES_SEQUENCE_NO =  "sequenceNo";
	public static final String  WFA_DEVICE_BILLING_CODE =  "billingCode";
	public static final String  WFA_DEVICE_STATUS =  "status";
	public static final String  WFA_DEVICE_PRODUCT_NAME =  "productName";
	public static final String  WFA_DEVICES_SERIAL_ID =  "deviceSerialID";
	public static final String  WFA_DEVICES_GROUP_ID=  "groupImageID";
	public static final String  WFA_DEVICES_ADD_FAILURE_ERROR_MESSGE = "errorMessage";
	public static final String  WFA_DEVICES_ADD_FAILURE_ERROR_CODE = "errorCode";
	
	public static final String  WFA_DEVICES_ADD_STATUS_NOT_STARTED =  "NOT_STARTED";
	public static final String  WFA_DEVICES_ADD_STATUS_PROGRESS =  "ADD_PROGRESS";
	public static final String  WFA_DEVICES_ADD_STATUS_COMPLETED =  "ADD_COMPLETED";
	public static final String  WFA_DEVICES_ADD_STATUS_FAILED =  "ADD_FAILED";
	
	public static final String XH_LINE_OF_SERVICE = "XhLineOfService";
	
	
	public static final String TELEPHONE_REGEX = "[^\\p{Digit}]+";

	public static final String TELEPHONE_REGEX_NO_SPACES_OR_DASH = "\\d{10}";

	public static final String TELEPHONE_REGEX_WITH_DASH_OR_DOTS_OR_SPACES = "\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}";

	public static final String TELEPHONE_REGEX_WITH_BRACES = "\\(\\d{3}\\)-\\d{3}-\\d{4}";

	public static final String NAME_REGEX = "^[a-zA-Z\\s]+$";

	public static final String STATUS_Y = "Y";

	public static final String STATUS_N = "N";
	
	public static final int RADIUS=6371;//radius of earth in Km  
	
	public static final String CONTENT_TYPE = "Content-Type";

}
