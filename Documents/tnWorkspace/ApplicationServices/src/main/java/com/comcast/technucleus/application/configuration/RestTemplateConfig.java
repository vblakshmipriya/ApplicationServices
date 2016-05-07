package com.comcast.technucleus.application.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.security.oauth.common.signature.SharedConsumerSecret;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.comcast.technucleus.application.constants.PropertiesConstants;
import com.comcast.technucleus.application.properties.Properties;

@EnableWebMvc
@Configuration 
public class RestTemplateConfig extends WebMvcConfigurerAdapter  {
	
	@Autowired
	private Properties properties;
	
	
	@Bean
	public RestTemplate restTemplate() {
		
		return new RestTemplate();
	}
	
	@Bean
	public OAuthRestTemplate oAuthRestTemplate() {
		
		String consumerSecret = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.CSP_CODE_BIG_CONSUMER_SECRET_KEY);
		String consumerKey = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.CSP_CODE_BIG_CONSUMER_KEY);
		SharedConsumerSecret secret = new SharedConsumerSecretImpl(consumerSecret);
		BaseProtectedResourceDetails protectedResourceDetails =  new BaseProtectedResourceDetails();
		protectedResourceDetails.setConsumerKey(consumerKey);
		protectedResourceDetails.setSharedSecret(secret);
		
		return new OAuthRestTemplate(protectedResourceDetails);
	}
	
	@Bean
	public OAuthRestTemplate oAuthGsonRestTemplate() {
		
		String consumerSecret = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.CSP_CODE_BIG_CONSUMER_SECRET_KEY);
		String consumerKey = properties.getProp(PropertiesConstants.SERVICE_END_POINT_PROPERTIES + PropertiesConstants.CSP_CODE_BIG_CONSUMER_KEY);
		SharedConsumerSecret secret = new SharedConsumerSecretImpl(consumerSecret);
		BaseProtectedResourceDetails protectedResourceDetails =  new BaseProtectedResourceDetails();
		protectedResourceDetails.setConsumerKey(consumerKey);
		protectedResourceDetails.setSharedSecret(secret);
		OAuthRestTemplate oauthRestTemplate = new OAuthRestTemplate(protectedResourceDetails);
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		GsonHttpMessageConverter msgConverter = new GsonHttpMessageConverter();
		converters.add(msgConverter);
		oauthRestTemplate.setMessageConverters(converters);
		
		return oauthRestTemplate;
	}
	
	
} 