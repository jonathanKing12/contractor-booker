package suncertify.db.table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import suncertify.db.datasource.DataSourceException;
import suncertify.db.datasource.DataSourceReader;

@RunWith(MockitoJUnitRunner.class)
public class DataCounterTest {

	private static final int ELEVENT_ROW_STARTING_POSITION = 110;

	private static final int ELEVENTH_ROW_INDEX = 11;

	private static final int SECONDD_ROW_STARTING_POSITION = 20;

	private static final int SECOND_ROW_INDEX = 2;

	private static final int AMOUNT_OF_DATA_READ = 50;

	private static final int NEGATIVE_ROW_INDEX = -1;

	private static final int DATA_SOURCE_SIZE = 100;

	private static final int FIRST_ROW_STARTING_POSITION = 0;

	private static final int FIRST_ROW_INDEX = 0;

	private TableDataCounter dataCounter;

	@Mock
	private TableHeader header;

	@Mock
	private DataSourceReader reader;

	@Before
	public void setUp() {
		dataCounter = new TableDataCounter(header, reader);
	}

	@Test
	public void shouldHaveFirstRow() throws DataSourceException {
		when(header.getRowStartingPosition(FIRST_ROW_INDEX)).thenReturn(FIRST_ROW_STARTING_POSITION);
		when(reader.available()).thenReturn(DATA_SOURCE_SIZE);
		assertTrue(dataCounter.doesRowExists(FIRST_ROW_INDEX));
	}

	@Test
	public void shouldHaveAlreadyReadRow() throws DataSourceException {
		when(header.getRowStartingPosition(SECOND_ROW_INDEX)).thenReturn(SECONDD_ROW_STARTING_POSITION);
		dataCounter.setAmountOfDataRead(AMOUNT_OF_DATA_READ);
		assertTrue(dataCounter.doesRowExists(SECOND_ROW_INDEX));
	}

	@Test
	public void shouldNotHaveNegativeRow() throws DataSourceException {
		assertFalse(dataCounter.doesRowExists(NEGATIVE_ROW_INDEX));
	}

	@Test
	public void shouldNotHaveRow() throws DataSourceException {
		when(header.getRowStartingPosition(ELEVENTH_ROW_INDEX)).thenReturn(ELEVENT_ROW_STARTING_POSITION);
		when(reader.available()).thenReturn(DATA_SOURCE_SIZE);
		assertFalse(dataCounter.doesRowExists(ELEVENTH_ROW_INDEX));
	}

	@Test
	public void test() throws DataSourceException {
		dataCounter.setAmountOfDataRead(DATA_SOURCE_SIZE);
		dataCounter.incrementBy(20);
		assertEquals(120, dataCounter.getSizeOfDataThatsBeenRead());
	}
}
