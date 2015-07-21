package ui.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.view.mergers.BookableMerger;
import ui.view.textwidget.TextWidget;
import ui.view.textwidget.TextWidgetBuilder;

public class FilterPanel extends JPanel implements ActionListener {

	private JButton search;
	private TextWidget name;
	private TextWidget location;

	public FilterPanel() {
		setUpTextHolders();
		setUpSearchButton();
		addComponents();
		this.setBackground(Color.cyan);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		BookableMerger merger = BookableMerger.getInstance();
		merger.search(name.getText(), location.getText());
	}

	private void setUpTextHolders() {
		name = createTextHolder("Name", 10);
		location = createTextHolder("Location", 20);
	}

	private TextWidget createTextHolder(String label, int numberOfColumns) {
		TextWidgetBuilder builder = new TextWidgetBuilder();
		return builder.addLabel(label).addNumberOfColumns(numberOfColumns).build();
	}

	private void setUpSearchButton() {
		search = new JButton("Search");
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
