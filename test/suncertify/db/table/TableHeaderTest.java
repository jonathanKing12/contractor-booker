package suncertify.db.table;

import static mockedcontent.TableHeaderContent.getInts;
import static mockedcontent.TableHeaderContent.getMoves;
import static mockedcontent.TableHeaderContent.getShorts;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
public class TableHeaderTest {

	private TableHeader header;
	@Mock
	private DataSourceReader reader;

	@Mock
	private DataSourceFactory factory;

	@Before
	public void setUp() throws DataSourceException {

		List<Short> mockedShorts = getShorts();

		MockedUtil.mockShorts(reader, mockedShorts);
		setUpMockedheader(reader);

		when(factory.getDataSourceReader()).thenReturn(reader);
		header = new TableHeader(factory);
		header.readTableHeader();

	}

	@Test
	public void verifyReadingFromDataSource() throws DataSourceException {
		verifyMoved();
		verify(reader, times(getInts().size())).readInt();
		verify(reader, times(getShorts().size())).readShort();
	}

	private void verifyMoved() throws DataSourceException {
		List<Integer> expectedMoves = getMoves();
		for (Integer expectedMove : expectedMoves)
			Mockito.verify(reader, times(1)).moveForwardBy(expectedMove);
	}

	@Test
	public void shouldGetRowStartingPosition() throws DataSourceException {
		assertEquals(20, header.getRowStartingPosition(0));
		assertEquals(132, header.getRowStartingPosition(1));
		assertEquals(2260, header.getRowStartingPosition(20));
	}

	@Test
	public void shouldGetRowSize() throws DataSourceException {
		assertEquals(112, header.getRowSize());
	}

	@Test
	public void shouldGetNumberOfColumns() throws DataSourceException {
		assertEquals(5, header.getNumberOfColumns());
	}

	@Test
	public void shouldGetColumnSize() throws DataSourceException {
		assertEquals(2, header.getColumnSize(0));
		assertEquals(64, header.getColumnSize(3));
		assertEquals(8, header.getColumnSize(4));
	}

}
