package suncertify.db.table;

/**
 * Creates a Table cell pointer that keeps track of the next cell in the table to read from
 *
 */
class TableCellPointer {

    protected int columnNumber;
    protected int numberOfColumns;
    private int rowNumber;

    /**
     * sets the number of columns to the specified numberOfColumns
     * 
     * @param numberOfColumns
     *            - the numberOfColumns
     */
    void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    /**
     * Returns the column's index of the cell that is being pointed at by this cell pointer
     * 
     * @return the index of the column that the cell is inside
     */
    int getColumnNumber() {
        return columnNumber;
    }

    /**
     * Returns the row's index of the cell that is being pointed at by this cell pointer
     * 
     * @return the index of the row that the cell is inside
     */
    int getRowNumber() {
        return rowNumber;
    }

    /**
     * moves this table cell pointer to point to the first cell at the specified row position
     * 
     * @param rowNumber
     *            - the row position
     */
    void moveToStartOfRow(int rowNumber) {
        this.rowNumber = rowNumber;
        columnNumber = 0;
    }

    /**
     * Moves this table cell pointer right one column to the next cell in the current row. If the current cell is the last cell of the current row
     * then the next cell will be the first cell of the next row
     * 
     **/
    void moveToNextColumn() {
        columnNumber++;
        if (columnNumber >= numberOfColumns) {
            moveToStartOfNextRow();
        }
    }

    private void moveToStartOfNextRow() {
        int nextRowNumber = rowNumber + 1;
        moveToStartOfRow(nextRowNumber);
    }
}
