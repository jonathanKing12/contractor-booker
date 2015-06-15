package suncertify.db.table;

class TableCellPointer {

	protected int columnNumber;
	protected int numberOfColumns;
	private int rowNumber;

	TableCellPointer(int numberOfColumns) {
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
		int rowNumber = getRowNumber();
		rowNumber++;
		moveToStartOfRow(rowNumber);
	}

	int getColumnNumber() {
		return columnNumber;
	}

	int getRowNumber() {
		return rowNumber;
	}
}
