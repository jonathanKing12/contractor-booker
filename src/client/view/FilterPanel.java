package client.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FilterPanel extends JPanel implements ActionListener {

	private ViewMerger merger;
	private JButton search;
	private JTextField nameTextField;
	private JTextField locationTextField;
	private JLabel nameLabel;
	private JLabel locationLabel;

	public FilterPanel() {
		merger = ViewMerger.getInstance();

		setUpLabels();
		setUpTextboxes();
		setUpSearchButton();
		this.setBackground(Color.cyan);
		addComponents();
	}

	private void setUpLabels() {
		nameLabel = new JLabel("Name");
		locationLabel = new JLabel("Location");
	}

	private void setUpTextboxes() {
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		locationTextField = new JTextField();
		locationTextField.setColumns(20);
		nameTextField.setToolTipText("enter subcontractors name");
		locationTextField.setToolTipText("enter subcontractors location");
	}

	private void setUpSearchButton() {
		search = new JButton("Search");
		search.addActionListener(this);
	}

	private void addComponents() {
		this.add(nameLabel);
		this.add(nameTextField);
		this.add(locationLabel);
		this.add(locationTextField);
		this.add(search);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		merger.search(nameTextField.getText().trim(), locationTextField.getText().trim());
	}
}
