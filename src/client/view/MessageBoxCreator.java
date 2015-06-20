package client.view;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import javax.swing.JFrame;

public class MessageBoxCreator {

	private JFrame parentFrame;

	MessageBoxCreator(JFrame parentFrame) {
		this.parentFrame = parentFrame;
	}

	String displayInputDialog(String message) {
		return showInputDialog(parentFrame, message);
	}

	void displayErrorMessageBox(String message, String title) {
		showMessageDialog(parentFrame, message, title, ERROR_MESSAGE);
	}
}
