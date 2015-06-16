package suncertify.db.table;

import datasource.DataSourceException;
import datasource.DataSourceFactory;
import datasource.DataSourceReader;

public class TableReader {

	private TableHeader header;
	private DataSourceReader dataSourceReader;
	private TableCellPointer tableCellPointer;
	private TableDataCounter dataCounter;

	// private DataSourceFactory factory;

	public TableReader(DataSourceFactory factory) {
		header = new TableHeader(factory);
		tableCellPointer = new TableCellPointer();
		// this.factory = factory;
		dataSourceReader = factory.getDatoSourceReader();
		// dataCounter = new TableDataCounter(header, dataSourceReader);
	}

	public void open() throws DataSourceException {
		header.readTableHeader();
		tableCellPointer.moveToStartOfRow(0);
		tableCellPointer.setNumberOfColumns(header.getNumberOfColumns());
		// dataCounter.setAmountOfDataRead(header.getRowStartingPosition(0));
		dataCounter = new TableDataCounter(header, dataSourceReader);
		dataSourceReader.open();
	}

	public String readNextColumn() throws DataSourceException {
		int columnNumber = tableCellPointer.getColumnNumber();
		int columnSize = header.getColumnSize(columnNumber);
		String columnValue;
		if (columnNumber == 0) {
			columnValue = readShort();
		} else {
			columnValue = readString(columnSize);
		}
		dataCounter.incrementBy(columnSize);
		tableCellPointer.moveToNextColumn();
		return columnValue.trim();
	}

	private String readString(int columnSize) throws DataSourceException {
		String columnValue;
		columnValue = dataSourceReader.readString(columnSize);
		return columnValue;
	}

	private String readShort() throws DataSourceException {
		Short hexAsShort = dataSourceReader.readShort();
		return String.format("%04X", hexAsShort);
	}

	public void moveToRow(int rowNumber) throws DataSourceException {
		int dataSize = dataCounter.getSizeOfDataBetweenCurrentPositionAndStartOfNextRow(rowNumber);
		dataSourceReader.moveForwardBy(dataSize);
		dataCounter.incrementBy(dataSize);
		tableCellPointer.moveToStartOfRow(rowNumber);
	}

	public boolean hasRow(int rowNumber) throws DataSourceException {
		return dataCounter.doesRowExists(rowNumber);
	}

	public void close() throws DataSourceException {
		dataSourceReader.close();
	}
}
