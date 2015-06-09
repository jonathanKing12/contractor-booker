package client.view;

import static java.awt.Color.cyan;
import static javax.swing.SwingConstants.CENTER;

import java.awt.Dimension;

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

	public TablePanel(int perferedWidth, int height) {
		merger = ViewMerger.getInstance();
		table = getTable();

		scrollPane = new JScrollPane(table);

		this.add(scrollPane);
		this.setBackground(cyan);
		setMainScreenSize(scrollPane, perferedWidth, height);
	}

	private void setMainScreenSize(JScrollPane scrollPane, int width, int height) {
		int oneEight = width / 8;
		int fiveEights = oneEight * 7;

		int sevenTenths = (height / 10) * 7;
		scrollPane.setPreferredSize(new Dimension(fiveEights, sevenTenths - 10));
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

	public void resizeTable(int width, int height) {
		// table.updateUI();
		System.out.println("yes");
		// this.updateUI();
		// scrollPane.se
		this.setMainScreenSize(scrollPane, width, height);
		this.updateUI();

	}
}
