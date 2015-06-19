package mockedcontent;

import datasource.DataSourceFactory;
import datasource.DataSourceReader;
import datasource.DataSourceWriter;

public class MockedDataSourceFactory implements DataSourceFactory {

	private String fileName;

	public MockedDataSourceFactory(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public DataSourceReader getDatoSourceReader() {
		return new MockedDataSourcceReader(fileName);
	}

	@Override
	public DataSourceWriter getDatoSourceWritter() {
		return new MockedDataSourceWriter(fileName);
	}

}
