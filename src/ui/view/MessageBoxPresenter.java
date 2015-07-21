package ui.view;

import static javax.swing.JOptionPane.*;

import java.awt.Component;

public class MessageBoxPresenter {

    private static final int YES_BUTTON_SELECTED = 0;

    public String displayInputDialog(String message) {
        Component parent = getParent();
        return showInputDialog(parent, message);
    }

    public void displayErrorMessageBox(String message, String title) {
        Component parent = getParent();
        showMessageDialog(parent, message, title, ERROR_MESSAGE);
    }

    public boolean dispayYesNoDialogBox(String message, String title) {

        Component parent = getParent();
        int option = showConfirmDialog(parent, message, title, YES_NO_OPTION);
        return option == YES_BUTTON_SELECTED;
    }

    private Component getParent() {
        ParentTracker parentTracker = ParentTracker.getInstance();
        return parentTracker.getCurrentParent();
    }
}
