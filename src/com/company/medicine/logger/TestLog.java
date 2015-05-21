package com.company.medicine.logger;

import java.io.IOException;
import java.util.logging.Logger;

public class TestLog {

	private static final Logger LOGGER = Logger.getLogger(TestLog.class
			.getName());

	public void log() throws SecurityException, IOException {
		LOGGER.info("Info log");
		LOGGER.fine("Fine log");
		LOGGER.finer("Finer log");
		LOGGER.finest("Finest log");
		LOGGER.warning("Warning log");
		LOGGER.severe("Severe log");
	}

}
