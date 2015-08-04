package ui.model.contractor;

import javax.swing.table.TableModel;

import transport.contractor.service.ContractorException;
import ui.controller.api.ContractorController;

/**
 * Aa facade to searching and booking contractors in the model.
 */
public class ContractorRowFacade implements ContractorModel {

    private ContractorTable model;
    private ContractorRowBooker booker;
    private ContractorRowUpdator updator;
    private ContractorRowSearcher searcher;

    /**
     * Constructs a instance of ContractorRowFacade with the specified controller.
     * 
     * @param controller
     *            - the controller
     */
    public ContractorRowFacade(ContractorController controller) {
        model = new ContractorTable();

        searcher = new ContractorRowSearcher(model);
        booker = new ContractorRowBooker(model);
        updator = new ContractorRowUpdator(model, controller);

        model.addTableModelListener(updator);
    }

    /**
     * gets a ContractorTable
     * 
     * @return - the TableModel
     */
    @Override
    public TableModel getTableModel() {
        return model;
    }

    /**
     * Searches for all Contractors that have the specified name and location.
     * 
     * @param name
     *            - the name
     * @param location
     *            - the location
     * @throws ContractorException
     *             if a ContractorException occurs
     */
    @Override
    public void search(String name, String location) throws ContractorException {
        searcher.search(name, location);
    }

    /**
     * Books the selected contractor to the specified customer.
     * 
     * @param customerId
     *            - the ID of the specified customer
     * @throws ContractorException
     *             if a ContractorException occurs
     */
    @Override
    public void bookSelectedContractorWithCustomer(String customerId) throws ContractorException {
        booker.bookSelectedContractorWithCustomer(customerId);
    }

    /**
     * Clears the results from the last search for Contractors.
     */
    @Override
    public void clearSearchResults() {
        searcher.clearSearchResults();
    }
}
