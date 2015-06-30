package suncertify.db.table;

/**
 * Represents a table column
 */
class TableColumn {

	private int valueSize;
	private int columnIndex;

	/**
	 * creates a table column of specified size and position
	 * 
	 * @param valueSize
	 *            - the size of the column
	 * @param columnIndex
	 *            - the column's position in the table
	 */
	TableColumn(int valueSize, int columnIndex) {
		this.valueSize = valueSize;
		this.columnIndex = columnIndex;
	}

	int getIndex() {
		return columnIndex;
	}

	int getValueSize() {
		return valueSize;
	}
}
