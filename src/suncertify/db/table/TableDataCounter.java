package suncertify.db.table;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.util.HashMap;

import suncertify.db.datasource.DataSourceException;
import suncertify.db.datasource.DataSourceReader;

/**
 * Keeps track of the amount of data that has been read from the table
 *
 */
class TableDataCounter {

	private int totalAmountOfDataRead;
	private TableHeader header;
	private DataSourceReader reader;

	TableDataCounter(TableHeader header, DataSourceReader reader) {
		this.header = header;
		this.reader = reader;
	}

	/**
	 * Sets the total amount of data that is read from the table
	 * @param amount The total amount of data that is read
	 */
	void setAmountOfDataRead(int amount) {
		totalAmountOfDataRead = amount;
	}

	/**
	 * Increments the total amount of data that is read from the table
	 * @param amount The amount to increases the total amount of read data by
	 */
	void incrementBy(int amount) {
		totalAmountOfDataRead += amount;
	}

	/**
	 * Check if the table has a row.
	 * @param rowNumber
	 * @return
	 * @throws DataSourceException If the amount of available data can be be retreived from the DataSourceReader
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

	int getSizeOfDataThatsBeenRead() {
		return totalAmountOfDataRead;
	}

	private boolean hasAlreadyReadRow(int rowNumber) {
		int rowStartingPosition = header.getRowStartingPosition(rowNumber);
		return rowStartingPosition < totalAmountOfDataRead;
	}

	private boolean isThereEnoughtAvailableDataForRowToExist(int rowNumber) throws DataSourceException {
		int dataSize = getSizeOfDataBetweenCurrentPositionAndStartOfNextRow(rowNumber);
		int available = reader.available();
		int rowsize = header.getRowSize();
		int tem = dataSize + rowsize;
		return available >= (tem);
	}

	int getSizeOfDataBetweenCurrentPositionAndStartOfNextRow(int rowNumber) {
		int rowPosition = header.getRowStartingPosition(rowNumber);
		return rowPosition - totalAmountOfDataRead;
	}
}
