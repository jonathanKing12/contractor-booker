package mockedcontent;

import suncertify.db.datasource.DataSourceFactory;
import suncertify.db.datasource.DataSourceReader;
import suncertify.db.datasource.DataSourceWriter;

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
		return new MockedDataSourceWriter();
	}

}
