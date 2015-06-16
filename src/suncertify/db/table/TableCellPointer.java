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
		System.out.println("number of columns " + numberOfColumns);
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

	public static void main(String[] args) {
		short s = 0x0;
		System.out.println(String.format("%04X", s));
	}
}
