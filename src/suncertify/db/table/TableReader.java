package suncertify.db.table;

import suncertify.db.datasource.DataSourceException;
import suncertify.db.datasource.DataSourceFactory;
import suncertify.db.datasource.DataSourceReader;

/**
 * Reads a data source in the format of table
 */
public class TableReader {

	private TableHeader header;
	private DataSourceReader dataSourceReader;
	private TableCellPointer cellPointer;
	private TableDataCounter dataCounter;

	/**
	 * Creates a table reader that read from a table. format
	 * 
	 * @param factory
	 *            - a {@link DataSourceFactory}
	 */
	public TableReader(DataSourceFactory factory) {
		header = new TableHeader(factory);
		cellPointer = new TableCellPointer();
		dataSourceReader = factory.getDataSourceReader();
	}

	/**
	 * establishes a new connection to the table
	 * 
	 * @throws DataSourceException
	 *             - if a data source error occurs
	 */
	public void open() throws DataSourceException {
		header.readTableHeader();
		cellPointer.moveToStartOfRow(0);
		cellPointer.setNumberOfColumns(header.getNumberOfColumns());
		dataCounter = new TableDataCounter(header, dataSourceReader);
		dataSourceReader.open();
	}

	/**
	 * Returns the value of cell at the next column on the current row to be read
	 * 
	 * @return cellValue - the value at the next cell
	 * @throws DataSourceException
	 *             - if a data source error occurs
	 */
	public String readNextColumn() throws DataSourceException {
		int columnNumber = cellPointer.getColumnNumber();
		int columnSize = header.getColumnSize(columnNumber);
		String columnValue = readColumnValue(columnNumber, columnSize);

		dataCounter.incrementBy(columnSize);
		cellPointer.moveToNextColumn();
		return columnValue.trim();
	}

	/**
	 * Moves forward to the beginning of the row at the specified position
	 * 
	 * @param rowNumber
	 *            - the row position
	 * @throws DataSourceException
	 *             - if a data source error occurs
	 */
	public void moveToRow(int rowNumber) throws DataSourceException {
		int dataSize = dataCounter.getSizeOfDataBetweenCurrentPositionAndStartOfRow(rowNumber);
		dataSourceReader.moveForwardBy(dataSize);
		dataCounter.incrementBy(dataSize);
		cellPointer.moveToStartOfRow(rowNumber);
	}

	/**
	 * Returns {@code true} if the table has a row at the specified position.
	 * 
	 * @param rowNumber
	 *            - the position in the table to check if a row exists
	 * @return {@code true} - if the row at the specified position exists in the table
	 * @throws DataSourceException
	 *             if a data source error occurs
	 */
	public boolean hasRow(int rowNumber) throws DataSourceException {
		return dataCounter.doesRowExists(rowNumber);
	}

	/**
	 * closes the connection to the table
	 * 
	 * @throws DataSourceException
	 *             - if a data source error occurs
	 */
	public void close() throws DataSourceException {
		dataSourceReader.close();
	}

	private String readColumnValue(int columnNumber, int columnSize) throws DataSourceException {
		return (columnNumber == 0) ? readShort() : readString(columnSize);
	}

	private String readShort() throws DataSourceException {
		Short hexAsShort = dataSourceReader.readShort();
		return String.format("%04X", hexAsShort);
	}

	private String readString(int columnSize) throws DataSourceException {
		String columnValue;
		columnValue = dataSourceReader.readString(columnSize);
		return columnValue;
	}
}
