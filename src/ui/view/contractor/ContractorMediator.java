package ui.view.contractor;

import javax.swing.JTable;

import ui.controller.ControllerFactory;
import ui.controller.api.ContractorController;
import ui.view.common.MessageBoxPresenter;

public class ContractorMediator implements ContractorMediatorView {

    private static ContractorMediator INSTANCE;
    private ContractorButtonPanel contractorButtonPanel;
    private ContractorController contractorController;
    private MessageBoxPresenter messageBoxPresenter;
    private ContractorTablePanel contractorTablePanel;

    static {
        INSTANCE = new ContractorMediator();
    }

    private ContractorMediator() {
        ControllerFactory factory = new ControllerFactory();
        contractorController = factory.getContractorController(this);
        messageBoxPresenter = new MessageBoxPresenter();
    }

    /**
     * Returns the only instance of ContractorMediator.
     * 
     * @return the only instance of ContractorMediator
     */
    public static ContractorMediator getInstance() {
        return INSTANCE;
    }

    /**
     * Enables the book contractor button if the specified modelSelected is {@code true}
     * 
     * @param modelSelected
     *            - boolean indicating if the book contractor button should be disabled or enabled
     */
    @Override
    public void enableBookContratorButton(boolean enabled) {
        contractorButtonPanel.enableBookContratorButton(enabled);
    }

    /**
     * Displays a error message with the specified errorMessage and title.
     * 
     * @param errorMessage
     *            - the errorMessage
     * @param title
     *            - the title
     * 
     */
    @Override
    public void displayErrorMessageDialog(String errorMessage, String title) {
        messageBoxPresenter.displayErrorMessageDialog(errorMessage, title);
    }

    /**
     * Books the selected contractor with the specified customer.
     * 
     * <p>
     * Delegates to {@link ContractorController#bookSelectedContractorWithCustomer(customerId)}
     * </p>
     * 
     * @param customerId
     *            - the ID of the specified customer
     */
    public void bookSelectedContractorWithCustomer(String customerId) {
        contractorController.bookSelectedContractorWithCustomer(customerId);
    }

    /**
     * Searches for all contractors that names and location begin with the specified name and location. Updates the contractor message with the number
     * of contractors that were found in search.
     * 
     * <p>
     * Delegates to {@link ContractorController#search(name, location)} and {@link ContractorTablePanel#updateNumberOfContractorsMessage()}
     * </p>
     * 
     * @param name
     *            - the name
     * @param location
     *            - the location
     */
    public void search(String name, String location) {
        contractorController.search(name, location);
        contractorTablePanel.updateNumberOfContractorsMessage();
    }

    /**
     * Returns a JTable with the contractor model connected.
     * 
     * <p>
     * Delegates to {@link ContractorController#getJTableWithModel()}
     * </p>
     * 
     * @return the JTable with model
     */
    public JTable getJTableWithModel() {
        return contractorController.getJTableWithModel();
    }

    /**
     * Sets the contractor table panel to the specified contractorTablePanel.
     * 
     * @param contractorTablePanel
     *            - the contractorTablePanel
     */
    public void setContractorTablePanel(ContractorTablePanel contractorTablePanel) {
        this.contractorTablePanel = contractorTablePanel;
    }

    /**
     * Sets the contractor button panel to the specified contractorButtonPanel.
     * 
     * @param contractorButtonPanel
     *            - the contractorButtonPanel
     */
    public void addButtonPanel(ContractorButtonPanel contractorButtonPanel) {
        this.contractorButtonPanel = contractorButtonPanel;
    }

    /**
     * Returns {@code true} if the contractor table is empty.
     * 
     * @return {@code true} if the contractor table is empty
     */
    public boolean isContractorTableEmpty() {
        return contractorTablePanel.isEmpty();
    }

    /**
     * Clears the contractor table by removing all contractors from the model
     * 
     * <p>
     * Delegates to {@link ContractorController#clearModel()}
     * </p>
     */
    public void emptyTable() {
        contractorController.clearModel();
        contractorTablePanel.updateNumberOfContractorsMessage();
    }
}
