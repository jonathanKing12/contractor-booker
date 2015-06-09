package client.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import transport.contractor.Contractor;
import transport.contractor.ContractorException;
import transport.network.LocalDelegator;

public class ContractorRowModelSearcher {

	private LocalDelegator delegator;
	private ContractorTableModel model;

	public ContractorRowModelSearcher(ContractorTableModel tableModel, LocalDelegator delegator) {
		this.delegator = delegator;
		this.model = tableModel;
	}

	public void search(String name, String location) throws IOException, ContractorException {

		List<Contractor> contractors = delegator.getContractors(name, location);
		List<ContractorRowModel> rowModels = new ArrayList<>();

		for (Contractor contractor : contractors) {
			ContractorRowModel rowModel = new ContractorRowModel(contractor);
			rowModels.add(rowModel);
		}
		model.setContractorRowModels(rowModels);
	}
}
