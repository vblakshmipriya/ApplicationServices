package com.comcast.technucleus.application.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.comcast.technucleus.application.util.ApplicationProperties;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
public class SpringMongoConfig {
	
	@Autowired
	ApplicationProperties properties;
	
	 public @Bean Mongo mongo() throws Exception {
		 
		 String userName = properties.getProperty("mongo.username");
		 String password = properties.getProperty("mongo.password");
		 String host = properties.getProperty("mongo.server");
		 
		 MongoCredential mongoCredential = MongoCredential.createCredential(userName, "admin",password.toCharArray());
		 return new MongoClient(new ServerAddress(host),Arrays.asList(mongoCredential));
	  }

	  public @Bean MongoOperations  mongoOps(String templateName) throws Exception {
	      return new MongoTemplate(mongo(),templateName);
	  }

	  public @Bean DB getDatabase(String databaseName) {
	        DB database = null;
	        try {
	        	database = mongo().getDB(databaseName);
	            return database;
	        } catch (Exception e) {
	            //log message
	        }
	        return database; 
	    }
}
