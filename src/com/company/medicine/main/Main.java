package com.company.medicine.main;

import static com.company.medicine.config.ConfigurationConstant.DB_ADDRESS;

import java.util.List;

import com.company.medicine.config.Configuration;
import com.company.medicine.config.ConfigurationFactory;
import com.company.medicine.dao.ConnectionFactory;
import com.company.medicine.dao.MedicineDao;
import com.company.medicine.dao.data.Medicine;
import com.company.medicine.logger.CustomLogger;
import com.company.medicine.logger.TestLog;

public class Main {

	public static void main(String[] args) throws Exception {
		CustomLogger.setupLogger();

		new TestLog().log();

		Configuration configuration = ConfigurationFactory.getConfiguration();
		System.out
				.println(configuration.getProperties().getProperty("vitamin"));
		System.out.println(configuration.getProperties()
				.getProperty(DB_ADDRESS));
		System.out.println(configuration.getProperties().getProperty(
				"antibiotikum"));
		System.out.println(configuration.getProperties().getProperty(
				"antibiotikum"));

		ConnectionFactory.getPostgreConncection();
		ConnectionFactory.getPostgreConncection();

		MedicineDao dao = new MedicineDao();
		Medicine medicine = new Medicine();
		medicine.setName("Algopirin");
		dao.saveMedicine(medicine);
		List<Medicine> medicines = dao.getMedicines();
		Medicine medicine2 = dao.getMedicine(1);
		medicine.setId(1);
		medicine.setName("Aspirin");
		dao.updateMedicine(medicine);
		medicine.setName("Algopirin");
		dao.saveMedicine(medicine);
		medicine.setName("Xanax");
		dao.saveMedicine(medicine);
		dao.deleteMedicine(2);
		dao.deleteAllMedicine();
	}

}
