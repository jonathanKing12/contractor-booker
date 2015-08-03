package ui.controller;

import javax.swing.JTable;

import transport.contractor.service.ContractorException;
import ui.controller.api.ContractorController;
import ui.model.contractor.ContractorModel;
import ui.model.contractor.ContractorRowFacade;
import ui.view.contractor.ContractorMediatorView;

public class ContractorTableController implements ContractorController {

    private ContractorModel contractorModel;
    private ContractorMediatorView contractorView;

    /**
     * Constructs a instance of ContractorTableController with the specified ContractorView.
     * 
     * @param contractorView
     *            - the contractorView
     */
    public ContractorTableController(ContractorMediatorView contractorView) {
        this.contractorView = contractorView;
        contractorModel = new ContractorRowFacade(this);
    }

    /**
     * Creates a new instance of JTable with a {@link ContractorModel}.
     * 
     * @return the JTable
     */
    @Override
    public JTable getJTableWithModel() {
        return new JTable(contractorModel.getTableModel());
    }

    /**
     * Searches for all models that have the specified name and location.
     * <p>
     * Delegates the call to {@link ContractorModel#search(name, location)}. If that method throws a {@link ContractorException} then the
     * {@link ContractorMediatorView#displayErrorMessage(message, title)} is invoked.
     * </p>
     * 
     * @param name
     *            - the name
     * @param location
     *            - the location
     */
    @Override
    public void search(String name, String location) {
        try {
            contractorModel.search(name, location);
        } catch (ContractorException e) {
            contractorView.displayErrorMessageDialog(e.getMessage(), "Error when searching");
        }
    }

    /**
     * Books the selected contractor to the specified customer.
     * 
     * <p>
     * Delegates the call to {@link ContractorModel#bookSelectedContractorWithCustomer(customerId)}. If that method throws a
     * {@link ContractorException} then the {@link ContractorMediatorView#displayErrorMessage(message, title)} is invoked.
     * </p>
     * 
     * @param customerId
     *            - the ID of the specified customer
     */
    @Override
    public void bookSelectedContractorWithCustomer(String customerId) {
        try {
            contractorModel.bookSelectedContractorWithCustomer(customerId);
        } catch (ContractorException e) {
            contractorView.displayErrorMessageDialog(e.getMessage(), "Error when booking");
        }
    }

    /**
     * Sets the contractor book button based on the specified isEnabled state.
     * <p>
     * Delegates the call to {@link ContractorMediatorView#enableBookContratorButton(isEnabled)}
     * </p>
     * 
     * @param isEnabled
     *            - Sets to {@code true} if the button should be enabled
     */
    @Override
    public void enableBookContratorButton(boolean isEnabled) {
        contractorView.enableBookContratorButton(isEnabled);
    }

    /**
     * Clears the results from the last search of Contractors from the model.
     * <p>
     * Delegates the call to {@link ContractorModel#clearSearchResults()}
     * </p>
     */
    @Override
    public void clearModel() {
        contractorModel.clearSearchResults();
    }
}
