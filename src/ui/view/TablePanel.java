package ui.view;

import static java.awt.Color.cyan;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import static javax.swing.SwingConstants.LEFT;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.table.*;

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
        scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setBackground(cyan);
    }

    private JTable getTable() {
        JTable table = merger.getJTableWithModel();
        allignColumnsTextToCentre(table);
        table.setFocusable(FALSE);
        table.setSelectionMode(SINGLE_SELECTION);
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
        renderer.setHorizontalAlignment(LEFT);
        column.setCellRenderer(renderer);
    }

    public boolean clear() {
        if (table.getRowCount() == 0) {
            return TRUE;
        }

        if (!isOkayToClear()) {
            return FALSE;
        }

        merger.clearSearchResults();
        return TRUE;
    }

    private boolean isOkayToClear() {
        String message = "your search results will be reset do you want to continue";
        MessageBoxPresenter box = new MessageBoxPresenter();
        return box.dispayYesNoDialogBox(message, "title");
    }
}
