package ui.model.contractor;

import static constants.Constants.CONTRACTOR_ID_KEY;
import static constants.Constants.FIRST_ROW_INDEX;
import static ui.model.contractor.Column.*;

import java.util.*;

import javax.swing.table.AbstractTableModel;

/**
 * Represents the contractor table.
 */
public class ContractorTable extends AbstractTableModel {

    private List<Map<String, Object>> rows;
    private Column[] columns;
    private ContractorRowParser contractorRowParser = new ContractorRowParser();

    /**
     * Constructs a instance of ContractorTable.
     */
    public ContractorTable() {
        columns = new Column[] { SELECTED, NAME, LOCATION, SPECIALTIES, SIZE, RATE, OWNER };
        contractorRowParser = new ContractorRowParser();
        rows = new ArrayList<>();
    }

    /**
     * Returns the number of columns.
     * 
     * @return the number of columns
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * Returns the name of the specified column
     * 
     * @param columnIndex
     *            - the index of the specified column
     * 
     * @return the name of the column
     */
    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex].getName();
    }

    /**
     * Returns the number of rows.
     * 
     * @return the number of rows
     */
    @Override
    public int getRowCount() {
        return rows.size();
    }

    /**
     * Returns the Class type of the specified column
     * 
     * @param columnIndex
     *            - the index of the specified column
     * 
     * @return the Class type of the column
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columns[columnIndex].getClassType();
    }

    /**
     * Sets the specified cell the specified value then invokes the {@link #fireTableCellUpdated(rowIndex, columnIndex)} method.
     * 
     * @param value
     *            - the value
     * @param rowIndex
     *            - the row index position of the specified cell
     * @param columnIndex
     *            - the column index position of the specified cell
     */
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        final Map<String, Object> row = rows.get(rowIndex);
        final String columnName = getColumnName(columnIndex);
        row.put(columnName, value);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    /**
     * Returns the value at the specified cell.
     * 
     * @param rowIndex
     *            - the row index position of the specified cell
     * @param columnIndex
     *            - the column index position of the specified cell
     * @return the specified cell value
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        final Map<String, Object> row = rows.get(rowIndex);
        final String columnName = getColumnName(columnIndex);
        return row.get(columnName);
    }

    /**
     * Returns {@code true} if the specified cell is editable.
     * 
     * @param rowIndex
     *            - the row index position of the specified cell
     * @param columnIndex
     *            - the column index position of the specified cell
     * @return {@code true} if the specified cell is editable
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columns[columnIndex] == SELECTED;
    }

    /**
     * Sets this object rows with the specified rowModels then invokes the {@link #fireTableRowsInserted(firstRowIndex, lastRowIndex)} method.
     * 
     * <p>
     * If the rows were not empty, then before the setting this object rows the {@link #fireTableRowsDeleted(firstRowIndex, lastRowIndex)} method is
     * invoked.
     * </p>
     * 
     * @param contractorRows
     *            - the contractorRows
     */
    public void setContractorRow(List<ContractorRow> contractorRows) {
        if (!rows.isEmpty()) {
            fireTableRowsDeleted(FIRST_ROW_INDEX, getLastRowIndex());
        }

        this.rows = toListOfMaps(contractorRows);
        fireTableRowsInserted(FIRST_ROW_INDEX, getLastRowIndex());
    }

    /**
     * Returns the ContractorRow at the specified row.
     * 
     * @param rowIndex
     *            the index of the specified row
     * @return the ContractorRow at the specified row.
     */
    public ContractorRow getModel(int rowIndex) {
        Map<String, Object> row = rows.get(rowIndex);
        return contractorRowParser.toContractorRow(row);
    }

    /**
     * 
     * @param rowModel
     */
    public void updateContractorRowModel(ContractorRow rowModel) {
        Map<String, Object> updatedRow = rowModel.toMap();
        Map<String, Object> oldRow = getRow(rowModel);
        int rowIndex = replaceOldRowWithUpdatedRow(oldRow, updatedRow);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public List<ContractorRow> getAllContractorRowModels() {
        return toList(rows);
    }

    public List<ContractorRow> toList(List<Map<String, Object>> rows) {
        final List<ContractorRow> contrators = new ArrayList<>();
        for (final Map<String, Object> row : rows) {
            contrators.add(contractorRowParser.toContractorRow(row));
        }
        return contrators;
    }

    private int replaceOldRowWithUpdatedRow(Map<String, Object> oldRow,
            Map<String, Object> updatedRow) {
        int rowIndex = rows.indexOf(oldRow);
        rows.remove(oldRow);
        rows.add(rowIndex, updatedRow);
        return rowIndex;
    }

    private List<Map<String, Object>> toListOfMaps(List<ContractorRow> rowModels) {
        final List<Map<String, Object>> listOfMaps = new ArrayList<>();
        for (ContractorRow rowModel : rowModels) {
            listOfMaps.add(rowModel.toMap());
        }
        return listOfMaps;
    }

    private Map<String, Object> getRow(ContractorRow rowModel) {
        Map<String, Object> oldRow = new HashMap<>();

        for (Map<String, Object> row : rows) {
            if (isIdsSame(row, rowModel)) {
                oldRow = row;
                break;
            }
        }
        return oldRow;
    }

    private boolean isIdsSame(Map<String, Object> row, ContractorRow rowModel) {
        int rowId = (int) row.get(CONTRACTOR_ID_KEY);
        return rowId == rowModel.getContractorRowId();
    }

    private int getLastRowIndex() {
        return rows.size() - 1;
    }

    public void clear() {
        int indexOfLastRowRemoved = rows.size() - 1;
        rows.clear();
        fireTableRowsDeleted(FIRST_ROW_INDEX, indexOfLastRowRemoved);

    }
}
