package integration.singleclient;

import static org.junit.Assert.assertEquals;
import integration.helper.TestDataUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import suncertify.db.record.RecordNotFoundException;

public class ReadTest extends BaseTest {

	private static final String FILE_NAME = "read";

	@BeforeClass
	public static void backupFile() throws IOException {
		TestDataUtil.backupFile(FILE_NAME);
	}

	@Test
	public void readFirstRecord() throws RecordNotFoundException {
		String[] expectedRow1 = { "Dogs With Tools", "Smallville", "Roofing", "7", "$35.00", "" };
		String[] row = db.read(0);
		assertRowsEqual(expectedRow1, row);
	}

	@Test
	public void readLastRecord() throws RecordNotFoundException {
		String[] expectedRow1 = { "Swanders & Flaughn", "Lendmarch", "Heating, Painting, Plumbing", "8", "$85.00", "" };
		String[] row = db.read(27);
		assertRowsEqual(expectedRow1, row);
	}

	@Test
	public void readTwoRecordsInReverseOrder() throws RecordNotFoundException {
		String[] expectedRow3 = { "Fred & Nobby", "Whoville", "Plumbing, Heating", "7", "$100.00", "" };
		String[] expectedRow7 = { "Philharmonic Remodeling", "Metropolis", "Glass, Carpets", "4", "$60.00", "" };
		String[] expectedRow8 = { "Dogs With Tools", "Pleasantville", "Heating, Painting", "7", "$45.00", "" };
		String[] expectedRow17 = { "Fred & Nobby", "Bali Hai", "Air Conditioning, Glass", "6", "$80.00", "" };

		// testing the read order not just testing the reading of 4 rows at once
		String[] row8 = db.read(8);
		String[] row7 = db.read(7);
		String[] row17 = db.read(17);
		String[] row3 = db.read(3);

		assertRowsEqual(expectedRow3, row3);
		assertRowsEqual(expectedRow7, row7);
		assertRowsEqual(expectedRow8, row8);
		assertRowsEqual(expectedRow17, row17);
	}

	@Test(expected = RecordNotFoundException.class)
	public void readInvalidRowTooSmallNumber() throws RecordNotFoundException {
		db.read(-1);
	}

	@Test(expected = RecordNotFoundException.class)
	public void readInvalidRowTooBigNumber() throws RecordNotFoundException {
		db.read(28);
	}

	private void assertRowsEqual(String[] expectedRowArray, String[] actualRowArray) {
		List<String> expectedRowList = Arrays.<String> asList(expectedRowArray);
		List<String> actualRowList = Arrays.<String> asList(actualRowArray);
		assertEquals(expectedRowList, actualRowList);
	}

	@AfterClass
	public static void restorFile() throws IOException {
		TestDataUtil.restorFile(FILE_NAME);
	}

	@Override
	protected String getFileName() {
		return FILE_NAME;
	}
}
