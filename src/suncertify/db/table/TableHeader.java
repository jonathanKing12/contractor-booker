package suncertify.db.table;

import static java.lang.Boolean.TRUE;

import java.util.ArrayList;
import java.util.List;

import suncertify.db.datasource.*;

/**
 * Stores the header of the data source.
 *
 */
class TableHeader {

    private int startingPositionOfFirstCell;
    private List<TableColumn> columns;
    private int rowSize;
    private boolean alreadyBeenRead;
    private DataSourceReader dataSourceReader;

    /**
     * Create a TableHeader instance
     * 
     * @param - a {@link DataSourceFactory}
     */
    TableHeader(DataSourceFactory factory) {
        dataSourceReader = factory.getDataSourceReader();
    }

    /**
     * Gets the position of the first cell in the specified row
     * 
     * @param rowNumber
     *            - the number of the specified row
     * @return the position of the first cell in the specified row
     */
    int getRowStartingPosition(int rowNumber) {
        return rowSize * rowNumber + startingPositionOfFirstCell;
    }

    /**
     * Gets the size of the specified cell
     * 
     * @param columnIndex
     *            - the index of the specified cell
     * @return the column size
     * @throws NullPointerException
     *             if there no column at the specified index
     */
    int getColumnSize(int columnIndex) {
        TableColumn column = null;
        for (TableColumn nextColumn : columns) {
            if (nextColumn.getIndex() == columnIndex) {
                column = nextColumn;
                break;
            }
        }
        return column.getSize();
    }

    /**
     * Reads the table header for the first time it is called
     * 
     * @throws DataSourceException
     *             if a DataSource exception occurs
     */
    void readTableHeader() throws DataSourceException {
        if (alreadyBeenRead) {
            return;
        }

        readTableHeaderContents();
        alreadyBeenRead = TRUE;
    }

    /**
     * get the size of a row
     * 
     * @return row size
     */
    int getRowSize() {
        return rowSize;
    }

    /**
     * gets the number of columns in the table
     * 
     * @return number of columns
     */
    int getNumberOfColumns() {
        return columns.size();
    }

    private void readTableHeaderContents() throws DataSourceException {
        dataSourceReader.open();
        int isDataFile = dataSourceReader.readInt();
        startingPositionOfFirstCell = dataSourceReader.readInt();
        columns = readColumnHeaders(dataSourceReader);
        startingPositionOfFirstCell = getRowStartingPosition(0);
        dataSourceReader.close();
    }

    private List<TableColumn> readColumnHeaders(DataSourceReader dataInputStream)
            throws DataSourceException {
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

    private TableColumn readColumnHeader(final int columnIndex, DataSourceReader dataInputStream)
            throws DataSourceException {
        final int sizeOfColumnName = dataInputStream.readShort();
        dataInputStream.moveForwardBy(sizeOfColumnName);
        final int sizeOfColumnValue = dataInputStream.readShort();
        rowSize += sizeOfColumnValue;
        return new TableColumn(sizeOfColumnValue, columnIndex);
    }
}
