package suncertify.db.table;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static suncertify.db.table.SetUpHeaderMock.setUpMockedheader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import datasource.DataSourceException;
import datasource.DataSourceFactory;
import datasource.DataSourceReader;
import datasource.DataSourceWriter;

@RunWith(MockitoJUnitRunner.class)
public class TableWriterTest {

	private TableWriter tableWriter;

	@Mock
	private DataSourceReader reader;

	@Mock
	private DataSourceFactory factory;

	@Mock
	private DataSourceWriter writer;

	private String deleted = "  ";
	private String rate = "$10.20";
	private String name = "jack";
	private String location = "my location";
	private String size = "76";

	@Before
	public void setUp() throws DataSourceException {
		setUpMockedheader(reader);
		when(factory.getDatoSourceReader()).thenReturn(reader);
		when(factory.getDatoSourceWritter()).thenReturn(writer);

		tableWriter = new TableWriter(factory);
		tableWriter.open();
	}

	@Test
	public void verifyWriting() throws DataSourceException {
		tableWriter.writeNextColumn(deleted);
		tableWriter.writeNextColumn(rate);
		tableWriter.writeNextColumn(name);
		tableWriter.writeNextColumn(location);
		tableWriter.writeNextColumn(size);
		verify(writer, times(1)).write(deleted, 2);
		verify(writer, times(1)).write(rate, 6);
		verify(writer, times(1)).write(name, 32);
		verify(writer, times(1)).write(location, 64);
		verify(writer, times(1)).write(size, 8);
	}
}
