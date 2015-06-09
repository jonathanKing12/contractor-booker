package suncertify.db.table;

class TableCellPointer {

	protected int columnNumber;
	protected TableHeader header;
	private int rowNumber;

	TableCellPointer(TableHeader header) {
		this.header = header;
	}

	void moveToStartOfRow(int rowNumber) {
		this.rowNumber = rowNumber;
		columnNumber = 0;
	}

	void moveToNextColumn() {
		columnNumber++;

		if (columnNumber >= header.getNumberOfColumns()) {
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
