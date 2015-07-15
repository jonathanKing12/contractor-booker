package suncertify.db.table;

/**
 * Represents a table column
 */
class TableColumn {

    private int size;
    private int index;

    /**
     * creates a table column of specified size and position
     * 
     * @param valueSize
     *            - the size of the column
     * @param columnIndex
     *            - the column's index in the table
     */
    TableColumn(int valueSize, int columnIndex) {
        this.size = valueSize;
        this.index = columnIndex;
    }

    /**
     * gets the columns index
     * 
     * @return the column's index
     */
    int getIndex() {
        return index;
    }

    /**
     * gets the size of the column
     * 
     * @return the column's size
     */
    int getSize() {
        return size;
    }
}
