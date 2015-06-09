package suncertify.db.table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static suncertify.db.table.SetUpHeaderMock.setUpMockedheader;

import java.util.List;

import mockedcontent.TableRowsContent;

import org.junit.Before;
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
		verify(reader, times(1)).moveForwardBy(0);
		verify(reader, times(1)).moveForwardBy(112);
		verify(reader, times(1)).moveForwardBy(2128);
	}

	@Test
	public void shouldReadFirstRow() throws DataSourceException {
		tableReader.moveToRow(0);
		assertEquals("0", tableReader.readNextColumn());
		assertEquals("$10.20", tableReader.readNextColumn());
		assertEquals("jack", tableReader.readNextColumn());
		assertEquals("my location", tableReader.readNextColumn());
		assertEquals("76", tableReader.readNextColumn());
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
	public void shouldHaveNextRow() throws DataSourceException {
		when(reader.available()).thenReturn(112);
		assertTrue(tableReader.hasRow(0));
	}

	@Test
	public void shouldNotHaveNextRow() throws DataSourceException {
		Mockito.when(reader.available()).thenReturn(111);
		assertFalse(tableReader.hasRow(0));
	}

	@Test
	public void shouldHaveRow() throws DataSourceException {
		Mockito.when(reader.available()).thenReturn(448);
		assertTrue(tableReader.hasRow(3));
	}

	@Test
	public void shouldHaveRowAfterMoveRow() throws DataSourceException {
		Mockito.when(reader.available()).thenReturn(336);
		tableReader.moveToRow(1);
		assertTrue(tableReader.hasRow(3));
	}

	@Test
	public void shouldHaveRowAfterMoveColumn() throws DataSourceException {
		Mockito.when(reader.available()).thenReturn(446);
		tableReader.readNextColumn();
		assertTrue(tableReader.hasRow(3));
	}

	@Test
	public void shouldHaveRowThatIsReading() throws DataSourceException {
		Mockito.when(reader.available()).thenReturn(336);
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
		assertEquals("0", tableReader.readNextColumn());
	}

	private String readThirdColumn() throws DataSourceException {
		String value = "";
		for (int i = 0; i < 3; i++)
			value = tableReader.readNextColumn();
		return value;
	}

	private void setupmock(DataSourceReader reader) throws DataSourceException {
		List<String> rows = TableRowsContent.getRows();

		OngoingStubbing<String> stubRead2 = when(reader.readString(2));
		mockReturnedData(rows, stubRead2, 0);

		OngoingStubbing<String> stubRead6 = when(reader.readString(6));
		mockReturnedData(rows, stubRead6, 1);
		OngoingStubbing<String> stubRead32 = when(reader.readString(32));
		mockReturnedData(rows, stubRead32, 2);
		OngoingStubbing<String> stubRead64 = when(reader.readString(64));
		mockReturnedData(rows, stubRead64, 3);
		OngoingStubbing<String> stubRead8 = when(reader.readString(8));
		mockReturnedData(rows, stubRead8, 4);
	}

	private void mockReturnedData(List<String> rows, OngoingStubbing<String> ongoingStub, int startIndex) {
		for (int i = startIndex; i < rows.size(); i += 5) {
			// System.out.print(rows.get(i) + " ");
			ongoingStub = ongoingStub.thenReturn(rows.get(i));
		}
		// System.out.println();
	}

}
