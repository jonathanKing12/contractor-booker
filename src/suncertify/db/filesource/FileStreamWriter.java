package suncertify.db.filesource;

import static java.lang.System.arraycopy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import settings.SettingException;
import suncertify.db.datasource.DataSourceException;
import suncertify.db.datasource.DataSourceWriter;

public class FileStreamWriter implements DataSourceWriter {

	private RandomAccessFile randomAccessFile;

	@Override
	public void open() throws DataSourceException {
		try {
			randomAccessFile = createRandomAccessFile();
		} catch (FileNotFoundException | SettingException e) {
			throw new DataSourceException(e.getMessage());
		}
	}

	private RandomAccessFile createRandomAccessFile() throws FileNotFoundException {
		DatabaseFinder finder = new DatabaseFinder();
		File file = finder.getDatabaseFile();
		return new RandomAccessFile(file, "rw");
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
