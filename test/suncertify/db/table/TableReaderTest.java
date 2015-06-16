package suncertify.db.table;

import static mockedcontent.TableHeaderContent.getShorts;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

import datasource.DataSourceException;
import datasource.DataSourceFactory;
import datasource.DataSourceReader;

@RunWith(MockitoJUnitRunner.class)
public class TableReaderTest {

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

	@Ignore
	@Test
	public void verifyMovingRow() throws DataSourceException {
		tableReader.moveToRow(0);
		tableReader.moveToRow(1);
		tableReader.moveToRow(20);
		verify(reader, times(1)).moveForwardBy(2);
		verify(reader, times(1)).moveForwardBy(112);
		verify(reader, times(1)).moveForwardBy(2128);
	}

	@Ignore
	@Test
	public void shouldReadFirstRow() throws DataSourceException {
		tableReader.moveToRow(0);
		assertEquals("0000", tableReader.readNextColumn());
		assertEquals("jack", tableReader.readNextColumn());
		assertEquals("my location", tableReader.readNextColumn());
		assertEquals("76", tableReader.readNextColumn());
		assertEquals("$10.20", tableReader.readNextColumn());
	}

	@Ignore
	@Test
	public void shouldReadThirdColumn() throws DataSourceException {
		tableReader.moveToRow(0);
		assertEquals("jack", readSecondColumn());
		tableReader.moveToRow(1);
		assertEquals("mary", readSecondColumn());
		tableReader.moveToRow(2);
		assertEquals("david", readSecondColumn());
		tableReader.moveToRow(3);
		assertEquals("mary", readSecondColumn());
	}

	@Ignore
	@Test
	public void shouldHaveNextRow() throws DataSourceException {
		when(reader.available()).thenReturn(114);
		assertTrue(tableReader.hasRow(0));
	}

	@Ignore
	@Test
	public void shouldNotHaveNextRow() throws DataSourceException {
		when(reader.available()).thenReturn(111);
		assertFalse(tableReader.hasRow(0));
	}

	@Test
	public void shouldHaveRow() throws DataSourceException {
		Mockito.when(reader.available()).thenReturn(468);
		assertTrue(tableReader.hasRow(3));
	}

	@Test
	public void shouldHaveRowAfterMoveRow() throws DataSourceException {
		when(reader.available()).thenReturn(336);
		tableReader.moveToRow(1);
		assertTrue(tableReader.hasRow(3));
	}

	@Test
	public void shouldHaveRowAfterMoveColumn() throws DataSourceException {
		when(reader.available()).thenReturn(446);
		tableReader.readNextColumn();
		assertTrue(tableReader.hasRow(3));
	}

	@Test
	public void shouldHaveRowThatIsReading() throws DataSourceException {
		when(reader.available()).thenReturn(336);
		tableReader.moveToRow(1);
		assertTrue(tableReader.hasRow(1));
	}

	@Test
	public void shouldHaveRowThatHasRead() throws DataSourceException {
		Mockito.when(reader.available()).thenReturn(110);
		tableReader.moveToRow(2);
		tableReader.readNextColumn();
		assertTrue(tableReader.hasRow(1));
	}

	@Test
	public void shouldNotHaveRow() throws DataSourceException, Exception {
		when(reader.available()).thenReturn(336);
		assertFalse(tableReader.hasRow(4));
	}

	@Test
	public void shouldNotHaveRowAfterMoveRow() throws DataSourceException, Exception {
		when(reader.available()).thenReturn(224);
		tableReader.moveToRow(1);
		assertFalse(tableReader.hasRow(4));
	}

	@Test
	public void shouldMoveToNextRow() throws DataSourceException {
		for (int i = 0; i < 5; i++) {
			System.out.println(tableReader.readNextColumn());
		}
		assertEquals("0000", tableReader.readNextColumn());
	}

	private String readSecondColumn() throws DataSourceException {
		String value = "";
		for (int i = 0; i < 2; i++)
			value = tableReader.readNextColumn();
		return value;
	}

	private void setupmock(DataSourceReader reader) throws DataSourceException {
		List<String> rows = TableRowsContent.getRows();

		OngoingStubbing<String> stubRead6 = when(reader.readString(6));
		mockReturnedData(rows, stubRead6, 0);
		OngoingStubbing<String> stubRead32 = when(reader.readString(32));
		mockReturnedData(rows, stubRead32, 1);
		OngoingStubbing<String> stubRead64 = when(reader.readString(64));
		mockReturnedData(rows, stubRead64, 2);
		OngoingStubbing<String> stubRead8 = when(reader.readString(8));
		mockReturnedData(rows, stubRead8, 3);
	}

	private void mockReturnedData(List<String> rows, OngoingStubbing<String> ongoingStub, int startIndex) {
		for (int i = startIndex; i < rows.size(); i += 4) {
			System.out.print(rows.get(i) + " ");
			ongoingStub = ongoingStub.thenReturn(rows.get(i));
		}
		System.out.println();
	}

}
