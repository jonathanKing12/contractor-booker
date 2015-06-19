package integration.singleclient;

import static org.junit.Assert.assertEquals;
import integration.helper.TestDataUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FindTest extends BaseTest {

	private static final String FILE_NAME = "find";

	private String nameCriea;
	private String locationCritea;
	private String specialtiesCritea;
	private String sizeCritea;
	private String ownerCritea;

	private String hourlyRate;

	@BeforeClass
	public static void backupFile() throws IOException {
		TestDataUtil.backupFile(FILE_NAME);
	}

	@Before
	public void setUp() {
		super.setup();
		resetSearchCritea();
	}

	private void resetSearchCritea() {
		nameCriea = null;
		locationCritea = null;
		specialtiesCritea = null;
		sizeCritea = null;
		hourlyRate = null;
		ownerCritea = null;

	}

	@Test
	public void shouldFindAllRecordsTest() {
		Integer[] actualResults = findRows();
		Integer[] expectedResults = getAllRecordIds();
		assertRowsEqual(expectedResults, actualResults);
	}

	@Test
	public void shouldFindAllRecordsThatNameBeginWithDo() {
		nameCriea = "Do";
		Integer[] actualResults = findRows();
		Integer[] expectedResults = new Integer[] { 0, 4, 5, 8, 16, 25 };
		assertRowsEqual(expectedResults, actualResults);
	}

	@Test
	public void shouldFindAllRecordsThatLocationBeginWithDo() {
		locationCritea = "P";
		Integer[] actualResults = findRows();
		Integer[] expectedResults = new Integer[] { 8, 9, 21, 22 };
		assertRowsEqual(expectedResults, actualResults);
	}

	@Test
	public void shouldFindAllRecordsWhosSpecialitiesHasRoofing() {
		specialtiesCritea = "Roofing";
		Integer[] actualResults = findRows();
		Integer[] expectedResults = new Integer[] { 0, 1, 2, 6, 10, 11, 14, 16, 20, 21, 23, 25 };
		assertRowsEqual(expectedResults, actualResults);
	}

	@Test
	public void shouldFindAllRecordsWhosNameAndSpecialities() {
		nameCriea = "Do";
		specialtiesCritea = "Roofing";

		Integer[] actualResults = findRows();
		Integer[] expectedResults = new Integer[] { 0, 16, 25 };
		assertRowsEqual(expectedResults, actualResults);
	}

	@Test
	public void shouldNotMatchAllCritea() {
		nameCriea = "IdoNotExist";
		Integer[] actualResults = findRows();
		assertEquals(0, actualResults.length);
	}

	@Test
	public void shouldNotMatchSomeCritea() {
		nameCriea = "IdoNotExist";
		specialtiesCritea = "Roofing";

		Integer[] actualResults = findRows();
		assertEquals(0, actualResults.length);
	}

	@Test
	public void shouldFindSmallerSizes() {
		sizeCritea = "5";
		Integer[] actualResults = findRows();
		Integer[] expectedResults = new Integer[] { 2, 5, 7, 11, 12, 16, 19, 21, 23, 24, 25, 26 };
		assertRowsEqual(expectedResults, actualResults);
	}

	@Test
	public void shouldNotMatchSizeCritea() {
		sizeCritea = "-1";
		Integer[] actualResults = findRows();
		assertEquals(0, actualResults.length);
	}

	@Test
	public void shouldMatchHourlyRateCritea() {
		hourlyRate = "65";
		Integer[] actualResults = findRows();
		Integer[] expectedResults = new Integer[] { 0, 4, 6, 7, 8, 10, 12, 14, 16, 19, 21 };
		assertRowsEqual(expectedResults, actualResults);
	}

	@Test
	public void shouldMatchHourlyRateCritea2() {
		hourlyRate = "36.03";
		Integer[] actualResults = findRows();
		Integer[] expectedResults = new Integer[] { 0, 16 };
		assertRowsEqual(expectedResults, actualResults);
	}

	@Test
	public void shouldFindOwnerCritea() {
		ownerCritea = "12";
		Integer[] actualResults = findRows();
		Integer[] expectedResults = new Integer[] { 0, 1 };
		assertRowsEqual(expectedResults, actualResults);
	}

	@AfterClass
	public static void restorFile() throws IOException {
		TestDataUtil.restorFile(FILE_NAME);
	}

	@Override
	protected String getFileName() {
		return FILE_NAME;
	}

	private String[] createSearchCritea() {
		return new String[] { nameCriea, locationCritea, specialtiesCritea, sizeCritea, hourlyRate, ownerCritea };
	}

	private Integer[] findRows() {
		String[] searchCritea = createSearchCritea();
		int[] recordIds = db.find(searchCritea);
		return convertToIntegerArray(recordIds);
	}

	private Integer[] convertToIntegerArray(int[] intRecordids) {
		Integer[] integerRecordids = new Integer[intRecordids.length];
		for (int i = 0; i < intRecordids.length; i++) {
			integerRecordids[i] = intRecordids[i];
		}
		return integerRecordids;
	}

	private Integer[] getAllRecordIds() {
		Integer[] recrodIds = new Integer[28];
		for (int i = 0; i < 28; i++) {
			recrodIds[i] = i;
		}
		return recrodIds;
	}

	private void assertRowsEqual(Integer[] expectedRowArray, Integer[] actualRowArray) {
		List<Integer> expectedRowList = Arrays.<Integer> asList(expectedRowArray);
		List<Integer> actualRowList = Arrays.<Integer> asList(actualRowArray);
		assertEquals(expectedRowList, actualRowList);
	}
}
