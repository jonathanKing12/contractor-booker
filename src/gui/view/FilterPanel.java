package gui.view;

import gui.view.mergers.BookableMerger;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class FilterPanel extends JPanel implements ActionListener {

	private JButton search;
	private TextHolder name;
	private TextHolder location;

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

	private TextHolder createTextHolder(String label, int numberOfColumns) {
		TextHolderBuilder builder = new TextHolderBuilder();
		return builder.addLabel(label).addNumberOfColumns(numberOfColumns).build();
	}

	private void setUpSearchButton() {
		search = new JButton("Search");
		search.addActionListener(this);
	}

	private void addComponents() {
		// name.addComponents();
		// location.addComponents(this);
		this.add(search);
	}
}
