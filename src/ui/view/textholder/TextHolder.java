package ui.view.textholder;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextHolder {

	private JLabel messageLabel;
	private JTextField textField;

	public TextHolder(String messageText, boolean isEditable, int numberOfColumns) {
		messageLabel = new JLabel(messageText);
		textField = new JTextField(numberOfColumns);
		textField.setEditable(isEditable);
		textField.setColumns(numberOfColumns);
	}

	public void setText(String text) {
		textField.setText(text);
	}

	public String getText() {
		String text = textField.getText();
		return text.trim();
	}

	public void addMessageLabelToPanel(JPanel panel) {
		panel.add(messageLabel);
	}

	public void addTextFieldToPanel(JPanel panel) {
		panel.add(textField);
	}
}
