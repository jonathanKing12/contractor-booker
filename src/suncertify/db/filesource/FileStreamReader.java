package suncertify.db.filesource;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import suncertify.db.datasource.DataSourceException;
import suncertify.db.datasource.DataSourceReader;

public class FileStreamReader implements DataSourceReader {

	private static FileStreamReadHelper readHelper;
	private DataInputStream dataInputStream;

	static {
		readHelper = new FileStreamReadHelper();
	}

	@Override
	public void open() throws DataSourceException {
		System.out.println("createDataInputStream open method");
		try {
			dataInputStream = createDataInputStream();
		} catch (IOException e) {
			throw new DataSourceException(e.getMessage());
		}
	}

	@Override
	public String readString(int size) throws DataSourceException {
		try {
			final byte[] data = new byte[size];
			readHelper.fillByteArrayFromStream(data, dataInputStream);
			return new String(data).trim();

		} catch (IOException e) {
			throw new DataSourceException(e.getMessage());
		}
	}

	@Override
	public int readInt() throws DataSourceException {
		try {
			return readHelper.readIntFromStream(dataInputStream);
		} catch (IOException e) {
			throw new DataSourceException(e.getMessage());
		}
	}

	@Override
	public short readShort() throws DataSourceException {
		try {
			return readHelper.readShortFromStream(dataInputStream);
		} catch (IOException e) {
			throw new DataSourceException(e.getMessage());
		}
	}

	@Override
	public void moveForwardBy(int distance) throws DataSourceException {
		try {
			readHelper.moveForwardAccrossStreamBy(dataInputStream, distance);
		} catch (IOException e) {
			throw new DataSourceException(e.getMessage());
		}
	}

	@Override
	public int available() throws DataSourceException {
		try {
			return readHelper.getAvailableBytes(dataInputStream);
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

	private DataInputStream createDataInputStream() throws FileNotFoundException {
		DatabaseFinder finder = new DatabaseFinder();
		File file = finder.getDatabaseFile();
		System.out.println("createDataInputStream for filestream reader");
		System.out.println("fileStreamReader is file " + file.isFile());
		return new DataInputStream(new FileInputStream(file));
	}
}
