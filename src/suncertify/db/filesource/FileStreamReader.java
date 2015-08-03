package suncertify.db.filesource;

import java.io.*;

import settings.SettingsException;
import suncertify.db.datasource.DataSourceException;
import suncertify.db.datasource.DataSourceReader;

public class FileStreamReader implements DataSourceReader {

    private static FileStreamReadHelper readHelper;
    private DataInputStream dataInputStream;

    static {
        readHelper = new FileStreamReadHelper();
    }

    /**
     * Opens a connection to a file
     * 
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    @Override
    public void open() throws DataSourceException {
        try {
            dataInputStream = createDataInputStream();
        } catch (IOException | SettingsException e) {
            throw new DataSourceException(e.getMessage());
        }
    }

    /**
     * Reads a string of the specified size from the stream
     * 
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
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

    /**
     * Reads a integer value from the stream
     * 
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    @Override
    public int readInt() throws DataSourceException {
        try {
            return readHelper.readIntFromStream(dataInputStream);
        } catch (IOException e) {
            throw new DataSourceException(e.getMessage());
        }
    }

    /**
     * reads a short value from the stream
     * 
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    @Override
    public short readShort() throws DataSourceException {
        try {
            return readHelper.readShortFromStream(dataInputStream);
        } catch (IOException e) {
            throw new DataSourceException(e.getMessage());
        }
    }

    /**
     * Moves forward in the stream by the specified distance
     * 
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    @Override
    public void moveForwardBy(int distance) throws DataSourceException {
        try {
            readHelper.moveForwardAccrossStreamBy(distance, dataInputStream);
        } catch (IOException e) {
            throw new DataSourceException(e.getMessage());
        }
    }

    /**
     * Returns the size of data left to read from the stream
     * 
     * @return the size of data
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    @Override
    public int available() throws DataSourceException {
        try {
            return readHelper.getAvailableBytes(dataInputStream);
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
            dataInputStream.close();
        } catch (IOException e) {
            throw new DataSourceException(e.getMessage());
        }
    }

    private DataInputStream createDataInputStream() throws FileNotFoundException, SettingsException {
        DatabaseFinder finder = new DatabaseFinder();
        File file = finder.getDatabaseFile();
        return new DataInputStream(new FileInputStream(file));
    }
}
