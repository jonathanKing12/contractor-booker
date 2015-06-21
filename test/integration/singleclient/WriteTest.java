package integration.singleclient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import integration.helper.TestDataUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import suncertify.db.record.Record;
import suncertify.db.record.RecordNotFoundException;
import suncertify.db.record.RecordReader;
import datasource.DataSourceException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WriteTest extends BaseTest {

	private static final String FILE_NAME = "write";

	@BeforeClass
	public static void backupFile() throws IOException {
		TestDataUtil.backupDbFile(FILE_NAME);
	}

	@Test
	public void shouldFirstWriteToEndOfFileTest() throws RecordNotFoundException {
		String[] createdRow = { "created name", "new", "1 speciality", "7", "$2.00", "123" };
		int recordNumber = db.create(createdRow);
		String[] readRow = db.read(28);
		assertEquals(28, recordNumber);
		assertRowsEqual(createdRow, readRow);
	}

	@Test
	public void shouldSecondReplaceDeletedRowTest() throws RecordNotFoundException {
		deleteRecord(3);

		String[] createdRow = { "created name", "new", "1 speciality", "7", "$2.00", "123" };
		int recordNumber = db.create(createdRow);
		String[] readRow = db.read(3);

		assertEquals(3, recordNumber);
		assertRowsEqual(createdRow, readRow);
	}

	@Test
	public void shouldThirdReplaceTheFirstDeletedRowTest() throws RecordNotFoundException, DataSourceException {
		deleteRecord(5);
		deleteRecord(27);

		String[] expectedRow = { "changed name", "changed location", "changed speciallity", "11", "$15.03", "" };
		int recordNumber = db.create(expectedRow);

		String[] actualRow = db.read(5);
		Record record27 = getRecord(27);

		assertEquals(5, recordNumber);
		assertTrue(record27.isDeleted());
		assertRowsEqual(expectedRow, actualRow);
	}

	@Test
	public void shouldForthWriteToEndOfFileTwiceTest() throws RecordNotFoundException {
		String[] expectedRow29 = { "29 row", "29 city", "29 speciality", "29", "$29.00", "" };
		String[] expectedRow30 = { "30 row", "30 city", "30 speciality", "30", "$30.00", "" };

		int recordNumber29 = db.create(expectedRow29);
		int recordNumber30 = db.create(expectedRow30);

		String[] actualRow29 = db.read(29);
		String[] actualRow30 = db.read(30);

		assertEquals(29, recordNumber29);
		assertEquals(30, recordNumber30);

		assertRowsEqual(expectedRow29, actualRow29);
		assertRowsEqual(expectedRow30, actualRow30);
	}

	@Test
	public void shouldSizthWriteToEndOfFileTwiceTest() throws RecordNotFoundException {
		String[] expectedRow17 = { "17 row", "17 city", "17 speciality", "17", "$17.00", "" };
		String[] expectedRow31 = { "31 row", "31 city", "31 speciality", "31", "$31.00", "" };

		deleteRecord(17);

		int recordNumber17 = db.create(expectedRow17);
		int recordNumber31 = db.create(expectedRow31);

		String[] actualRow17 = db.read(17);
		String[] actualRow31 = db.read(31);

		assertEquals(17, recordNumber17);
		assertEquals(31, recordNumber31);

		assertRowsEqual(expectedRow17, actualRow17);
		assertRowsEqual(expectedRow31, actualRow31);
	}

	private void deleteRecord(int recordNumber) throws RecordNotFoundException {
		long lockCookie = db.lock(recordNumber);
		db.delete(recordNumber, lockCookie);
	}

	private void assertRowsEqual(String[] expectedRowArray, String[] actualRowArray) {
		List<String> expectedRowList = Arrays.<String> asList(expectedRowArray);
		List<String> actualRowList = Arrays.<String> asList(actualRowArray);
		assertEquals(expectedRowList, actualRowList);
	}

	@AfterClass
	public static void restorFile() throws IOException {
		TestDataUtil.restorDbFile(FILE_NAME);
	}

	@Override
	protected String getFileName() {
		return FILE_NAME;
	}

	private Record getRecord(int recordNumber) throws DataSourceException {
		RecordReader reader = new RecordReader(factory);
		reader.open();
		Record record = reader.readRecord(recordNumber);
		reader.close();
		return record;
	}

}
