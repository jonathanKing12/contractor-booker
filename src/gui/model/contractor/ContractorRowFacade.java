package gui.model.contractor;

import gui.controller.api.BookableController;
import gui.model.api.BookableModel;

import javax.swing.table.TableModel;

import transport.contractor.ContractorException;
import transport.local.Delegator;
import transport.local.RemoteDelegator;

public class ContractorRowFacade implements BookableModel {

	private ContractorRowSearcher searcher;
	private ContractorRowUpdator updator;
	private ContractorRowBooker booker;
	private ContractorTable model;

	public ContractorRowFacade(BookableController controller) {
		model = new ContractorTable();
		Delegator delegator = new RemoteDelegator();

		searcher = new ContractorRowSearcher(model, delegator);
		booker = new ContractorRowBooker(model, delegator);
		updator = new ContractorRowUpdator(model, controller);

		model.addTableModelListener(updator);
	}

	@Override
	public TableModel getTableModel() {
		return model;
	}

	@Override
	public void search(String name, String location) throws ContractorException {
		searcher.search(name, location);
	}

	@Override
	public void bookSelectedContractorWithCustomer(String customerId) throws ContractorException {
		booker.bookSelectedContractorWithCustomer(customerId);
	}
}
