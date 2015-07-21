package suncertify.db.table;

import static mockedcontent.TableHeaderContent.getShorts;
import static org.mockito.Mockito.*;
import static suncertify.db.table.SetUpHeaderMock.setUpMockedheader;

import java.util.List;

import mockedcontent.MockedUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import suncertify.db.datasource.*;

@RunWith(MockitoJUnitRunner.class)
public class TableWriterTest {

	/**
	 * 
	 */
	private static final int COMBINED_SIZE_OF_LAST_FOUR_COLUMNS = 104;

	/**
	 * 
	 */
	private static final short MARK_RECORD_AS_DELETED_IN_HEX = (short)-32768;

	/**
	 * 
	 */
	private static final String MARK_RECORD_AS_NOT_DELETED = "00";

	/**
	 * 
	 */
	private static final String MARK_RECORD_AS_DELETED = "8000";

	/**
	 * 
	 */
	private static final int NAME_COLUMN_SIZE = 32;

	/**
	 * 
	 */
	private static final int LOCATION_COLUMN_SIZE = 64;

	/**
	 * 
	 */
	private static final int SIZE_COLUMN_SIZE = 8;

	/**
	 * 
	 */
	private static final int HOURLY_RATE_COLUMN_SIZE = 6;

	/**
	 * 
	 */
	private static final int SECOND_ROW_POSITION = 1;

	/**
	 * 
	 */
	private static final int FIRST_ROW_STARTING_POSITION = 20;

	/**
	 * 
	 */
	private static final int ROW_SIZE = 112;

	private TableWriter tableWriter;

	@Mock
	private DataSourceReader reader;

	@Mock
	private DataSourceFactory factory;

	@Mock
	private DataSourceWriter writer;

	private short deleted = 00;
	private String rate = "$10.20";
	private String name = "jack";
	private String location = "my location";
	private String size = "76";

	@Before
	public void setUp() throws DataSourceException {
		setUpMockedheader(reader);
		List<Short> mockedShorts = getShorts();
		for (int i = 0; i < 4; i++)
			mockedShorts.add((short) 0);

		MockedUtil.mockShorts(reader, mockedShorts);
		
		when(factory.getDataSourceReader()).thenReturn(reader);
		when(factory.getDataSourceWritter()).thenReturn(writer);

		tableWriter = new TableWriter(factory);
		tableWriter.open();
	}

	@Test
	public void verifyWriting() throws DataSourceException {
		tableWriter.writeNextColumn(MARK_RECORD_AS_NOT_DELETED);
		tableWriter.writeNextColumn(rate);
		tableWriter.writeNextColumn(name);
		tableWriter.writeNextColumn(location);
		tableWriter.writeNextColumn(size);
		
		verify(writer, times(1)).writeShort(deleted);
		verify(writer, times(1)).writeString(rate, HOURLY_RATE_COLUMN_SIZE);
		verify(writer, times(1)).writeString(name, NAME_COLUMN_SIZE);
		verify(writer, times(1)).writeString(location, LOCATION_COLUMN_SIZE);
		verify(writer, times(1)).writeString(size, SIZE_COLUMN_SIZE);
		verify(writer, times(1)).open();
		verifyNoMoreInteractions(writer);
	}
	
	@Test
	public void verifyWritingDeletedRow() throws DataSourceException {
		tableWriter.writeNextColumn(MARK_RECORD_AS_DELETED);
		verify(writer, times(1)).writeShort(MARK_RECORD_AS_DELETED_IN_HEX);
		verify(writer, times(1)).open();
		verifyNoMoreInteractions(writer);
	}
	
	@Test
	public void shouldWriteFirstTwoColumnOfSecondRow() throws DataSourceException {
		tableWriter.moveToRow(SECOND_ROW_POSITION);
		tableWriter.writeNextColumn(MARK_RECORD_AS_NOT_DELETED);
		tableWriter.writeNextColumn(rate);
		
		verify(writer, times(1)).writeShort(deleted);
		verify(writer, times(1)).writeString(rate, HOURLY_RATE_COLUMN_SIZE);
		verify(writer, times(1)).moveForward(ROW_SIZE+FIRST_ROW_STARTING_POSITION);
		verify(writer, times(1)).open();
		verifyNoMoreInteractions(writer);
	}
	
	@Test
	public void shouldWriteFirstTwoColumnOfThreeRows() throws DataSourceException {
		tableWriter.moveToRow(0);
		tableWriter.writeNextColumn(MARK_RECORD_AS_NOT_DELETED);
		tableWriter.writeNextColumn(rate);
		
		tableWriter.moveToRow(SECOND_ROW_POSITION);
		tableWriter.writeNextColumn(MARK_RECORD_AS_NOT_DELETED);
		tableWriter.writeNextColumn(rate);
		
		tableWriter.moveToRow(2);
		tableWriter.writeNextColumn(MARK_RECORD_AS_NOT_DELETED);
		tableWriter.writeNextColumn(rate);
		
		verify(writer, times(3)).writeShort(deleted);
		verify(writer, times(3)).writeString(rate, HOURLY_RATE_COLUMN_SIZE);
		
		verify(writer, times(1)).moveForward(FIRST_ROW_STARTING_POSITION);	
		verify(writer, times(2)).moveForward(COMBINED_SIZE_OF_LAST_FOUR_COLUMNS);
		
		verify(writer, times(1)).open();
		verifyNoMoreInteractions(writer);
	}
	
	@Test
	public void shouldNotMove() throws DataSourceException {
		tableWriter.moveToRow(0);
		tableWriter.moveToRow(0);
		
		tableWriter.writeNextColumn(MARK_RECORD_AS_NOT_DELETED);
		
		verify(writer, times(1)).moveForward(FIRST_ROW_STARTING_POSITION);	
		verify(writer, times(1)).moveForward(0);
		verify(writer, times(1)).writeShort(deleted);	
		
		verify(writer, times(1)).open();
		verifyNoMoreInteractions(writer);
	}
	
	@Test
	public void shouldClose() throws DataSourceException {
		tableWriter.close();
		verify(writer, times(1)).open();
		verify(writer, times(1)).close();
		verifyNoMoreInteractions(writer);
	}
}
