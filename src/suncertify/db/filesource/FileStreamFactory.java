package suncertify.db.filesource;

import suncertify.db.datasource.DataSourceFactory;
import suncertify.db.datasource.DataSourceReader;
import suncertify.db.datasource.DataSourceWriter;

public class FileStreamFactory implements DataSourceFactory {

	@Override
	public DataSourceReader getDatoSourceReader() {
		return new FileStreamReader();
	}

	@Override
	public DataSourceWriter getDatoSourceWritter() {
		return new FileStreamWriter();
	}
}
