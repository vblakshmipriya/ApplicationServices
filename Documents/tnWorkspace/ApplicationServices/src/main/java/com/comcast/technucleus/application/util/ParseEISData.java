package com.comcast.technucleus.application.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.comcast.technucleus.application.model.UserProfile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class ParseEISData {

	/** The Constant log. */
	private final static Logger log = LoggerFactory.getLogger(ParseEISData.class.getName());

	/** The CSV file path */
	private String CSV_FILE_PATH = "/opt/xtools/eisflatfile/eisdata.txt";

	private final static String DELIMITER = ",";

	/**
	 * parseEISData aggregates all the data from file containing comma as
	 * delimiter and formats it before sending it to the invoking service.
	 * 
	 * @see List<String>
	 */
	public Set<String> parseEISData() {

		BufferedReader br = null;
		String line = "";

		// Convert Keys to upper Case for display purposes.
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();

		Set<String> fileFormats = new HashSet<String>();
		try {
			br = new BufferedReader(new FileReader(CSV_FILE_PATH));
			while ((line = br.readLine()) != null) {
				UserProfile fileFormat = new UserProfile();
				// use comma as separator
				String[] delimitedValues = line.split(DELIMITER);
				fileFormat.setName(delimitedValues[1]);
				fileFormat.set_id(delimitedValues[2]);
				fileFormat.setTechID(delimitedValues[5]);
				fileFormat.setFulfillmentCenter(delimitedValues[10]);
				fileFormat.setAddressLine1(delimitedValues[16]);
				fileFormat.setAddressLine2(delimitedValues[17]);
				fileFormat.setCity(delimitedValues[18]);
				fileFormat.setState(delimitedValues[19]);
				fileFormat.setPostalCode(delimitedValues[20]);
				fileFormat.setCountry(line.substring(line.lastIndexOf(DELIMITER) + 1));

				fileFormats.add(gson.toJson(fileFormat));
			}

		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException when trying to read the file : {}" + CSV_FILE_PATH, e);
		} catch (IOException e) {
			log.error("IOException when trying to read the file : {}" + CSV_FILE_PATH, e);
		} catch (Exception e) {
			log.error("Exception when trying to read the file : {}" + CSV_FILE_PATH, e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					log.error("IOException when trying to read the file : {}" + CSV_FILE_PATH, e);
				}
			}
		}

		return fileFormats;

	}

}
