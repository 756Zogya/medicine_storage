package com.company.medicine.dao;

public class MedicineDaoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5811090306072920294L;

	public MedicineDaoException() {
		super();
	}

	public MedicineDaoException(String message) {
		super(message);
	}

	public MedicineDaoException(String message, Throwable cause) {
		super(message, cause);
	}
}
