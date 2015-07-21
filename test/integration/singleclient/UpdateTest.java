package integration.singleclient;

import static org.junit.Assert.assertEquals;
import integration.helper.TestDataUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import suncertify.db.RecordNotFoundException;

public class UpdateTest extends BaseTest {

	private static final String FILE_NAME = "update";

	@BeforeClass
	public static void backupFile() throws IOException {
		TestDataUtil.backupDbFile(FILE_NAME);
	}

	@Test
	public void updateFirstRecord() throws RecordNotFoundException {
		String[] expectedRow = { "Dogs With Tools", "Smallville", "Roofing", "7", "$35.00", "1234" };

		String[] rowBeforeUpdated = db.read(0);
		rowBeforeUpdated[5] = "1234";
		long lockCookie = db.lock(0);
		db.update(0, rowBeforeUpdated, lockCookie);
		db.unlock(0, lockCookie);
		String[] rowAfterUpdated = db.read(0);
		assertRowsEqual(expectedRow, rowAfterUpdated);
	}

	@Test(expected = SecurityException.class)
	public void shouldNotUpdateRecordWhenRecordIsNotLocked() throws RecordNotFoundException {
		String[] row = db.read(1);
		row[5] = "8888";
		db.update(1, row, 0);
	}

	@Test(expected = SecurityException.class)
	public void shouldNotUpdateRecordWhenRecordIsNotLockedWithCookie() throws RecordNotFoundException {
		DbWithReserveLocks dbWithReserveLocks = (DbWithReserveLocks) db;
		String[] row = dbWithReserveLocks.read(2);
		row[5] = "1212";
		dbWithReserveLocks.lockRecord26();
		dbWithReserveLocks.update(26, row, 0);
	}

	@Test(expected = RecordNotFoundException.class)
	public void shouldthrowRecordNotFound() throws SecurityException, RecordNotFoundException {
		db.update(-1, new String[] {}, 0);
	}

	@AfterClass
	public static void restorFile() throws IOException {
		TestDataUtil.restorDbFile(FILE_NAME);
	}

	private void assertRowsEqual(String[] expectedRowArray, String[] actualRowArray) {
		List<String> expectedRowList = Arrays.<String> asList(expectedRowArray);
		List<String> actualRowList = Arrays.<String> asList(actualRowArray);
		assertEquals(expectedRowList, actualRowList);
	}

	@Override
	protected String getFileName() {
		return FILE_NAME;
	}
}
