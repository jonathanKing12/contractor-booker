package mockedcontent;

import static java.lang.System.arraycopy;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import datasource.DataSourceException;
import datasource.DataSourceWriter;

public class MockedDataSourceWriter implements DataSourceWriter {

	private RandomAccessFile randomAccessFile;
	private String fileName;

	public MockedDataSourceWriter(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void open() throws DataSourceException {
		try {
			File databaseFile = new File(fileName);
			randomAccessFile = new RandomAccessFile(databaseFile, "rw");
		} catch (IOException e) {
			throw new DataSourceException(e.getMessage());
		}
	}

	@Override
	public void writeString(String value, int size) throws DataSourceException {
		try {
			byte[] valueBytes = value.getBytes();
			byte[] bytes = new byte[size];
			arraycopy(valueBytes, 0, bytes, 0, valueBytes.length);
			randomAccessFile.write(bytes);
		} catch (IOException e) {
			throw new DataSourceException(e.getMessage());
		}
	}

	@Override
	public int getLength() throws DataSourceException {
		try {
			return (int) randomAccessFile.length();
		} catch (IOException e) {
			throw new DataSourceException(e.getMessage());
		}
	}

	@Override
	public void close() throws DataSourceException {
		try {
			randomAccessFile.close();
		} catch (IOException e) {
			System.out.println("mocked data source writer " + e.getMessage());
			throw new DataSourceException(e.getMessage());
		}
	}

	@Override
	public void moveForward(int distance) throws DataSourceException {
		try {
			randomAccessFile.skipBytes(distance);
		} catch (IOException e) {
			throw new DataSourceException(e.getMessage());
		}
	}

	@Override
	public void writeShort(short value) throws DataSourceException {
		try {
			randomAccessFile.writeShort(value);
		} catch (IOException e) {
			throw new DataSourceException(e.getMessage());
		}

	}
}
