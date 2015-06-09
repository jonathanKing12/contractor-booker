package filesource;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import datasource.DataSourceException;
import datasource.DataSourceReader;

public class FileStreamReader implements DataSourceReader {

	private DataInputStream dataInputStream;
	private String fileName;

	public FileStreamReader(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void open() throws DataSourceException {
		try {
			dataInputStream = connectToDatabaseFile(fileName);
		} catch (IOException e) {
			throw new DataSourceException(e.getMessage());
		}

	}

	@Override
	public String readString(int size) throws DataSourceException {
		try {
			final byte[] data = new byte[size];
			dataInputStream.read(data);
			return new String(data).trim();
		} catch (IOException e) {
			throw new DataSourceException(e.getMessage());
		}
	}

	@Override
	public int readInt() throws DataSourceException {
		try {
			return dataInputStream.readInt();
		} catch (IOException e) {
			throw new DataSourceException(e.getMessage());
		}
	}

	@Override
	public short readShort() throws DataSourceException {
		try {
			return dataInputStream.readShort();
		} catch (IOException e) {
			throw new DataSourceException(e.getMessage());
		}
	}

	@Override
	public void moveForwardBy(int distance) throws DataSourceException {
		try {
			dataInputStream.skipBytes(distance);
		} catch (IOException e) {
			throw new DataSourceException(e.getMessage());
		}
	}

	@Override
	public void close() throws DataSourceException {
		try {
			dataInputStream.close();
		} catch (IOException e) {
			throw new DataSourceException(e.getMessage());
		}
	}

	@Override
	public int available() throws DataSourceException {
		try {
			return dataInputStream.available();
		} catch (IOException e) {
			throw new DataSourceException(e.getMessage());
		}
	}

	private DataInputStream connectToDatabaseFile(String fileName) throws FileNotFoundException {
		File databaseFile = new File(fileName);
		return new DataInputStream(new FileInputStream(fileName));
	}
}
