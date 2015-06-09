package suncertify.db.table;

public class TableColumn {

	private int valueSize;
	private int columnIndex;

	public TableColumn(int valueSize, int columnIndex) {
		this.valueSize = valueSize;
		this.columnIndex = columnIndex;
	}

	public int getIndex() {
		return columnIndex;
	}

	public int getValueSize() {
		return valueSize;
	}
}
