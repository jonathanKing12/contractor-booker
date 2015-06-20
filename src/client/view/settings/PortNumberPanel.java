package client.view.settings;

import javax.swing.JPanel;

public class PortNumberPanel extends JPanel {

	private TextHolder textHolder;

	PortNumberPanel() {
		textHolder = new TextHolder("select port number");
		textHolder.addComponents(this);
	}

	void setPortNumber(String portNumber) {
		textHolder.setText(portNumber);
	}

	String getPortNumber() {
		return textHolder.getSettings();
	}
}
