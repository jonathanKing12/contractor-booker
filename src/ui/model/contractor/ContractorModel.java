package ui.model.contractor;

import javax.swing.table.TableModel;

import transport.contractor.service.ContractorException;

/**
 * Represents a Model for handling contractors.
 */
public interface ContractorModel {

    /**
     * Gets a TableModel.
     * 
     * @return - the TableModel
     */
    TableModel getTableModel();

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
    void search(String name, String location) throws ContractorException;

    /**
     * Books the selected contractor to the specified customer.
     * 
     * @param customerId
     *            - the ID of the specified customer
     * @throws ContractorException
     *             if a ContractorException occurs
     */
    void bookSelectedContractorWithCustomer(String customerId) throws ContractorException;

    /**
     * Clears the results from the last search for Contractors.
     */
    void clearSearchResults();
}
