package integration.singleclient;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import integration.helper.TestDataUtil;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import suncertify.db.record.Record;
import suncertify.db.record.RecordNotFoundException;
import suncertify.db.record.RecordReader;
import datasource.DataSourceException;

public class DeleteTest extends BaseTest {

	@BeforeClass
	public static void backupFile() throws IOException {
		TestDataUtil.backupFile("delete");
	}

	@Test
	public void shouldDeleteRecord() throws SecurityException, RecordNotFoundException, DataSourceException {
		long lockCookie = db.lock(3);
		db.delete(3, lockCookie);
		Record record = verifyRecordIsDeleted(3);
		assertTrue(record.isDeleted());
	}

	@Test(expected = RecordNotFoundException.class)
	public void shouldNotbeAbleToReadRecordAfterIthasBeenDeleted() throws SecurityException, RecordNotFoundException {
		long lockCookie = db.lock(0);
		db.delete(0, lockCookie);
		db.read(0);
	}

	@Test
	public void shouldbeAbleToReadOtherRecordAfterOneRecordhasBeenDeleted() throws SecurityException, RecordNotFoundException, DataSourceException {
		long lockCookie = db.lock(2);
		db.delete(2, lockCookie);
		Record record1 = verifyRecordIsDeleted(1);
		Record record3 = verifyRecordIsDeleted(3);
		assertFalse(record3.isDeleted());
		assertFalse(record1.isDeleted());
	}

	@Test(expected = SecurityException.class)
	public void shouldNotDeleteRowIfNotLocked() throws SecurityException, RecordNotFoundException {
		db.delete(4, 0);
	}

	@Test(expected = RecordNotFoundException.class)
	public void shouldNotDeleteRowIfDoesNotExistLow() throws SecurityException, RecordNotFoundException {
		db.delete(-1, 0);
	}

	@Test(expected = RecordNotFoundException.class)
	public void shouldNotDeleteRowIfDoesNotExistHigh() throws SecurityException, RecordNotFoundException {
		db.delete(28, 0);
	}

	@AfterClass
	public static void restorFile() throws IOException {
		TestDataUtil.restorFile("delete");
	}

	private Record verifyRecordIsDeleted(int i) throws DataSourceException {
		RecordReader reader = new RecordReader(factory);
		reader.open();
		Record record = reader.readRecord(i);
		reader.close();
		return record;
	}

	@Override
	protected String getFileName() {
		return "delete";
	}
}
