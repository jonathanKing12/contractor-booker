package filesource;

import datasource.DataSourceFactory;
import datasource.DataSourceReader;
import datasource.DataSourceWriter;

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
