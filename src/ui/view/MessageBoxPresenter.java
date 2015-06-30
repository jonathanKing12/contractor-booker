package ui.view;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Component;

public class MessageBoxPresenter {

	public String displayInputDialog(String message) {
		Component parent = getParent();
		return showInputDialog(parent, message);
	}

	public void displayErrorMessageBox(String message, String title) {
		Component parent = getParent();
		showMessageDialog(parent, message, title, ERROR_MESSAGE);
	}

	private Component getParent() {
		ParentTracker merger = ParentTracker.getInstance();
		return merger.getCurrentParent();
	}
}
