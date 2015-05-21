package com.company.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.company.medicine.dao.data.Medicine;

public class MedicineDao {

	private final String SQL_MAX_ID = "select MAX(id) as maxId from medicines";
	private final String SQL_INSERT_MEDICINE = "insert into medicines values (?, ?)";
	private final String SQL_UPDATE_MEDICINE = "update medicines set name = ? where id = ?";
	private final String SQL_SELECT_MEDICINE = "select * from medicines where id = ?";
	private final String SQL_SELECT_MEDICINES = "select * from medicines";
	private final String SQL_DELETE_MEDICINE = "delete from medicines where id = ?";
	private final String SQL_DELETE_MEDICINES = "delete from medicines";

	private static final Logger LOGGER = Logger.getLogger(TestMedicineDao.class
			.getName());

	public int saveMedicine(Medicine medicine) throws MedicineDaoException {
		LOGGER.info("Inserting medicine [" + medicine.getName() + "] ...");

		Connection connection = null;
		PreparedStatement psSelect = null;
		PreparedStatement psInsert = null;
		ResultSet rs = null;
		int rowCount = 0;
		try {
			connection = ConnectionFactory.getPostgreConncection();
			connection.setAutoCommit(false);

			psSelect = connection.prepareStatement(SQL_MAX_ID);
			rs = psSelect.executeQuery();

			int id = 0;
			while (rs.next()) {
				id = rs.getInt("maxId");
				break;
			}

			psInsert = connection.prepareStatement(SQL_INSERT_MEDICINE);
			psInsert.setInt(1, ++id);
			psInsert.setString(2, medicine.getName());

			rowCount = psInsert.executeUpdate();
			connection.commit();
			LOGGER.info(rowCount + " rows are inserted!");

		} catch (SQLException e) {
			String errorMessage = "Unable to insert medicine ["
					+ medicine.getName() + "]!";
			LOGGER.severe(errorMessage);
			throw new MedicineDaoException(errorMessage, e.getCause());
		} finally {
			closeResultSet(rs);
			closePreparedStatement(psSelect);
			closePreparedStatement(psInsert);
			closeConnection(connection);
		}
		return rowCount;
	}

	public int updateMedicine(Medicine medicine) throws MedicineDaoException {
		LOGGER.info("Updating medicine [" + medicine.getName() + "], id ["
				+ medicine.getId() + "] ...");

		Connection connection = null;
		PreparedStatement psUpdate = null;
		int rowCount = 0;
		try {
			connection = ConnectionFactory.getPostgreConncection();
			connection.setAutoCommit(false);

			psUpdate = connection.prepareStatement(SQL_UPDATE_MEDICINE);
			psUpdate.setString(1, medicine.getName());
			psUpdate.setInt(2, medicine.getId());

			rowCount = psUpdate.executeUpdate();
			connection.commit();
			LOGGER.info(rowCount + " rows are updated!");

		} catch (SQLException e) {
			LOGGER.severe("Unable to update medicine [" + medicine.getName()
					+ ", id [" + medicine.getId() + "]!");
			String errorMessage = "Unable to insert medicine ["
					+ medicine.getName() + "]!";
			LOGGER.severe(errorMessage);
			throw new MedicineDaoException(errorMessage, e.getCause());
		} finally {
			closePreparedStatement(psUpdate);
			closeConnection(connection);
		}
		return rowCount;
	}

	public Medicine getMedicine(int id) throws MedicineDaoException {
		LOGGER.info("Querying medicine for id [" + id + "] ...");

		Connection connection = null;
		PreparedStatement psSelect = null;
		ResultSet rs = null;
		Medicine medicine = null;
		try {
			connection = ConnectionFactory.getPostgreConncection();

			psSelect = connection.prepareStatement(SQL_SELECT_MEDICINE);
			psSelect.setInt(1, id);
			rs = psSelect.executeQuery();

			while (rs.next()) {
				medicine = new Medicine();
				medicine.setId(rs.getInt("id"));
				medicine.setName(rs.getString("name"));
				LOGGER.info("Medicine [" + medicine.getName() + "] is selected");
				break;
			}

		} catch (SQLException e) {
			LOGGER.severe("Unable to query medicine for id [" + id + "]!");
			String errorMessage = "Unable to insert medicine ["
					+ medicine.getName() + "]!";
			LOGGER.severe(errorMessage);
			throw new MedicineDaoException(errorMessage, e.getCause());
		} finally {
			closeResultSet(rs);
			closePreparedStatement(psSelect);
			closeConnection(connection);
		}
		return medicine;
	}

