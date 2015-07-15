package suncertify.db.filesource;

import static java.lang.System.arraycopy;

import java.io.*;

import settings.SettingException;
import suncertify.db.datasource.DataSourceException;
import suncertify.db.datasource.DataSourceWriter;

public class FileStreamWriter implements DataSourceWriter {

    /**
     * 
     */
    private static final int DESTINATION_START_INDEX = 0;
    /**
     * 
     */
    private static final int SOURCE_START_INDEX = 0;
    private RandomAccessFile randomAccessFile;

    /**
     * Opens a connection to a file
     * 
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    @Override
    public void open() throws DataSourceException {
        try {
            randomAccessFile = createRandomAccessFile();
        } catch (FileNotFoundException | SettingException e) {
            throw new DataSourceException(e.getMessage());
        }
    }

    /**
     * Returns the length of the file
     * 
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    @Override
    public int getLength() throws DataSourceException {
        try {
            return (int) randomAccessFile.length();
        } catch (IOException e) {
            throw new DataSourceException(e.getMessage());
        }
    }

    /**
     * Moves forward in the file by the specified distance
     * 
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    @Override
    public void moveForward(int distance) throws DataSourceException {
        try {
            randomAccessFile.skipBytes(distance);
        } catch (IOException e) {
            throw new DataSourceException(e.getMessage());
        }
    }

    /**
     * Writes the specified column value to the file.
     * 
     * @param columnValue
     *            - the value that is to be written
     * @param columnSize
     *            - the size of data in the file that is to be override by the columnValue
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    @Override
    public void writeString(String value, int size) throws DataSourceException {
        try {
            byte[] valueBytes = value.getBytes();
            byte[] bytes = new byte[size];
            arraycopy(valueBytes, SOURCE_START_INDEX, bytes, DESTINATION_START_INDEX,
                    valueBytes.length);
            randomAccessFile.write(bytes);
        } catch (IOException e) {
            throw new DataSourceException(e.getMessage());
        }
    }

    /**
     * Writes the specified value to the file.
     * 
     * @param value
     *            - the value that is to be written
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    @Override
    public void writeShort(short value) throws DataSourceException {
        try {
            randomAccessFile.writeShort(value);
        } catch (IOException e) {
            throw new DataSourceException(e.getMessage());
        }
    }

    /**
     * Closes a connection to the file
     * 
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    @Override
    public void close() throws DataSourceException {
        try {
            randomAccessFile.close();
        } catch (IOException e) {
            throw new DataSourceException(e.getMessage());
        }
    }

    private RandomAccessFile createRandomAccessFile() throws FileNotFoundException {
        DatabaseFinder finder = new DatabaseFinder();
        File file = finder.getDatabaseFile();
        return new RandomAccessFile(file, "rw");
    }
}
