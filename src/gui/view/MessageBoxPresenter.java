package gui.view;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Component;

public class MessageBoxPresenter {

	private static MessageBoxPresenter messageBoxPresenter;
	private Component parent;

	private MessageBoxPresenter() {
	}

	public static MessageBoxPresenter getInstance() {
		if (messageBoxPresenter == null) {
			messageBoxPresenter = new MessageBoxPresenter();
		}
		return messageBoxPresenter;
	}

	public void SetParent(Component parent) {
		this.parent = parent;
	}

	public String displayInputDialog(String message) {
		return showInputDialog(parent, message);
	}

	public void displayErrorMessageBox(String message, String title) {
		showMessageDialog(parent, message, title, ERROR_MESSAGE);
	}
}
