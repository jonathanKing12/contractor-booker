package suncertify.db.table;

import static java.lang.Boolean.TRUE;

import java.util.ArrayList;
import java.util.List;

import datasource.DataSourceException;
import datasource.DataSourceFactory;
import datasource.DataSourceReader;

class TableHeader {

	private int startingPositionOfFirstCell;
	private List<TableColumn> columns;
	private int rowSize;
	private boolean alreadyBeenRead;
	private DataSourceReader dataSourceReader;

	TableHeader(DataSourceFactory factory) {
		dataSourceReader=factory.getDatoSourceReader();
	}

	int getRowStartingPosition(int recordNumber) {
		return rowSize * recordNumber + startingPositionOfFirstCell;
	}

	int getColumnSize(int columnIndex) {
		TableColumn column = null;
		for (TableColumn nextColumn : columns) {
			if (nextColumn.getIndex() == columnIndex) {
				column = nextColumn;
				break;
			}
		}
		return column.getValueSize();
	}

	void readTableHeader() {
		if (alreadyBeenRead) {
			return;
		}

		try {		
			readTableHeaderContents();			
		} catch (DataSourceException e) {
			throw new RuntimeException(e.getMessage());
		}
		
		alreadyBeenRead = TRUE;
	}

	private void readTableHeaderContents() throws DataSourceException {
		dataSourceReader.open();
		int isDataFile = dataSourceReader.readInt();
		startingPositionOfFirstCell = dataSourceReader.readInt();
		columns = readColumnHeaders(dataSourceReader);
		startingPositionOfFirstCell = getRowStartingPosition(0);
		dataSourceReader.close();
	}

	int getRowSize() {
		return rowSize;
	}

	int getNumberOfColumns() {
		return columns.size();
	}

	private List<TableColumn> readColumnHeaders(DataSourceReader dataInputStream) throws DataSourceException {
		int totalNumberofColumns = dataInputStream.readShort();
		final List<TableColumn> columns = new ArrayList<>(totalNumberofColumns);

		columns.add(new TableColumn(2, 0));
		rowSize = 2;
		for (int columnIndex = 0; columnIndex < totalNumberofColumns; columnIndex++) {
			final TableColumn column = readColumnHeader((columnIndex + 1), dataInputStream);
			columns.add(column);
		}
		return columns;
	}

	private TableColumn readColumnHeader(final int columnIndex, DataSourceReader dataInputStream) throws DataSourceException {
		final int sizeOfColumnName = dataInputStream.readShort();
		dataInputStream.moveForwardBy(sizeOfColumnName);
		final int sizeOfColumnValue = dataInputStream.readShort();
		rowSize += sizeOfColumnValue;
		final TableColumn column = new TableColumn(sizeOfColumnValue, columnIndex);
		return column;
	}
}
