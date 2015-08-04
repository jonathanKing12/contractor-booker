package ui.controller.api;

import javax.swing.JTable;

/**
 * Represents a Controller for handling contractors.
 */
public interface ContractorController {

    /**
     * Creates a new instance of JTable with the model.
     * 
     * @return the JTable
     */
    JTable getJTableWithModel();

    /**
     * Searches for all models that have the specified name and location.
     * 
     * @param name
     *            - the name
     * @param location
     *            - the location
     */
    void search(String name, String location);

    /**
     * Books the selected contractor to the specified customer.
     * 
     * @param customerId
     *            - the ID of the specified customer
     */
    void bookSelectedContractorWithCustomer(String customerId);

    /**
     * Sets the contractor book button based on the specified isEnabled state.
     * 
     * @param isEnabled
     *            - Sets to {@code true} if the button should be enabled
     */
    void enableBookContratorButton(boolean isEnabled);

    /**
     * Clears the results from the last search of Contractors from the model.
     */
    void clearModel();
}
