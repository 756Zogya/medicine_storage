package com.company.medicine.config;

public class ConfigurationFactory {

	private static Configuration configuration;

	public static Configuration getConfiguration() {
		if (configuration == null) {
			configuration = new Configuration();
		}
		return configuration;
	}

	public static void setConfiguration(Configuration configuration) {
		ConfigurationFactory.configuration = configuration;
	}

}
