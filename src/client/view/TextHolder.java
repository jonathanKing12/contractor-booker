package client.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextHolder {

	private JLabel messageLabel;
	private JTextField textField;

	public TextHolder(String messageText) {
		messageLabel = new JLabel(messageText);
		textField = new JTextField();
	}

	public TextHolder(String messageText, boolean isEditable) {
		this(messageText);
		textField.setEditable(isEditable);
	}

	public void addComponents(JPanel panel) {
		panel.add(messageLabel);
		panel.add(textField);
	}

	public void setText(String text) {
		// text = (text != null) ? text.trim() : text;
		textField.setText(text);
	}

	public String getSettings() {
		String text = textField.getText();
		return (text.isEmpty()) ? text : text.trim();
	}
}
