package client.view;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MessageBoxCreator {

	public static String displayInputDialog(JFrame parentFrame, String message) {
		return JOptionPane.showInputDialog(parentFrame, "enter customer ID");
	}

	public static void displayMessageBox(JFrame parentFrame, String message) {
		JOptionPane.showMessageDialog(parentFrame, message, "Invalid Customer ID", ERROR_MESSAGE);
	}

}
