package ui.view.contractor;

import ui.view.common.MessageBoxPresenterView;

/**
 * Represents a instance of Mediating Contractor operations.
 */
public interface ContractorMediatorView extends MessageBoxPresenterView {

    /**
     * Displays a error message with the specified errorMessage and title.
     * 
     * @param errorMessage
     *            - the errorMessage
     * @param title
     *            - the title
     */
    @Override
    void displayErrorMessageDialog(String errorMessage, String title);

    /**
     * Enables the book contractor button if the specified modelSelected is {@code true}
     * 
     * @param modelSelected
     *            - boolean indicating if the book contractor button should be disabled or enabled
     */
    void enableBookContratorButton(boolean modelSelected);
}
