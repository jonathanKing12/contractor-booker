package mockedcontent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;

import datasource.DataSourceException;
import datasource.DataSourceWriter;
import filesource.FileStreamWriter;

public class MockedDataSourceWriter implements DataSourceWriter {

	private FileStreamWriter fileStreamWriter;
	private String fileName;

	public MockedDataSourceWriter(String fileName) {
		this.fileName = fileName;
		fileStreamWriter = new FileStreamWriter(fileName);
	}

	@Override
	public void open() throws DataSourceException {
		try {
			Field randomAccessFileField = FileStreamWriter.class.getDeclaredField("randomAccessFile");
			randomAccessFileField.setAccessible(true);
			randomAccessFileField.set(fileStreamWriter, getRandomAccessFile());

		} catch (IOException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new DataSourceException(e.getMessage());
		}
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

	private RandomAccessFile getRandomAccessFile() throws FileNotFoundException {
		return new RandomAccessFile(new File(fileName), "rw");
	}
}
