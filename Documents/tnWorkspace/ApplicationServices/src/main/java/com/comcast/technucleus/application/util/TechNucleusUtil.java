/*
 * @author
 * @date Jun 9, 2015
 * @project TechNucleus
 */
package com.comcast.technucleus.application.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import com.comcast.technucleus.application.configuration.RestTemplateConfig;
import com.comcast.technucleus.application.constants.ServicesConstants;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

// TODO: Auto-generated Javadoc
/**
 * The Class TechNucleusUtil.
 */
public class TechNucleusUtil {

	private static final Logger log = LoggerFactory.getLogger(TechNucleusUtil.class.getName());

	@Autowired
	private RestTemplateConfig config;

	/**
	 * Checks if is account number.
	 *
	 * @param searchVariable
	 *            the search variable
	 * @return true, if is account number
	 */
	public static boolean isAccountNumber(String searchVariable) {

		boolean isAccountNum = false;

		// There is two type of Account number
		if (searchVariable.length() == 16 || searchVariable.length() == 13) {

			try {
				Long.parseLong(searchVariable);
			} catch (NumberFormatException e) {
				log.error("NumberFormatException while validating account number");
			}
			isAccountNum = true;
		}

		return isAccountNum;
	}

	/**
	 * Checks if is phone number.
	 *
	 * @param phoneNo
	 *            the phone no
	 * @return true, if is phone number
	 */
	public static boolean isPhoneNumber(String phoneNo) {
		boolean isNumber = false;
		// validate phone numbers of format "1234567890" or 
		// validating phone number with -, . or spaces or 
		// validating phone number where area code is in braces ()
		if ((phoneNo.matches(ServicesConstants.TELEPHONE_REGEX_NO_SPACES_OR_DASH))
				|| (phoneNo.matches(ServicesConstants.TELEPHONE_REGEX_WITH_DASH_OR_DOTS_OR_SPACES))
				|| (phoneNo.matches(ServicesConstants.TELEPHONE_REGEX_WITH_BRACES))) {
			isNumber = true;
		}
		return isNumber;
	}

	/**
	 * Checks if is firstand last name valid.
	 *
	 * @param searchVariable
	 *            the search variable
	 * @return true, if is firstand last name valid
	 */
	public static boolean isFirstandLastNameValid(String searchVariable) {

		boolean isNameValid = false;
		if (searchVariable != null && !searchVariable.isEmpty()) {

			try {
				Pattern pattern = Pattern.compile(ServicesConstants.NAME_REGEX);
				Matcher matcher = pattern.matcher(searchVariable);
				isNameValid = matcher.matches();
			} catch (NumberFormatException e) {
				log.debug("isFirstandLastNameValid : " + e.getMessage());
			}
			isNameValid = true;
		}
		return isNameValid;
	}

	/**
	 * Gets the file from resources for a given file name.
	 *
	 * @param fileName
	 *            the file name
	 * @return the String
	 */
	public static String getFileFromResource(String fileName) {
		String result = "";
		ClassLoader classLoader = TechNucleusUtil.class.getClassLoader();
		try {
			result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
		} catch (IOException e) {
			log.error("Get File From Resource : getFileWithFromResource :{} ", e.getMessage(), e);
		}
		return result;
	}

	/**
	 * Gets the requested format type time.
	 *
	 * @param formatType
	 *            the format type
	 * @return the requested format type time
	 */
	public static String getRequestedFormatType(String formatType) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		return sdf.format(date);
	}

	public String formatAccountNumber(String accountNumber) {
		return StringUtils.removePattern(accountNumber, "\"");
	}

	public static boolean isWorkOrderNum(String searchVariable) {
		boolean isWorkOrderNum = false;
		if (searchVariable.length() == 20 && StringUtils.isNumeric(searchVariable)) {

			isWorkOrderNum = true;
		}
		return isWorkOrderNum;
	}

	public static boolean isTechNum(String searchVariable) {

		boolean isWorkOrderNum = false;

		if (searchVariable.length() == 4) {
			try {
				Long.parseLong(searchVariable);
				isWorkOrderNum = true;
			} catch (NumberFormatException e) {
				log.error("isWorkOrderNum : {} is not a valid worknumber ", searchVariable);
			}
		}
		return isWorkOrderNum;
	}

	public static boolean isNullOrEmpty(String str) {
		if (str == null || (str != null && str.isEmpty()))
			return true;
		return false;
	}

	public static JsonObject getJsonObject(String jsonString){
		JsonParser parser = new JsonParser();
		return parser.parse(jsonString).getAsJsonObject();
	}
	/**
	 * Gets the value using key.
	 *
	 * @param json
	 *            the json
	 * @return the value
	 */
	public static String getValueFromJson(String json, String keyInput) {

		JSONObject jObject = new JSONObject(json);
		return (String) jObject.get(keyInput);
	}

	public static JsonObject convertStringToJSON(String jsonString) {

		JsonObject jsonObject = new JsonObject();

		JsonParser parser = new JsonParser();
		JsonElement jsonElement = parser.parse(jsonString);
		jsonObject = jsonElement.getAsJsonObject();

		return jsonObject;
	}

	public static void addRequestHeader(WebServiceMessage message) {
		SaajSoapMessage soapMessage = (SaajSoapMessage) message;
		SOAPMessage saajMessage = soapMessage.getSaajMessage();

		SOAPHeader soapHeaderNew = null;
		try {
			soapHeaderNew = saajMessage.getSOAPHeader();

			QName requestHeaderQname = new QName("http://xml.comcast.com/types", "requestHeader");

			SOAPHeaderElement requestHeaderELement = soapHeaderNew.addHeaderElement(requestHeaderQname);


			GregorianCalendar c = new GregorianCalendar();
			c.setTime(new Date());
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm");
			String timeStamp = formatter.format(c.getTime());


			SOAPElement timestamp = requestHeaderELement.addChildElement("timestamp");
			timestamp.addTextNode(timeStamp);


			SOAPElement sourceSystemId = requestHeaderELement.addChildElement("sourceSystemId");
			sourceSystemId.addTextNode("TechNucleus");

			SOAPElement sourceSystemUserId = requestHeaderELement.addChildElement("sourceSystemUserId");
			sourceSystemUserId.addTextNode("TechNucleusSystem");

			SOAPElement sourceServerId = requestHeaderELement.addChildElement("sourceServerId");
			sourceServerId.addTextNode("TechNucleusServerId");

			SOAPElement trackingId = requestHeaderELement.addChildElement("trackingId");
			trackingId.addTextNode(UUID.randomUUID().toString());

		} catch (SOAPException e) {
			log.error("addRequestHeader: SOAP Exception :" + e.getMessage());
		}
	}


	public static HttpStatus getHttpStatus(String httpStatusCode) {
		if(null != httpStatusCode) {
			if(httpStatusCode.equalsIgnoreCase("401")) {
				return HttpStatus.UNAUTHORIZED;
			} else if(httpStatusCode.equalsIgnoreCase("400")) {
				return HttpStatus.BAD_REQUEST;
			} else if(httpStatusCode.equalsIgnoreCase("404")) {
				return HttpStatus.NOT_FOUND;
			} else  {
				return HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
}
