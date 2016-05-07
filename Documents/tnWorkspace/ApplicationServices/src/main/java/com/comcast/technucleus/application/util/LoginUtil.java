/*
 * @author
 * @date Jun 9, 2015
 * @project TechNucleus
 */
package com.comcast.technucleus.application.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.comcast.technucleus.application.constants.PropertiesConstants;
import com.comcast.technucleus.application.properties.Properties;

// TODO: Auto-generated Javadoc
/**
 * The Class SSOUtil.
 */

@SuppressWarnings("deprecation")
public class LoginUtil {
	
	private final static Logger log = LoggerFactory
			.getLogger(LoginUtil.class.getName());

	@Autowired
	private static Properties properties;

	/**
	 * Gets the auth token.
	 *
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the auth token
	 */

	@SuppressWarnings({ "resource" })
	public static HttpStatus getAuthToken(String userName, String password) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		// AuthTokenResponse authTokenResponse = null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			// logger.debug("In getAuthToken for - "+ userName);
			HttpGet httpget = new HttpGet(properties
					.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.SITEMINDER_ENDPOINT_CALL_URL).trim());
			HttpContext context = new BasicHttpContext();
			// Making a GET request to ping-federate URL to grab smagentname,
			// targetHostName and uri.
			HttpResponse response = httpclient.execute(httpget, context);

			// Retrieving smagentname, targetHostName and uri.
			HttpUriRequest currentReq = (HttpUriRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
			String uri = currentReq.getURI().toString();
			HttpHost targetHost = (HttpHost) context.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
			HttpEntity entity = response.getEntity();
			String pfSigningPageHTML = EntityUtils.toString(entity);

			String smagentname = getValueByFieldName("smagentname", pfSigningPageHTML, "\"");
			String targetHostName = targetHost.toString();

			// Preparing POST parameters to get authenticated from ping
			// federate.
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			HttpPost httpost = new HttpPost(targetHostName + uri);
			nvps.add(new BasicNameValuePair("USER", userName));
			nvps.add(new BasicNameValuePair("PASSWORD", password));
			nvps.add(new BasicNameValuePair("smagentname", smagentname));
			httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

			// Making a POST request to with smagentname and USER/PASSWORD.
			response = httpclient.execute(httpost, context);
			EntityUtils.toString(response.getEntity());

			// check SM session which direct us that user provides correct
			// credential or not.
			List<org.apache.http.cookie.Cookie> cookies = httpclient.getCookieStore().getCookies();

			if (cookies != null && !cookies.isEmpty())
				for (org.apache.http.cookie.Cookie cookie : cookies) {
					if (cookie.getName() != null && cookie.getName().indexOf("SMSESSION") != -1) {
						status = HttpStatus.ACCEPTED;
					}
				}
		} catch (Exception e)
		{
			log.debug("getAuthToken:Exception occured while retriving Auth Token ",e);		
			status = HttpStatus.BAD_GATEWAY;
		}
		return status;
	}

	/**
	 * Gets the value by field name.
	 *
	 * @param fieldName
	 *            the field name
	 * @param input
	 *            the input
	 * @param pattern
	 *            the pattern
	 * @return the value by field name
	 */

	private static String getValueByFieldName(String fieldName, String input, String pattern) {

		String searchPattern = "name=" + fieldName + " value=" + pattern;
		int searchPatternLength = searchPattern.length();
		int searchPatternIndex = input.indexOf(searchPattern);
		String valueStringWithLeftOvers = input.substring(searchPatternIndex + searchPatternLength);

		int valueStartIndex = searchPatternIndex + searchPatternLength;
		int valueEndIndex = valueStartIndex + valueStringWithLeftOvers.indexOf(pattern);
		String value = input.substring(valueStartIndex, valueEndIndex);

		return value;
	}

}
