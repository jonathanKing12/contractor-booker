package client.view;

import static java.awt.Color.cyan;
import static javax.swing.SwingConstants.CENTER;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class TablePanel extends JPanel {

	private JTable table;
	private JScrollPane scrollPane;
	private ViewMerger merger;

	public TablePanel() {
		this.setLayout(new BorderLayout());
		merger = ViewMerger.getInstance();
		table = getTable();
		// table.setFillsViewportHeight(Boolean.TRUE);
		scrollPane = new JScrollPane(table);
		// scrollPane.setf(Boolean.TRUE);
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
		renderer.setHorizontalAlignment(CENTER);
		column.setCellRenderer(renderer);
	}

	// public void resizeTable(int width, int height) {
	// setMainScreenSize(width, height);
	// updateUI();
	// }
}
