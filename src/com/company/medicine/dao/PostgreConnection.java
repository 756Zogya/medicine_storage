package com.company.medicine.dao;

import static com.company.medicine.config.ConfigurationConstant.DB_ADDRESS;
import static com.company.medicine.config.ConfigurationConstant.DB_NAME;
import static com.company.medicine.config.ConfigurationConstant.DB_PASSWORD;
import static com.company.medicine.config.ConfigurationConstant.DB_PORT;
import static com.company.medicine.config.ConfigurationConstant.DB_USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.company.medicine.config.Configuration;
import com.company.medicine.config.ConfigurationFactory;

public class PostgreConnection implements ConnectionIf {

	private static final Logger LOGGER = Logger
			.getLogger(PostgreConnection.class.getName());

	private static final Configuration config = ConfigurationFactory
			.getConfiguration();

	private static final String DRIVER = "org.postgresql.Driver";
	private static final String DB_PREFIX = "jdbc:postgresql://";

	public PostgreConnection() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			String errorMessage = "Unable to find driver!";
			LOGGER.severe(errorMessage);
			throw new MedicineDaoException(errorMessage);
		}
	}

	@Override
	public Connection getConnection() {

		LOGGER.info("Connecting to database...");
		Connection connection = null;

		try {

			String url = DB_PREFIX
					+ config.getProperties().getProperty(DB_ADDRESS) + ":"
					+ config.getProperties().getProperty(DB_PORT) + "/"
					+ config.getProperties().getProperty(DB_NAME);
			String user = config.getProperties().getProperty(DB_USER);
			String password = config.getProperties().getProperty(DB_PASSWORD);
			connection = DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			String errorMessage = "Unable to connect to database!";
			LOGGER.severe(errorMessage);
			throw new MedicineDaoException(errorMessage, e.getCause());
		}

		if (connection != null) {
			LOGGER.info("Connected!");
		} else {
			LOGGER.severe("FAIL");
		}
		return connection;
	}

}
