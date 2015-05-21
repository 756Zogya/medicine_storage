package com.company.medicine.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLogger {

	private static final String USER_DIR = System.getProperty("user.dir");

	public static void setupLogger() throws SecurityException, IOException {
		Logger logger = Logger.getLogger("com.company.medicine");

		for (Handler handler : logger.getHandlers()) {
			logger.removeHandler(handler);
		}

		createLogDir();

		logger.setLevel(Level.FINEST);
		FileHandler fileHandler = new FileHandler(USER_DIR + "/log/log.txt",
				true);
		fileHandler.setFormatter(new FileFormatter());
		logger.addHandler(fileHandler);

		// ConsoleHandler consoleHandler = new ConsoleHandler();
		// consoleHandler.setFormatter(new ConsoleFormatter());
		// consoleHandler.setLevel(Level.FINEST);
		// logger.addHandler(consoleHandler);

	}

	private static void createLogDir() {
		File theDir = new File(USER_DIR + "/log");

		if (!theDir.exists()) {
			theDir.mkdir();
		}
	}

}
