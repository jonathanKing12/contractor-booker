package filesource;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import datasource.DataSourceException;
import datasource.DataSourceReader;

public class FileStreamReader implements DataSourceReader {

	private static FileStreamReadHelper readHelper;
	private DataInputStream dataInputStream;
	private String fileName;

	static {
		readHelper = new FileStreamReadHelper();
	}

	public FileStreamReader(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void open() throws DataSourceException {
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
			readHelper.moveForwardInStreamBy(dataInputStream, distance);
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
		return new DataInputStream(new FileInputStream(fileName));
	}
}