	public List<Medicine> getMedicines() throws MedicineDaoException {
		LOGGER.info("Querying medicines ...");

		Connection connection = null;
		PreparedStatement psSelect = null;
		ResultSet rs = null;
		List<Medicine> medicines = new ArrayList<Medicine>();
		try {
			connection = ConnectionFactory.getPostgreConncection();

			psSelect = connection.prepareStatement(SQL_SELECT_MEDICINES);
			rs = psSelect.executeQuery();

			while (rs.next()) {
				Medicine medicine = new Medicine();
				medicine.setId(rs.getInt("id"));
				medicine.setName(rs.getString("name"));
				medicines.add(medicine);
			}

			LOGGER.info("Medicines [" + medicines.toString() + "] are selected");
		} catch (SQLException e) {
			String errorMessage = "Unable to query medicines!";
			LOGGER.severe(errorMessage);
			throw new MedicineDaoException(errorMessage, e.getCause());
		} finally {
			closeResultSet(rs);
			closePreparedStatement(psSelect);
			closeConnection(connection);
		}
		return medicines;
	}

	public int deleteMedicine(int id) throws MedicineDaoException {
		LOGGER.info("Removing medicine for id [" + id + "] ...");

		Connection connection = null;
		PreparedStatement psDelete = null;
		int rowCount = 0;
		try {
			connection = ConnectionFactory.getPostgreConncection();
			connection.setAutoCommit(false);

			psDelete = connection.prepareStatement(SQL_DELETE_MEDICINE);
			psDelete.setInt(1, id);
			rowCount = psDelete.executeUpdate();

			LOGGER.info(rowCount + " medicines are removed");
			connection.commit();
		} catch (SQLException e) {
			String errorMessage = "Unable to delete medicine for id [" + id
					+ "]!";
			LOGGER.severe(errorMessage);
			throw new MedicineDaoException(errorMessage, e.getCause());
		} finally {
			closePreparedStatement(psDelete);
			closeConnection(connection);
		}
		return rowCount;
	}

	public int deleteAllMedicine() throws MedicineDaoException {
		LOGGER.info("Removing all medicines ...");

		Connection connection = null;
		PreparedStatement psDelete = null;
		int rowCount = 0;
		try {
			connection = ConnectionFactory.getPostgreConncection();
			connection.setAutoCommit(false);

			psDelete = connection.prepareStatement(SQL_DELETE_MEDICINES);
			rowCount = psDelete.executeUpdate();

			LOGGER.info(rowCount + " medicines are removed");
			connection.commit();
		} catch (SQLException e) {
			String errorMessage = "Unable to delete medicines!";
			LOGGER.severe(errorMessage);
			throw new MedicineDaoException(errorMessage, e.getCause());
		} finally {
			closePreparedStatement(psDelete);
			closeConnection(connection);
		}
		return rowCount;
	}

	public int getNextId() throws MedicineDaoException {
		LOGGER.info("Querying next id from medicines ...");

		Connection connection = null;
		PreparedStatement psSelect = null;
		ResultSet rs = null;
		int id = 0;
		try {
			connection = ConnectionFactory.getPostgreConncection();

			psSelect = connection.prepareStatement(SQL_MAX_ID);
			rs = psSelect.executeQuery();

			while (rs.next()) {
				id = rs.getInt("maxId");
				break;
			}

			id++;
			LOGGER.info("next Id is [" + id + "]!");

		} catch (SQLException e) {
			String errorMessage = "Unable to query next id for medicines!";
			LOGGER.severe(errorMessage);
			throw new MedicineDaoException(errorMessage, e.getCause());
		} finally {
			closeResultSet(rs);
			closePreparedStatement(psSelect);
			closeConnection(connection);
		}
		return id;
	}

	private void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.severe("Unable to close connection!");
			}
		}
	}

	private void closePreparedStatement(PreparedStatement psSelect) {
		if (psSelect != null) {
			try {
				psSelect.close();
			} catch (SQLException e) {
				LOGGER.severe("Unable to close prepareStatement!");
			}
		}
	}

	private void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				LOGGER.severe("Unable to close resultSet!");
			}
		}
	}
}
