package ui.view.contractor;

import static java.lang.Boolean.FALSE;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import static javax.swing.SwingConstants.LEFT;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;
import javax.swing.table.*;

public class ContractorTablePanel extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;
    private ContractorMediator mediator;
    private JLabel contractorMessageCount;

    /**
     * Constructs a instance of TablePanel.
     */
    public ContractorTablePanel() {
        mediator = ContractorMediator.getInstance();
        mediator.setContractorTablePanel(this);

        this.setLayout(new BorderLayout());
        table = getTable();
        scrollPane = new JScrollPane(table);
        contractorMessageCount = new JLabel("0 Contractors");
        this.add(contractorMessageCount, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setBackground(Color.cyan);
    }

    /**
     * Returns {@code true} if the table is empty.
     * 
     * @return {@code true} if the table is empty
     */
    boolean isEmpty() {
        return table.getRowCount() == 0;
    }

    /**
     * Changes the contractor count message to display the number of contractors that are displayed in the table.
     */
    void updateNumberOfContractorsMessage() {
        String message = createNumberOfContractorsMessage();
        contractorMessageCount.setText(message);
    }

    private String createNumberOfContractorsMessage() {
        int numberOfRows = table.getRowCount();
        String message = (numberOfRows == 1) ? "Contractor" : "Contractors";

        StringBuilder builder = new StringBuilder();
        builder.append(numberOfRows);
        builder.append(" ");
        builder.append(message);
        return builder.toString();
    }

    private JTable getTable() {
        JTable table = mediator.getJTableWithModel();
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
}
