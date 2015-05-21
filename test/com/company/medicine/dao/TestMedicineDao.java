package com.company.medicine.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.company.medicine.dao.data.Medicine;

public class TestMedicineDao {
	private final MedicineDao medicineDao = new MedicineDao();

	private static final String MEDICINE_NAME = "Algopirin";

	@Before
	public void setUp() {
		medicineDao.deleteAllMedicine();
	}

	@After
	public void tearDown() {
		medicineDao.deleteAllMedicine();
	}

	@Test
	public void testSaveNewMedicine() {
		Medicine medicine = new Medicine();
		medicine.setName(MEDICINE_NAME);

		medicineDao.saveMedicine(medicine);

		List<Medicine> medicines = medicineDao.getMedicines();
		assertEquals(1, medicines.size());
		Medicine returnMedicine = medicineDao.getMedicine(1);
		Assert.assertNotNull(returnMedicine);
		Assert.assertEquals(MEDICINE_NAME, medicine.getName());
	}

}
