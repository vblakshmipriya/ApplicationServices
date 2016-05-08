package com.comcast.technucleus.application.services.client.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.CXFBusFactory;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comcast.technucleus.application.constants.PropertiesConstants;
import com.comcast.technucleus.application.exception.ApplicationServiceException;
import com.comcast.technucleus.application.model.ContactInfo;
import com.comcast.technucleus.application.properties.Properties;
import com.comcast.technucleus.application.services.client.ContactServiceClient;
import com.comcast.xml.contact.types.BillingNote;
import com.comcast.xml.contact.types.CITNote;
import com.comcast.xml.contact.types.EmailContact;
import com.comcast.xml.contact.types.HealthCheckNote;
import com.comcast.xml.contact.types.QueryContactResponse;
import com.comcast.xml.contact.services.ContactServiceFault;
import com.comcast.xml.contact.services.ContactServicePort;
import com.comcast.xml.contact.types.QueryContact;
import com.comcast.xml.contact.types.QueryContactType;
import com.comcast.xml.types.RequestHeader;

/******************************************************************************************************
 * 
 * File Name : ContactServiceHandlerImpl.java 
 * Description : This Class makes makes call to ContactServiceHandler and Contact details
 * 										
 *****************************************************************************************************/
@Component
public class ContactServiceClientImpl implements ContactServiceClient
{
	private final static Logger log = LoggerFactory
			.getLogger(ContactServiceClientImpl.class.getName());


	private Properties properties;

	private ContactServicePort contactService;

	@Autowired
	public ContactServiceClientImpl(Properties properties)
	{
		try{
			this.properties = properties;
			contactService = buildContactServicePort();
			log.debug("SchedulingServiceHandlerImpl: Successfully created ContactServicePort");
			setRequestHeader((BindingProvider) contactService);	
			log.debug("SchedulingServiceHandlerImpl: Successfully created ContactServicePort");
		}catch(Exception e)
		{
			contactService =null;
			log.error("Exception while Creating SchedulingServicePort", e);
		}
	}


	private static final String REQUEST_HEADER = "requestHeader";
	private static final String NAMESPACE_URI = "http://xml.comcast.com/types";
	private static final String NODE_HEALTH = "NODE_HEALTH";
	private static final String NODE_HEALTH_EEH_SERVER = "NodeHealth_EHHServer";

	/**
	 * Method Name : getContactList
	 * Description : This method call query contact API and retrieved all the contact details 
	 * 
	 * @param       String         accountNumber
	 * @param       String         Date                 
	 * @return	    List<ContactInfo>
	 */

