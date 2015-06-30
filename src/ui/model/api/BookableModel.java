package ui.model.api;

import java.io.IOException;

import javax.swing.table.TableModel;

import transport.contractor.ContractorException;

public interface BookableModel {

	void search(String name, String location) throws IOException, ContractorException;

	void bookSelectedContractorWithCustomer(String customerId) throws IOException, ContractorException;

	TableModel getTableModel();
}
