package client.model;

import java.io.IOException;

import javax.swing.table.TableModel;

import transport.contractor.ContractorException;
import transport.network.LocalDelegator;
import client.controller.BookableController;

public class ContractorModel implements BookableModel {

	private ContractorRowModelSearcher searcher;
	private ContractorRowModelSelectionUpdator updator;
	private ContractorRowModelBooker booker;
	private ContractorTableModel model;

	public ContractorModel(BookableController controller) throws IOException {
		model = new ContractorTableModel();
		LocalDelegator delegator = new LocalDelegator();

		searcher = new ContractorRowModelSearcher(model, delegator);
		booker = new ContractorRowModelBooker(model, delegator);
		updator = new ContractorRowModelSelectionUpdator(model, controller);

		model.addTableModelListener(updator);
	}

	@Override
	public TableModel getTableModel() {
		return model;
	}

	@Override
	public void search(String name, String location) throws IOException, ContractorException {
		searcher.search(name, location);
	}

	@Override
	public void bookSelectedContractorWithCustomer(String customerId) throws IOException, ContractorException {
		booker.bookSelectedContractorWithCustomer(customerId);
	}

}
