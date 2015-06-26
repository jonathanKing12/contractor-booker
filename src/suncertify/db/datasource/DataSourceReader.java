package suncertify.db.datasource;

public interface DataSourceReader {

	String readString(int size) throws DataSourceException;

	int readInt() throws DataSourceException;

	short readShort() throws DataSourceException;

	void moveForwardBy(int distance) throws DataSourceException;

	void close() throws DataSourceException;

	int available() throws DataSourceException;

	void open() throws DataSourceException;
}
