package com.comcast.technucleus.application.services.client.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
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
import org.springframework.http.HttpStatus;

import com.comcast.technucleus.application.constants.PropertiesConstants;
import com.comcast.technucleus.application.constants.ServicesConstants;
import com.comcast.technucleus.application.exception.ApplicationClientException;
import com.comcast.technucleus.application.exception.ApplicationClientException.CEErrorCode;
import com.comcast.technucleus.application.properties.Properties;
import com.comcast.technucleus.application.services.client.LocationServiceClient;
import com.comcast.xml.location.services.LocationServiceFault;
import com.comcast.xml.location.services.LocationServicePort;
import com.comcast.xml.location.services.QueryMarket;
import com.comcast.xml.location.types.ArrayOfMarketReferenceType;
import com.comcast.xml.location.types.BillingSystemType;
import com.comcast.xml.location.types.LegacyMarketIDCriteriaType;
import com.comcast.xml.location.types.MarketReferenceType;
import com.comcast.xml.location.types.QueryMarketAddressSearchType;
import com.comcast.xml.location.types.QueryMarketRequestType;
import com.comcast.xml.location.types.QueryMarketResponseType;
import com.comcast.xml.types.MessageType;
import com.comcast.xml.types.MessagesType;
import com.comcast.xml.types.RequestHeader;
import com.comcast.xml.types.ServiceFault;

/******************************************************************************************************
 * File Name : LocationServiceImpl.java 
 * 
 * Description : This class makes the network calls to Location Service to retreive marketID
 * 
 *****************************************************************************************************/
public class LocationServiceClientImpl implements LocationServiceClient
{

	private final static Logger log = LoggerFactory.getLogger(LocationServiceClientImpl.class);


	private Properties properties;

	private LocationServicePort locationServicePort;

	@Autowired
	public LocationServiceClientImpl(Properties properties)
	{
		try{
			this.properties = properties;
			this.locationServicePort = buildLocationServicePort();
			log.debug("LocationServiceClientImpl: Successfully created ContactServicePort");
			setRequestHeader((BindingProvider) locationServicePort);	
			log.debug("SchedulingServiceHandlerImpl: Successfully created ContactServicePort");
		}catch(Exception e)
		{
			this.locationServicePort =null;
			log.error("Exception while Creating SchedulingServicePort", e);
		}
	}

	/**
	 * Method Name : locationServicePort
	 * Description : This method build buildContactServicePort to make the buildContactService call.
	 * 
	 * 
	 * @return GrandslamAccountServicePort
	 */

