package suncertify.db.table;

class TableCellPointer {

	protected int columnNumber;
	protected int numberOfColumns;
	private int rowNumber;

	void setNumberOfColumns(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}

	void moveToStartOfRow(int rowNumber) {
		this.rowNumber = rowNumber;
		columnNumber = 0;
	}

	void moveToNextColumn() {
		columnNumber++;
		if (columnNumber >= numberOfColumns) {
			moveToStartOfNextRow();
		}
	}

	private void moveToStartOfNextRow() {
		int nextRowNumber = rowNumber+1;
		moveToStartOfRow(nextRowNumber);
	}

	int getColumnNumber() {
		return columnNumber;
	}

	int getRowNumber() {
		return rowNumber;
	}
}
