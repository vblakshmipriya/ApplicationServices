package com.comcast.technucleus.application.domain.data;

import com.google.gson.JsonObject;
/******************************************************************************************************
 * 
 * File Name  : ConfigurationDO.java
 * Decription : This class is Domain Object and have intent of storing configuration.
 *
 *
 *****************************************************************************************************/

public class ConfigurationDO implements IDomianData 
{
	private JsonObject configurationData = null;

	public JsonObject getConfigurationData() {
		return configurationData;
	}

	public void setConfigurationData(JsonObject configurationData) {
		this.configurationData = configurationData;
	}

}
