package suncertify.db.table;

import datasource.DataSourceException;
import datasource.DataSourceFactory;
import datasource.DataSourceWriter;

public class TableWriter {

	private TableHeader header;
	private TableCellPointer cursor;
	private TableDataCounter dataCounter;
	private DataSourceWriter dataSourceWriter;

	public TableWriter(DataSourceFactory factory) {
		this.header = new TableHeader(factory);
		this.dataSourceWriter = factory.getDatoSourceWritter();
		cursor = new TableCellPointer(header);
		dataCounter = new TableDataCounter(header, factory.getDatoSourceReader());
	}

	public void open() throws DataSourceException {
		header.readTableHeader();
		dataSourceWriter.open();
		cursor.moveToStartOfRow(0);
	}

	public void moveToRow(int rowNumber) throws DataSourceException {
		int dataSize = dataCounter.getSizeOfDataBetweenCurrentPositionAndStartOfRow(rowNumber);
		dataSourceWriter.moveForward(dataSize);
		dataCounter.incrementBy(dataSize);
		cursor.moveToStartOfRow(rowNumber);
	}

	public void writeNextColumn(String columnValue) throws DataSourceException {
		int columnIndex = cursor.getColumnNumber();
		int columnSize = header.getColumnSize(columnIndex);
		dataSourceWriter.write(columnValue, columnSize);
		dataCounter.incrementBy(columnSize);
		cursor.moveToNextColumn();
	}

	// public int moveToEndOfLastRow() throws DataSourceException {
	// int dataSourceSize = dataSourceWriter.getLength();
	// int dataSizeThatsBeenRead = dataCounter.getSizeOfDataThatsBeenRead();
	// int dataSizeToEndOfDataSource = dataSourceSize - dataSizeThatsBeenRead;
	// dataSourceWriter.moveForward(dataSizeToEndOfDataSource);
	// dataCounter.incrementBy(dataSizeToEndOfDataSource);
	// cursor.moveToStartOfRow(cursor.getRowNumber() + 1);
	// return getLastRowPosition(dataSourceSize);
	// }

	public void close() throws DataSourceException {
		dataSourceWriter.close();
	}

	// private int getLastRowPosition(int length) throws DataSourceException {
	// int firstRowBytePosition = header.getRowStartingPosition(0);
	// int numberOfRows = length - firstRowBytePosition;
	// return numberOfRows / header.getRowSize();
	// }
}
