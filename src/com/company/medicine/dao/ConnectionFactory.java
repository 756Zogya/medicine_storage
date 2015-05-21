package com.company.medicine.dao;

import java.sql.Connection;
import java.util.logging.Logger;

public class ConnectionFactory {

	private static final Logger LOGGER = Logger
			.getLogger(ConnectionFactory.class.getName());

	private static PostgreConnection postgreConnection = new PostgreConnection();

	private ConnectionFactory() {

	}

	public static Connection getPostgreConncection() {
		LOGGER.info("Providing new Postgre Connection ...");
		return postgreConnection.getConnection();
	}

}