	private LocationServicePort buildLocationServicePort()
	{
		log.debug("buildLocationServicePort: Started building buildLocationServicePort");
		Map<String,Object> outProps=new HashMap<String,Object>();
		outProps.put("action","UsernameToken");
		outProps.put("passwordType","PasswordText");
		outProps.put("user",properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.ESP_USER));
		outProps.put("passwordCallbackClass","com.comcast.technucleus.util.ContactAndSchedulingCallback");
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(LocationServicePort.class);
		String soapLocationServiceURL = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.SOAP_LOCATION_SERVICE_URL);
		factory.setAddress(soapLocationServiceURL+ "?wsdl");
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
		log.debug("buildLocationServicePort: Completed building GrandslamAccountServicePort");
		return  (LocationServicePort)factory.create();
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
		requestHeader.setSourceSystemId(ServicesConstants.NODE_HEALTH);
		requestHeader.setSourceServerId(ServicesConstants.NODE_HEALTH_EEH_SERVER);
		requestHeader.setTrackingId(UUID.randomUUID().toString());
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(new Date());
		requestHeader.setTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
		bindingProvider.getRequestContext().put(Header.HEADER_LIST, Arrays.asList(new Header(
				new QName(ServicesConstants.NAMESPACE_URI, ServicesConstants.REQUEST_HEADER), requestHeader,
				new JAXBDataBinding(RequestHeader.class))));
		log.debug("setRequestHeader: request header set successfully ");
	}


	/**
	 * Method Name : getMarketReferenceId
	 * Description : This method makes a call to Location service and receives the response
	 * 
	 * @param  String 	city
	 * @param  String 	state
	 * @param  String 	zipCode
	 * @return String 	marketId 
	 */
	public String getMarketReferenceId(String city, String state, String zipCode, String businessUnit) {

		log.debug("getMarketReferenceId: Constructing the request for Location Service");
		QueryMarket queryMarket = new QueryMarket();
		QueryMarketRequestType queryMarketRequestType = new QueryMarketRequestType();
		QueryMarketAddressSearchType queryMarketAddressSearchType = new QueryMarketAddressSearchType();
		queryMarketAddressSearchType.setCity(city);
		queryMarketAddressSearchType.setState(state);
		queryMarketAddressSearchType.setZip(zipCode);
		queryMarketRequestType.setAddressSearch(queryMarketAddressSearchType);
		LegacyMarketIDCriteriaType legacyMarketIDCriteriaType = new LegacyMarketIDCriteriaType();
		legacyMarketIDCriteriaType.setBillingSystemMarketID(businessUnit);
		legacyMarketIDCriteriaType.setBillingSystem(BillingSystemType.CSG);
		queryMarketRequestType.setLegacyIDSearch(legacyMarketIDCriteriaType);
		queryMarket.setQueryMarket(queryMarketRequestType);

		QueryMarketResponseType queryMarketResponse = null;

		if(null != locationServicePort) {
			try {
				queryMarketResponse = locationServicePort.queryMarket(queryMarketRequestType);
				if(null != queryMarketResponse) {
					return getMarketIdFromResponse(queryMarketResponse);
				} else {
					throw new ApplicationClientException(CEErrorCode.LOCATION_SERVICE_SOAP_RESPONSE_IS_NULL, HttpStatus.INTERNAL_SERVER_ERROR.toString());
				}
			} catch(LocationServiceFault ex) {
				String soapFaultMessage = getSoapFaultMessage(ex);
				log.error("getMarketReferenceId: Soap error response from Location service with details -{}", ex);
				throw new ApplicationClientException(CEErrorCode.LOCATION_SERVICE_SOAP_ERROR_RESPONSE, soapFaultMessage, HttpStatus.INTERNAL_SERVER_ERROR.toString(),ex);

			} catch(ApplicationClientException ex) {
				throw ex;
			}catch (Exception ex) {

				log.error("getMarketReferenceId: Unknown exception from Location Service-{}",ex);
				throw new ApplicationClientException(CEErrorCode.LOCATION_SERVICE_UNKNOWN_ERROR, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex);
			}
		}
		throw new ApplicationClientException(CEErrorCode.LOCATION_SERVICE_PORT_IS_NULL, HttpStatus.INTERNAL_SERVER_ERROR.toString());
	}


	private String getSoapFaultMessage(LocationServiceFault ex) {
		String soapFaultMessage = "";
		ServiceFault faultInfo = ex.getFaultInfo();
		if(null != faultInfo) {
			MessagesType messages = faultInfo.getMessages();
			if(null != messages) {

				MessageType messageType = messages.getMessages().get(0);
				if(null != messageType) {
					soapFaultMessage = messageType.getCode() + "--" + messageType.getText();
				}
			}
		}
		return soapFaultMessage;
	}

	/**
	 * Method Name : getMarketIdFromResponse
	 * Description : This method retrieves the marketId from soap response 
	 * 
	 * @param  QueryMarketResponse 	response
	 * @return String 	marketId 
	 */
	private String getMarketIdFromResponse(QueryMarketResponseType response) {

		String marketId = "";
		if(null != response) {
			ArrayOfMarketReferenceType marketReference = response.getMarketReference();
			if(null != marketReference) {
				List<MarketReferenceType> marketReferenceTypes = marketReference.getMarketReferenceTypes(); 
				if(null != marketReferenceTypes && !marketReferenceTypes.isEmpty())  {
					for(MarketReferenceType marketReferenceType : marketReferenceTypes) {
						long marketID = marketReferenceType.getMarketID();
						marketId = String.valueOf(marketID);
						break;
					}
				}
			}
		}
		return marketId;
	}
}
