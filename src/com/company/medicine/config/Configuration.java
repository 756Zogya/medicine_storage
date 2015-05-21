package com.company.medicine.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class Configuration {

	private static final Logger LOGGER = Logger.getLogger(Configuration.class
			.getName());

	private static final String USER_DIR = System.getProperty("user.dir");
	private static final String CONFIG_PROPERTIES = USER_DIR
			+ "/conf/config.properties";
	private final Properties properties = new Properties();

	public Configuration() {
		InputStream is = null;
		try {
			is = new FileInputStream(CONFIG_PROPERTIES);
		} catch (FileNotFoundException e) {
			LOGGER.info("Unable to open " + CONFIG_PROPERTIES);
		}
		if (is != null) {
			try {
				properties.load(is);
			} catch (IOException e) {
				LOGGER.info("Unable to load properties");
			}
		}
	}

	public Properties getProperties() {
		return properties;
	}

}
