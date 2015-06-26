package gui.view.settings.panels;

import gui.view.TextHolder;
import gui.view.TextHolderBuilder;

import javax.swing.JPanel;

public class PortNumberPanel extends JPanel {

	private TextHolder textHolder;

	public PortNumberPanel() {
		textHolder = createTextHolder();
		textHolder.addComponents();
		this.add(textHolder);
	}

	public void setPortNumber(String portNumber) {
		textHolder.setText(portNumber);
	}

	public String getPortNumber() {
		return textHolder.getText();
	}

	private TextHolder createTextHolder() {
		TextHolderBuilder builder = new TextHolderBuilder();
		return builder.addLabel("Select port number").addNumberOfColumns(10).build();
	}
}
