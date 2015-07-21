package ui.view;

import static java.awt.Color.cyan;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import ui.view.mergers.BookableMerger;

public class TablePanel extends JPanel {

	private JTable table;
	private JScrollPane scrollPane;
	private BookableMerger merger;

	public TablePanel() {
		merger = BookableMerger.getInstance();
		merger.setTablePanel(this);
		this.setLayout(new BorderLayout());
		table = getTable();

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				int row = table.isRowSelected(e.getFirstIndex()) ? e.getFirstIndex() : e
						.getLastIndex();

				// System.out.println(row + " " + table.isColumnSelected(0));
				if (!table.isColumnSelected(0)) {
					table.clearSelection();
				}

			}
		});
		table.setFocusable(Boolean.FALSE);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.CENTER);
		this.setBackground(cyan);

	}

	private JTable getTable() {
		JTable table = merger.getJTableWithModel();
		allignColumnsTextToCentre(table);
		return table;
	}

	private void allignColumnsTextToCentre(JTable table) {
		TableColumnModel columnModel = table.getColumnModel();

		for (int columnIndex = 1; columnIndex < columnModel.getColumnCount(); columnIndex++) {
			TableColumn column = columnModel.getColumn(columnIndex);
			allignColumnTextToCentre(column);
		}
	}

	private void allignColumnTextToCentre(TableColumn column) {
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.LEFT);
		column.setCellRenderer(renderer);
	}

	public boolean clear() {
		if (table.getRowCount() == 0) {
			return TRUE;
		}

		if (!isOkayToClear()) {
			return FALSE;
		}

		merger.clearRowModels();
		return TRUE;
	}

	private boolean isOkayToClear() {
		String message = "your search results will be reset do you want to continue";
		MessageBoxPresenter box = new MessageBoxPresenter();
		return box.dispayYesNoDialogBox(message);
	}

	public void clearSelection() {
		table.clearSelection();

	}
}
