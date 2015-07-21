package suncertify.db.datasource;


public interface DataSourceWriter {

    /**
     * Opens a connection to the data source
     * 
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    void open() throws DataSourceException;

    /**
     * Returns the length of the data source
     * 
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    int getLength() throws DataSourceException;

    /**
     * Moves forward in the data source by the specified distance
     * 
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    void moveForward(int distance) throws DataSourceException;

    /**
     * Writes the specified column value to the data source.
     * 
     * @param columnValue
     *            - the value that is to be written
     * @param columnSize
     *            - the size of data in the data source that is to be override by the columnValue
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    void writeString(String columnValue, int columnSize) throws DataSourceException;

    /**
     * Writes the specified value to the data source.
     * 
     * @param value
     *            - the value that is to be written
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    void writeShort(short value) throws DataSourceException;

    /**
     * Closes a connection to the data source
     * 
     * @throws DataSourceException
     *             if a DataSourceException occurs
     */
    void close() throws DataSourceException;
}
