package suncertify.db.table;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import datasource.DataSourceException;
import datasource.DataSourceReader;

class TableDataCounter {

	private int totalAmountOfDataRead;
	private TableHeader header;
	private DataSourceReader reader;

	TableDataCounter(TableHeader header, DataSourceReader reader) {
		this.header = header;
		this.reader = reader;
	}

	void setAmountOfDataRead(int amount) {
		totalAmountOfDataRead = amount;
	}

	void incrementBy(int data) {
		totalAmountOfDataRead += data;
	}

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
		return reader.available() >= (dataSize + header.getRowSize());
	}

	int getSizeOfDataBetweenCurrentPositionAndStartOfNextRow(int rowNumber) {
		int rowPosition = header.getRowStartingPosition(rowNumber);
		System.out.println("calculate " + rowPosition + " " + totalAmountOfDataRead);
		return rowPosition - totalAmountOfDataRead;
	}
}
