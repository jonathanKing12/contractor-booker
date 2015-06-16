package suncertify.db.table;

import datasource.DataSourceException;
import datasource.DataSourceFactory;
import datasource.DataSourceWriter;

public class TableWriter {

	private TableHeader header;
	private TableCellPointer tableCellPointer;
	private TableDataCounter dataCounter;
	private DataSourceWriter dataSourceWriter;

	public TableWriter(DataSourceFactory factory) {
		this.header = new TableHeader(factory);
		this.dataSourceWriter = factory.getDatoSourceWritter();
		tableCellPointer = new TableCellPointer();
		dataCounter = new TableDataCounter(header, factory.getDatoSourceReader());
	}

	public void open() throws DataSourceException {
		header.readTableHeader();
		tableCellPointer.setNumberOfColumns(header.getNumberOfColumns());
		tableCellPointer.moveToStartOfRow(0);
		dataCounter.setAmountOfDataRead(0);
		dataSourceWriter.open();
	}

	public void moveToRow(int rowNumber) throws DataSourceException {
		int dataSize = dataCounter.getSizeOfDataBetweenCurrentPositionAndStartOfNextRow(rowNumber);
		dataSourceWriter.moveForward(dataSize);
		dataCounter.incrementBy(dataSize);
		tableCellPointer.moveToStartOfRow(rowNumber);
	}

	public void writeNextColumn(String columnValue) throws DataSourceException {
		int columnIndex = tableCellPointer.getColumnNumber();
		int columnSize = header.getColumnSize(columnIndex);

		if (isFirstColumn(columnIndex)) {
			writeShort(columnValue);
		} else {
			writeString(columnValue, columnSize);
		}
		dataCounter.incrementBy(columnSize);
		tableCellPointer.moveToNextColumn();
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

	public void close() throws DataSourceException {
		dataSourceWriter.close();
	}
}
