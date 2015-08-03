package ui.view.common;

public interface MessageBoxPresenterView {

    /**
     * Displays a error message with the specified errorMessage and title.
     * 
     * @param errorMessage
     *            - the errorMessage
     * @param title
     *            - the title
     */
    void displayErrorMessageDialog(String errorMessage, String title);
}
