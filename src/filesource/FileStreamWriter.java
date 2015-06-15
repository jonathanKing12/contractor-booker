package filesource;

import static java.lang.System.arraycopy;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import datasource.DataSourceException;
import datasource.DataSourceWriter;
import datasource.locator.DataSourceLocationException;
import datasource.locator.DataSourceLocator;
import datasource.locator.DataSourceLocatorFactory;

public class FileStreamWriter implements DataSourceWriter {

	private RandomAccessFile randomAccessFile;
	private String fileName;

	public FileStreamWriter(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void open() throws DataSourceException {
		retreiveFileName();
		try {
			File databaseFile = new File(fileName);
			randomAccessFile = new RandomAccessFile(databaseFile, "rw");
		} catch (IOException e) {
			throw new DataSourceException(e.getMessage());
		}
	}

	private void retreiveFileName() throws DataSourceLocationException {
		if (fileName.isEmpty()) {
			DataSourceLocatorFactory factory = new DataSourceLocatorFactory();
			DataSourceLocator locator = factory.getDataSourceLocator();
			String location = locator.getLocation();
			fileName = location + "db-write2.db";
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

	public static void main(String[] args) {

		String start = "8000";
		int next = Integer.parseInt(start, 16);
		short deleted = (short) next;

		File databaseFile = new File("C:/db/deleteFile.db");
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(databaseFile, "rw")) {
			randomAccessFile.writeShort(deleted);
		} catch (IOException e) {
			System.out.println(e.getSuppressed());
			System.out.println(e.getMessage());
		}

		short isDeleted = -1;
		String third = "-1";
		File databaseFile2 = new File("C:/db/deleteFile.db");
		try (DataInputStream randomAccessFile2 = new DataInputStream(new FileInputStream(databaseFile2))) {

			isDeleted = randomAccessFile2.readShort();
			third = String.format("%04X", isDeleted);

		} catch (IOException e) {
			System.out.println(e.getSuppressed());
			System.out.println(e.getMessage());
		}

		System.out.println("next " + next);
		System.out.println("deleted " + deleted);
		System.out.println("isDeleted " + isDeleted);
		System.out.println("third " + third);

		// System.out.println(Integer.toBinaryString(Integer.parseInt(s)));
		// System.out.println(Integer.toHexString(randomAccessFile2.readShort()));
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
