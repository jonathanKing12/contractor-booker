package ui.view.common;

import static javax.swing.JOptionPane.*;

import java.awt.Component;

public class MessageBoxPresenter implements MessageBoxPresenterView {

    private static final int YES_BUTTON_SELECTED = 0;
    private ParentTracker parentTracker;

    /**
     * Constructs a instance of MessageBoxPresenter
     */
    public MessageBoxPresenter() {
        parentTracker = ParentTracker.getInstance();
    }

    /**
     * Displays a error message with the specified errorMessage and title.
     * 
     * @param errorMessage
     *            - the errorMessage
     * @param title
     *            - the title
     */
    @Override
    public void displayErrorMessageDialog(String errorMessage, String title) {
        Component parent = parentTracker.getParent();
        showMessageDialog(parent, errorMessage, title, ERROR_MESSAGE);
    }

    /**
     * Displays a input dialog with the specified message.
     * 
     * @param message
     *            - the message
     * @return what the user has entered
     */
    public String displayInputDialog(String message) {
        Component parent = parentTracker.getParent();
        return showInputDialog(parent, message);
    }

    /**
     * Displays a Yes/No dialog with the specified message and title.
     * 
     * @param message
     *            - the message
     * @param title
     *            - the title
     * @return {@code true} if the user has selected the yes button.
     */
    public boolean dispayYesNoDialog(String message, String title) {
        Component parent = parentTracker.getParent();
        int option = showConfirmDialog(parent, message, title, YES_NO_OPTION);
        return option == YES_BUTTON_SELECTED;
    }
}
