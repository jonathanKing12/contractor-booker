package datasource;

public interface DataSourceWriter {

	void open() throws DataSourceException;

	int getLength() throws DataSourceException;

	void moveForward(int distance) throws DataSourceException;

	void write(String columnValue, int columnSize) throws DataSourceException;

	void close() throws DataSourceException;

}
