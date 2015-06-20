package filesource;

import static java.lang.System.arraycopy;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;

import setting.AccessSetting;
import setting.AccessSetting;
import setting.SettingException;
import datasource.DataSourceException;
import datasource.DataSourceWriter;

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

	private void retreiveFileName() throws SettingException {
		if (fileName.isEmpty()) {
			DataSourceLocatorFactory factory = new DataSourceLocatorFactory();
			AccessSetting locator = factory.getDataSourceLocator();
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

	@Override
	public void writeShort(short value) throws DataSourceException {
		try {
			randomAccessFile.writeShort(value);
		} catch (IOException e) {
			throw new DataSourceException(e.getMessage());
		}
	}

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dog dog = new Dog();

		Field field = Dog.class.getDeclaredField("numberOfTimesPlayedfetch");
		field.setAccessible(true);
		field.set(dog, "test");
		System.out.println(dog.getNumberOfTimesPlayedFetch());
	}
}

class Dog {
	private String numberOfTimesPlayedfetch = "initial value";

	public String getNumberOfTimesPlayedFetch() {
		return numberOfTimesPlayedfetch;
	}
}
