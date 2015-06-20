package client.view.settings;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextHolder {

	private JLabel messageLabel;
	private JTextField textField;

	TextHolder(String messageText) {
		messageLabel = new JLabel(messageText);
		textField = new JTextField();
	}

	TextHolder(String messageText, boolean isEditable) {
		this(messageText);
		textField.setEditable(isEditable);
	}

	void addComponents(JPanel panel) {
		panel.add(messageLabel);
		panel.add(textField);
	}

	void setText(String settings) {
		textField.setText(settings);
	}

	String getSettings() {
		return textField.getText();
	}
}
