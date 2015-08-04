package ui.view.contractor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.view.common.textwidget.TextWidget;
import ui.view.common.textwidget.TextWidgetBuilder;

/**
 * Represents the search panel.
 */
public class ContractorSearchPanel extends JPanel implements ActionListener {

    private static final int SIZE_20 = 20;
    private static final int SIZE_10 = 10;
    private static final String SEARCH_CONTRACTORS = "Search Contractors";
    private static final String LOCATION = "Location";
    private static final String NAME = "Name";
    private JButton search;
    private TextWidget name;
    private TextWidget location;

    public ContractorSearchPanel() {
        setUpTextHolders();
        setUpSearchButton();
        addComponents();
        this.setBackground(Color.cyan);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ContractorMediator merger = ContractorMediator.getInstance();
        merger.search(name.getText(), location.getText());
    }

    private void setUpTextHolders() {
        name = createTextHolder(NAME, SIZE_10);
        location = createTextHolder(LOCATION, SIZE_20);
    }

    private TextWidget createTextHolder(String label, int numberOfColumns) {
        TextWidgetBuilder builder = new TextWidgetBuilder();
        return builder.addLabel(label).addNumberOfColumns(numberOfColumns).build();
    }

    private void setUpSearchButton() {
        search = new JButton(SEARCH_CONTRACTORS);
        search.addActionListener(this);
    }

    private void addComponents() {
        addTextHolder(name);
        addTextHolder(location);
        this.add(search);
    }

    private void addTextHolder(TextWidget textHolder) {
        textHolder.addMessageLabelToPanel(this);
        textHolder.addTextFieldToPanel(this);
    }
}
