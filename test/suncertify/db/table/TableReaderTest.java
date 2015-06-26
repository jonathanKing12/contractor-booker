package suncertify.db.table;

import static mockedcontent.TableHeaderContent.getShorts;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static suncertify.db.table.SetUpHeaderMock.setUpMockedheader;

import java.util.List;

import mockedcontent.MockedUtil;
import mockedcontent.TableRowsContent;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import suncertify.db.datasource.DataSourceException;
import suncertify.db.datasource.DataSourceFactory;
import suncertify.db.datasource.DataSourceReader;

@RunWith(MockitoJUnitRunner.class)
public class TableReaderTest {

	private static final int FIFTH_ROW_POSITION = 4;
	private static final int THIRD_ROW_POSITION = 2;
	private static final int SECOND_ROW_POSITION = 1;
	private static final int FORTH_ROW_POSITION = 3;
	private static final int FIRST_ROW_POSITION = 0;
	private static final int ROW_STARTING_POSITION = 20;
	private static final int SIZE_OF_ROW = 112;
	private static final int SIZE_OF_DELETED_FLAG = 2;

	private TableReader tableReader;

	@Mock
	private DataSourceReader reader;

	@Mock
	private DataSourceFactory factory;

	@Before
	public void setUp() throws DataSourceException, Exception {
		setUpMockedheader(reader);
		
		List<Short> mockedShorts = getShorts();
		for (int i = 0; i < 4; i++)
			mockedShorts.add((short) 0);

		MockedUtil.mockShorts(reader, mockedShorts);
		
		setupmock(reader);
		
		when(factory.getDatoSourceReader()).thenReturn(reader);
		tableReader = new TableReader(factory);
		tableReader.open();
	}

	
	 @Test
	public void verifyMovingRow() throws DataSourceException {
		tableReader.moveToRow(0);
		tableReader.moveToRow(1);
		tableReader.moveToRow(20);
		verify(reader, times(1)).moveForwardBy(SIZE_OF_DELETED_FLAG);
		verify(reader, times(1)).moveForwardBy(SIZE_OF_ROW);	
		verify(reader, times(1)).moveForwardBy(19*SIZE_OF_ROW);
	}

	
    @Test
	public void shouldReadFirstRow() throws DataSourceException {
		tableReader.moveToRow(0);
		assertEquals("0000", tableReader.readNextColumn());
		assertEquals("76", tableReader.readNextColumn());
		assertEquals("jack", tableReader.readNextColumn());
		assertEquals("my location", tableReader.readNextColumn());
		assertEquals("$10.20", tableReader.readNextColumn());
	}

	
	@Test
	public void shouldReadThirdColumn() throws DataSourceException {
		tableReader.moveToRow(0);
		assertEquals("jack", readThirdColumn());
		tableReader.moveToRow(1);
		assertEquals("mary", readThirdColumn());
		tableReader.moveToRow(2);
		assertEquals("david", readThirdColumn());
		tableReader.moveToRow(3);
		assertEquals("mary", readThirdColumn());
	}
	
	@Test
	public void shouldMoveToNextRow() throws DataSourceException {
		for (int i = 0; i < 5; i++) {
			tableReader.readNextColumn();
		}
		assertEquals("0000", tableReader.readNextColumn());
	}

	
	@Test
	public void shouldHaveNextRow() throws DataSourceException {
		when(reader.available()).thenReturn(SIZE_OF_ROW+ROW_STARTING_POSITION);
		assertTrue(tableReader.hasRow(FIRST_ROW_POSITION));
	}

	
	@Test
	public void shouldNotHaveNextRow() throws DataSourceException {
		when(reader.available()).thenReturn(SIZE_OF_ROW-1);
		assertFalse(tableReader.hasRow(FIRST_ROW_POSITION));
	}

	 @Test
	public void shouldHaveRow() throws DataSourceException {
		int size=(SIZE_OF_ROW*4)+ROW_STARTING_POSITION;
		when(reader.available()).thenReturn(size);
		assertTrue(tableReader.hasRow(FORTH_ROW_POSITION));
	}

	@Test
	public void shouldHaveRowAfterMoveRow() throws DataSourceException {
		int size=SIZE_OF_ROW*3;
		when(reader.available()).thenReturn(size);
		tableReader.moveToRow(SECOND_ROW_POSITION);
		assertTrue(tableReader.hasRow(FORTH_ROW_POSITION));
	}

	@Test
	public void shouldHaveRowAfterMoveColumn() throws DataSourceException {
		int size=(SIZE_OF_ROW*4)+ROW_STARTING_POSITION;
		when(reader.available()).thenReturn(size);
		tableReader.readNextColumn();
		assertTrue(tableReader.hasRow(FORTH_ROW_POSITION));
	}

   @Test
	public void shouldHaveRowThatIsReading() throws DataSourceException {
		when(reader.available()).thenReturn(SIZE_OF_ROW*3);
		tableReader.moveToRow(SECOND_ROW_POSITION);
		assertTrue(tableReader.hasRow(SECOND_ROW_POSITION));
	}

	@Test
	public void shouldHaveRowThatHasRead() throws DataSourceException {
		when(reader.available()).thenReturn(110);
		tableReader.moveToRow(THIRD_ROW_POSITION);
		tableReader.readNextColumn();
		assertTrue(tableReader.hasRow(SECOND_ROW_POSITION));
	}

	@Test
	public void shouldNotHaveRow() throws DataSourceException, Exception {
		when(reader.available()).thenReturn(SIZE_OF_ROW*3);
		assertFalse(tableReader.hasRow(FIFTH_ROW_POSITION));
	}

	@Test
	public void shouldNotHaveRowAfterMoveRow() throws DataSourceException, Exception {
		when(reader.available()).thenReturn(224);
		tableReader.moveToRow(SECOND_ROW_POSITION);
		assertFalse(tableReader.hasRow(FIFTH_ROW_POSITION));
	}
	
	@Test
	public void shouldClose() throws DataSourceException {
		tableReader.close();
		verify(reader, times(2)).open();
		verify(reader, times(2)).close();
	}

	private String readThirdColumn() throws DataSourceException {
		String value = "";
		for (int i = 0; i < 3; i++){
			value = tableReader.readNextColumn();
		}
		return value;
	}

	private void setupmock(DataSourceReader reader) throws DataSourceException {

		OngoingStubbing<String> size = when(reader.readString(6));
		mockReturnedData(TableRowsContent.getSizeColumn(), size);
		
		OngoingStubbing<String> name = when(reader.readString(32));
		mockReturnedData(TableRowsContent.getNameColumn(), name);
		
		OngoingStubbing<String> location = when(reader.readString(64));
		mockReturnedData(TableRowsContent.geLocationColumn(), location);
		
		OngoingStubbing<String> hourlyRate = when(reader.readString(8));
		mockReturnedData(TableRowsContent.getHourlyRateColumn(), hourlyRate);
	}

	private void mockReturnedData(List<String> rows, OngoingStubbing<String> ongoingStub) {
		for (int i = 0; i < rows.size(); i ++) {
			ongoingStub = ongoingStub.thenReturn(rows.get(i));
		}
	}

}
