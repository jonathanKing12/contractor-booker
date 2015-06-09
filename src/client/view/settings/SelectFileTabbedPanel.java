package client.view.settings;

import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.JFileChooser.DIRECTORIES_ONLY;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.controller.ConfigurableController;

public class SelectFileTabbedPanel extends JPanel implements ActionListener {

	private String panelTitle;
	private JLabel message;
	private JButton browse;
	private JTextField textField;
	private ConfigurableController controller;
	private JFileChooser fileChooser;

	SelectFileTabbedPanel() {
		panelTitle = "choose location";
		fileChooser = getFileChooser();
		message = new JLabel("select location");
		// controller = new ConfigurationController();

		setUpTextField();
		setUpBrowseButton();
		addComponents();
	}

	public String getTtile() {
		return panelTitle;
	}

	public void setDataSourceLocation(String fileName) {
		textField.setText(fileName);
		controller.setFileName(fileName);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		int response = fileChooser.showDialog(this, "choose database file");

		if (isApproved(response)) {
			String dataSourceLocation = getNewDataSoureLocation();
			setDataSourceLocation(dataSourceLocation);
		}
	}

	private JFileChooser getFileChooser() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(DIRECTORIES_ONLY);
		return fileChooser;
	}

	private void setUpTextField() {
		textField = new JTextField();
		textField.setEditable(false);
	}

	private void setUpBrowseButton() {
		browse = new JButton("browse");
		browse.addActionListener(this);
	}

	private void addComponents() {
		this.add(message);
		this.add(textField);
		this.add(browse);
	}

	private boolean isApproved(int response) {
		return response == APPROVE_OPTION;
	}

	private String getNewDataSoureLocation() {
		return fileChooser.getSelectedFile().getAbsolutePath();
	}
}
