package integration.singleclient;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import suncertify.db.DB;
import suncertify.db.Data;
import suncertify.db.record.RecordNotFoundException;
import datasource.DataSourceFactory;
import filesource.FileStreamFactory;

public class UpdateTest {

	private DB db;
	private DataSourceFactory factory;

	@Before
	public void setup() {
		factory = new FileStreamFactory();
		db = new Data(factory);
	}

	@Test
	public void updateFirstRecord() throws RecordNotFoundException {
		String[] expectedRow = { "Dogs With Tools", "Smallville", "Roofing", "7", "$35.00", "1234" };

		String[] rowBeforeUpdated = db.read(0);
		rowBeforeUpdated[5] = "1234";
		// char isdeleted = 0x8000;
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
		String[] row = db.read(2);
		row[5] = "1212";
		db.lock(2);
		db.update(2, row, 0);
	}

	@Test(expected = RecordNotFoundException.class)
	public void shouldthrowRecordNotFound() throws SecurityException, RecordNotFoundException {
		db.update(-1, new String[] {}, 0);
	}

	private void assertRowsEqual(String[] expectedRowArray, String[] actualRowArray) {
		List<String> expectedRowList = Arrays.<String> asList(expectedRowArray);
		List<String> actualRowList = Arrays.<String> asList(actualRowArray);
		assertEquals(expectedRowList, actualRowList);
	}
}
