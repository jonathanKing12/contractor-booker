package suncertify.db.datasource;


public interface DataSourceReader {

    /**
     * Opens a connection to the data source
     * 
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    void open() throws DataSourceException;

    /**
     * Reads a string of the specified size from a data source
     * 
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    String readString(int size) throws DataSourceException;

    /**
     * Reads a integer value from a data source
     * 
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    int readInt() throws DataSourceException;

    /**
     * Reads a short value from a data source
     * 
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    short readShort() throws DataSourceException;

    /**
     * Moves forward in the data source by the specified distance
     * 
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    void moveForwardBy(int distance) throws DataSourceException;

    /**
     * Returns the size of data left to read from the data source
     * 
     * @return the size of data
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    int available() throws DataSourceException;

    /**
     * Closes a connection to the data source
     * 
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    void close() throws DataSourceException;
}