	@Override
	public List<ContactInfo> getContactList(String accountNumber , String Date) 
	{
		List<ContactInfo> results = null;
		try
		{
			log.debug("getContactList: Request came for Contact Service "+ accountNumber);
			long todaysDate =   getDate(Date);
			GregorianCalendar c = new GregorianCalendar();
			c.setTimeInMillis(todaysDate);
			XMLGregorianCalendar toDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			int numberOfDays  =Integer.parseInt( properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES 
					+ PropertiesConstants.CONTACT_SERVICE_NO_OF_DAYS));
			Long difference = new Long("86400000");
			GregorianCalendar calender = new GregorianCalendar();
			calender.setTimeInMillis(todaysDate - (numberOfDays * difference));
			XMLGregorianCalendar fromDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calender);
			QueryContactResponse queryContactResponse = contactService.queryContact(accountNumber, fromDate, 
					toDate, true, QueryContactType.ALL);
			log.debug("getContactList: retrived customer comment List with count : "+ queryContactResponse);		
			results = buildResponse(queryContactResponse);
			log.debug("getContactList: Successfully build the request contact service ");
		}catch(ContactServiceFault e)
		{
			throw new ApplicationServiceException("getContactList : ContactServiceFault while calling Contact Service",e);
		}catch(DatatypeConfigurationException e)
		{
			throw new ApplicationServiceException("getContactList : DatatypeConfigurationException"
					+ " while calling Contact Service",e);	
		}catch(Exception e)
		{
			throw new ApplicationServiceException("getContactList : An execption "
					+ "occured while calling Contact Service",e);
		}
		return results;
	}


	private long getDate(String date) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date dt = simpleDateFormat.parse(date);
		return dt.getTime();
	}


	/**
	 * Method Name : buildResponse
	 * Description : This method build response form QueryContactResponse which is the result of query contact api.
	 * 
	 * @param      QueryContactResponse 		queryContactResponse
	 * 
	 * @return List<ContactInfo>
	 */

	private List<ContactInfo> buildResponse(QueryContactResponse queryContactResponse)
	{
		if(queryContactResponse == null || queryContactResponse.getQueryContact() == null ||
				queryContactResponse.getQueryContact().getQueryContacts() == null || 
				queryContactResponse.getQueryContact().getQueryContacts().size() ==0)
		{
			return null;
		}
		List<QueryContact> queryContacts = queryContactResponse.getQueryContact().getQueryContacts();
		List<ContactInfo> contactInfoList = new ArrayList<ContactInfo>();
		for(QueryContact queryContact : queryContacts)
		{
			ContactInfo contactInfo = new ContactInfo();

			contactInfo.setType(queryContact.getType());
			if (queryContact instanceof BillingNote)
			{
				BillingNote billingNotes =  (BillingNote) queryContact;
				contactInfo.setComments(billingNotes.getComments());
				contactInfo.setSource(billingNotes.getSource());
				contactInfo.setType(billingNotes.getType());
				contactInfo.setDate(getDateAsString(billingNotes.getEndTime()));
				contactInfoList.add(contactInfo); 
			}else if(queryContact instanceof CITNote)
			{
				CITNote citNote =  (CITNote) queryContact; 
				contactInfo.setComments(citNote.getText());
				contactInfo.setSource(citNote.getSource());
				contactInfo.setType(citNote.getType());
				contactInfo.setDate(getDateAsString(citNote.getUpdatedDate()));
				contactInfoList.add(contactInfo); 	  
			}else if(queryContact instanceof HealthCheckNote)
			{
				HealthCheckNote healthCheckNote = (HealthCheckNote) queryContact;
				contactInfo.setComments(healthCheckNote.getComments());
				contactInfo.setType(healthCheckNote.getType());
				contactInfo.setSource(healthCheckNote.getSource());
				contactInfo.setDate(getDateAsString(healthCheckNote.getEndTime()));
				contactInfoList.add(contactInfo); 	   
			}else if(queryContact instanceof EmailContact)
			{
				EmailContact emailContact =  (EmailContact) queryContact;
				contactInfo.setComments(emailContact.getSubject());
				contactInfo.setType(emailContact.getType());
				contactInfo.setSource(emailContact.getDeliveryType());
				contactInfo.setDate(getDateAsString(emailContact.getDeliveryTime()));
				contactInfoList.add(contactInfo);  
			}
		}
		return contactInfoList;
	}


	private String getDateAsString(XMLGregorianCalendar endTime) 
	{
		Calendar calendar = endTime.toGregorianCalendar();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		formatter.setTimeZone(calendar.getTimeZone());
		return formatter.format(calendar.getTime());
	}


	/**
	 * Method Name : buildContactServicePort
	 * Description : This method build buildContactServicePort to make the buildContactService call.
	 * 
	 * 
	 * @return GrandslamAccountServicePort
	 */

	private ContactServicePort buildContactServicePort()
	{
		log.debug("buildGrandslamAccountServicePort: Started building GrandslamAccountServicePort");
		Map<String,Object> outProps=new HashMap<String,Object>();
		outProps.put("action","UsernameToken");
		outProps.put("passwordType","PasswordText");
		outProps.put("user",properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.ESP_USER));
		outProps.put("passwordCallbackClass","com.comcast.technucleus.util.ContactAndSchedulingCallback");
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(ContactServicePort.class);
		factory.setAddress(properties
				.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.CONTACT_SERVICE_URL).trim()
				+ "?wsdl");
		LoggingInInterceptor loggingInInterceptor=new LoggingInInterceptor();
		loggingInInterceptor.setPrettyLogging(true);
		LoggingOutInterceptor loggingOutInterceptor=new LoggingOutInterceptor();
		loggingOutInterceptor.setPrettyLogging(true);
		// Added to log the SOAP requests/responses
		Bus bus = new CXFBusFactory().createBus();
		bus.getInInterceptors().add(loggingInInterceptor);
		bus.getOutInterceptors().add(loggingOutInterceptor);
		WSS4JOutInterceptor wss4jOut=new WSS4JOutInterceptor(outProps);
		factory.getOutInterceptors().add(wss4jOut);
		log.debug("buildGrandslamAccountServicePort: Completed building GrandslamAccountServicePort");
		return  (ContactServicePort)factory.create();
	}

	/**
	 * Method Name : setRequestHeader
	 * Description : This Method will set Request Header
	 * 
	 * 
	 * @param BindingProvider bindingProvider
	 * 
	 */

	private void setRequestHeader(BindingProvider bindingProvider)
			throws DatatypeConfigurationException, JAXBException  {
		bindingProvider.getRequestContext().remove(Header.HEADER_LIST);
		RequestHeader requestHeader = new RequestHeader();
		requestHeader.setSourceSystemId(NODE_HEALTH);
		requestHeader.setSourceServerId(NODE_HEALTH_EEH_SERVER);
		requestHeader.setTrackingId(UUID.randomUUID().toString());
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(new Date());
		requestHeader.setTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		bindingProvider.getRequestContext().put(Header.HEADER_LIST, Arrays.asList(new Header(
				new QName(NAMESPACE_URI, REQUEST_HEADER), requestHeader,
				new JAXBDataBinding(RequestHeader.class))));
		log.debug("setRequestHeader: request header set successfully ");
	}



}
