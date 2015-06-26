package gui.view;

import static java.awt.Color.cyan;
import static javax.swing.SwingConstants.CENTER;
import gui.view.mergers.BookableMerger;

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

	public TablePanel() {
		this.setLayout(new BorderLayout());
		table = getTable();
		scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.CENTER);
		this.setBackground(cyan);
	}

	private JTable getTable() {
		BookableMerger merger = BookableMerger.getInstance();
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
}
