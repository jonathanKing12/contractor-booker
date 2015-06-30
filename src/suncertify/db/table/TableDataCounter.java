package suncertify.db.table;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import suncertify.db.datasource.DataSourceException;
import suncertify.db.datasource.DataSourceReader;

/**
 * Keeps track of the amount of data that has been read from the table
 */
class TableDataCounter {

	private int totalAmountOfDataRead;
	private TableHeader header;
	private DataSourceReader reader;

	/**
	 * Creates a new TableDataCounter with the specified table header and data source reader
	 * 
	 * @param header
	 *            - the table header
	 * @param reader
	 *            - the data source reader used to get access to the table data
	 */
	TableDataCounter(TableHeader header, DataSourceReader reader) {
		this.header = header;
		this.reader = reader;
	}

	/**
	 * Sets the total amount of data that has been read from the table
	 * 
	 * @param amount
	 *            - the total amount of data that has been read
	 */
	void setAmountOfDataRead(int amount) {
		totalAmountOfDataRead = amount;
	}

	/**
	 * Increments the total amount of data that has been read from the table
	 * 
	 * @param amount
	 *            - the amount to increase the total amount of data read by
	 */
	void incrementBy(int amount) {
		totalAmountOfDataRead += amount;
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
	boolean doesRowExists(int rowNumber) throws DataSourceException {
		if (rowNumber < 0) {
			return FALSE;
		}

		if (hasAlreadyReadRow(rowNumber)) {
			return TRUE;
		}
		return isThereEnoughtAvailableDataForRowToExist(rowNumber);
	}

	/**
	 * Returns the amount of data that has been read from the table
	 * 
	 * @return the amount of read table data
	 */
	int getAmountOfDataThatsBeenRead() {
		return totalAmountOfDataRead;
	}

	/**
	 * Returns the size of the gap between the last piece of data read from the table to the start
	 * of the specified row. If the specified row has already been read then a negative number will
	 * be returned.
	 * 
	 * @param rowNumber
	 *            - the specified row position.
	 * @return the size of data gap
	 */
	int getSizeOfDataBetweenCurrentPositionAndStartOfRow(int rowNumber) {
		int rowPosition = header.getRowStartingPosition(rowNumber);
		return rowPosition - totalAmountOfDataRead;
	}

	private boolean hasAlreadyReadRow(int rowNumber) {
		int rowStartingPosition = header.getRowStartingPosition(rowNumber);
		return rowStartingPosition < totalAmountOfDataRead;
	}

	private boolean isThereEnoughtAvailableDataForRowToExist(int rowNumber)
			throws DataSourceException {
		int dataSize = getSizeOfDataBetweenCurrentPositionAndStartOfRow(rowNumber);
		int available = reader.available();
		int rowsize = header.getRowSize();
		int tem = dataSize + rowsize;
		return available >= (tem);
	}
}
