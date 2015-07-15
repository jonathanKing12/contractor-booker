package suncertify.db.table;

import suncertify.db.datasource.DataSourceException;
import suncertify.db.datasource.DataSourceFactory;
import suncertify.db.datasource.DataSourceWriter;

public class TableWriter {

	private TableHeader header;
	private TableCellPointer cellPointer;
	private TableDataCounter dataCounter;
	private DataSourceWriter dataSourceWriter;

	/**
	 * Creates a table writer that writes to table
	 * 
	 * @param factory
	 *            - a {@link DataSourceFactory}
	 */
	public TableWriter(DataSourceFactory factory) {
		this.header = new TableHeader(factory);
		this.dataSourceWriter = factory.getDataSourceWritter();
		cellPointer = new TableCellPointer();
		dataCounter = new TableDataCounter(header, factory.getDataSourceReader());
	}

	/**
	 * establishes a new connection to the table
	 * 
	 * @throws DataSourceException
	 *             - if a data source error occurs
	 */
	public void open() throws DataSourceException {
		header.readTableHeader();
		cellPointer.setNumberOfColumns(header.getNumberOfColumns());
		cellPointer.moveToStartOfRow(0);
		dataCounter.setAmountOfDataRead(0);
		dataSourceWriter.open();
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
		dataSourceWriter.moveForward(dataSize);
		dataCounter.incrementBy(dataSize);
		cellPointer.moveToStartOfRow(rowNumber);
	}

	/**
	 * Writes the specified value to the next column of the current row
	 * 
	 * @param columnValue
	 *            - the value to be written to column
	 * @throws DataSourceException
	 *             - if a data source error occurs
	 */
	public void writeNextColumn(String columnValue) throws DataSourceException {
		int columnIndex = cellPointer.getColumnNumber();
		int columnSize = header.getColumnSize(columnIndex);
		writeValueToColumn(columnValue, columnIndex, columnSize);

		dataCounter.incrementBy(columnSize);
		cellPointer.moveToNextColumn();
	}

	/**
	 * closes the connection to the table
	 * 
	 * @throws DataSourceException
	 *             - if a data source error occurs
	 */
	public void close() throws DataSourceException {
		dataSourceWriter.close();
	}

	private void writeValueToColumn(String columnValue, int columnIndex, int columnSize)
			throws DataSourceException {
		if (isFirstColumn(columnIndex)) {
			writeShort(columnValue);
		} else {
			writeString(columnValue, columnSize);
		}
	}

	private boolean isFirstColumn(int columnIndex) {
		return columnIndex == 0;
	}

	private void writeString(String columnValue, int columnSize) throws DataSourceException {
		dataSourceWriter.writeString(columnValue, columnSize);
	}

	private void writeShort(String columnValue) throws DataSourceException {
		int hexAsInt = Integer.parseInt(columnValue, 16);
		short hexAsShort = (short) hexAsInt;
		dataSourceWriter.writeShort(hexAsShort);
	}
}
