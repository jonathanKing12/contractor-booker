package ui.model.api;

import javax.swing.table.TableModel;

import transport.contractor.service.ContractorException;

public interface BookableModel {

    void search(String name, String location) throws ContractorException;

    void bookSelectedContractorWithCustomer(String customerId) throws ContractorException;

    TableModel getTableModel();

    void clearSearchResults();
}
