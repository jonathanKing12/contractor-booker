package ui.model.contractor;

import javax.swing.table.TableModel;

import transport.contractor.service.ContractorException;
import ui.controller.api.BookableController;
import ui.model.api.BookableModel;

public class ContractorRowFacade implements BookableModel {

	private ContractorTable model;
	private ContractorRowBooker booker;
	private ContractorRowUpdator updator;
	private ContractorRowSearcher searcher;

	public ContractorRowFacade(BookableController controller) {
		model = new ContractorTable();

		searcher = new ContractorRowSearcher(model);
		booker = new ContractorRowBooker(model);
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

	@Override
	public void clear() {
		searcher.clearSearch();
	}
}
