package client.model;

import static client.model.Column.LOCATION;
import static client.model.Column.NAME;
import static client.model.Column.OWNER;
import static client.model.Column.RATE;
import static client.model.Column.SELECTED;
import static client.model.ContractorRowModelUtil.toContractorRowModel;
import static constants.Constants.CONTRACTOR_ID_KEY;
import static constants.Constants.FIRST_ROW_INDEX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

public class ContractorTableModel extends AbstractTableModel {

	private List<Map<String, Object>> rows;
	private Column[] columns;

	public ContractorTableModel() {
		columns = new Column[] { SELECTED, NAME, LOCATION, RATE, OWNER };
		rows = new ArrayList<>();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public String getColumnName(final int columnIndex) {
		return columns[columnIndex].getName();
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public Class<?> getColumnClass(final int columnIndex) {
		return columns[columnIndex].getClassType();
	}

	@Override
	public void setValueAt(final Object value, final int rowIndex, final int columnIndex) {
		final Map<String, Object> row = rows.get(rowIndex);
		final String columnName = getColumnName(columnIndex);
		row.put(columnName, value);
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		final Map<String, Object> row = rows.get(rowIndex);
		final String columnName = getColumnName(columnIndex);
		return row.get(columnName);
	}

	@Override
	public boolean isCellEditable(final int rowIndex, final int columnIndex) {
		return columns[columnIndex] == SELECTED;
	}

	public void setContractorRowModels(List<ContractorRowModel> rowModels) {
		if (!rows.isEmpty())
			fireTableRowsDeleted(FIRST_ROW_INDEX, getLastRowIndex());

		this.rows = toListOfMaps(rowModels);
		fireTableRowsInserted(FIRST_ROW_INDEX, getLastRowIndex());
	}

	public ContractorRowModel getModel(int rowIndex) {
		Map<String, Object> row = rows.get(rowIndex);
		return toContractorRowModel(row);
	}

	public void updateContractorRowModel(ContractorRowModel rowModel) {
		Map<String, Object> updatedRow = rowModel.toMap();
		Map<String, Object> oldRow = getRow(rowModel);
		int rowIndex = replaceOldRowWithUpdatedRow(oldRow, updatedRow);
		fireTableRowsUpdated(rowIndex, rowIndex);
	}

	public List<ContractorRowModel> getAllContractorRowModels() {
		return toList(rows);
	}

	public static List<ContractorRowModel> toList(List<Map<String, Object>> rows) {
		final List<ContractorRowModel> contrators = new ArrayList<>();
		for (final Map<String, Object> row : rows) {
			contrators.add(toContractorRowModel(row));
		}
		return contrators;
	}

	private int replaceOldRowWithUpdatedRow(Map<String, Object> oldRow, Map<String, Object> updatedRow) {
		int rowIndex = rows.indexOf(oldRow);
		rows.remove(oldRow);
		rows.add(rowIndex, updatedRow);
		return rowIndex;
	}

	private List<Map<String, Object>> toListOfMaps(List<ContractorRowModel> rowModels) {
		final List<Map<String, Object>> listOfMaps = new ArrayList<>();
		for (ContractorRowModel rowModel : rowModels) {
			listOfMaps.add(rowModel.toMap());
		}
		return listOfMaps;
	}

	private Map<String, Object> getRow(ContractorRowModel rowModel) {
		Map<String, Object> oldRow = new HashMap<>();

		for (Map<String, Object> row : rows) {
			if (isIdsSame(row, rowModel)) {
				oldRow = row;
				break;
			}
		}
		return oldRow;
	}

	private boolean isIdsSame(Map<String, Object> row, ContractorRowModel rowModel) {
		int rowId = (int) row.get(CONTRACTOR_ID_KEY);
		return rowId == rowModel.getContractorRowId();
	}

	private int getLastRowIndex() {
		return rows.size() - 1;
	}
}
