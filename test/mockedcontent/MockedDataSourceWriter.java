package mockedcontent;

import datasource.DataSourceException;
import datasource.DataSourceWriter;
import filesource.FileStreamWriter;

public class MockedDataSourceWriter implements DataSourceWriter {

	private FileStreamWriter fileStreamWriter;

	public MockedDataSourceWriter() {
		fileStreamWriter = new FileStreamWriter();
	}

	@Override
	public void open() throws DataSourceException {
		fileStreamWriter.open();
	}

	@Override
	public void writeString(String value, int size) throws DataSourceException {
		fileStreamWriter.writeString(value, size);
	}

	@Override
	public int getLength() throws DataSourceException {
		return fileStreamWriter.getLength();
	}

	@Override
	public void close() throws DataSourceException {
		fileStreamWriter.close();
	}

	@Override
	public void moveForward(int distance) throws DataSourceException {
		fileStreamWriter.moveForward(distance);
	}

	@Override
	public void writeShort(short value) throws DataSourceException {
		fileStreamWriter.writeShort(value);
	}
}
