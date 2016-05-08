package com.comcast.technucleus.application.router.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comcast.technucleus.application.model.ContactInfo;
import com.comcast.technucleus.application.router.ContactServiceRouter;
import com.comcast.technucleus.application.services.client.ContactServiceClient;
import com.comcast.xml.offermgmt.types.TcopBillingCodeMapping;
import com.google.common.base.Throwables;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;

@Component
public class ContactServiceRouterImpl  implements ContactServiceRouter
{
	
	@Autowired
	private ContactServiceClient serviceHandler;
	
	private final static Logger log = LoggerFactory
			.getLogger(LocationServiceRouterImpl.class.getName());

	@HystrixCommand(groupKey="ContactService", commandKey ="getContactList", fallbackMethod="getContactListFallBack",
			commandProperties={ 
			@HystrixProperty(name="execution.timeout.enabled", value="false")},
			threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "20"),
					@HystrixProperty(name = "maxQueueSize", value = "200"),
					@HystrixProperty(name = "keepAliveTimeMinutes", value = "10"),
					@HystrixProperty(name = "queueSizeRejectionThreshold", value = "190")
	})
	@Override
	public Future<List<ContactInfo>> getContactList(String accountNumber, String scheduleDate)
	{	
		return new AsyncResult<List<ContactInfo>>(){
			@Override 
			public List<ContactInfo> invoke() 
			{
				return serviceHandler.getContactList(accountNumber, scheduleDate);
			}
		};
	}	
	public List<ContactInfo> getContactListFallBack(String accountNumber, String Date , Throwable t)
	{	
		List<ContactInfo> contactList = new ArrayList<ContactInfo>();

		if(null != t) {
			log.error("getContactListFallBack: Hystrix command execution failed with error - {}",t);
			return contactList;
		} else{
			log.error("getContactListFallBack: Timeout occurred while executing hystrix command");
			return contactList;
		}
	}
}
