package gui.model.contractor;

import java.util.ArrayList;
import java.util.List;

import transport.contractor.Contractor;
import transport.contractor.ContractorException;
import transport.local.Delegator;

public class ContractorRowSearcher {

	private Delegator delegator;
	private ContractorTable model;

	public ContractorRowSearcher(ContractorTable tableModel, Delegator delegator) {
		this.delegator = delegator;
		this.model = tableModel;
	}

	public void search(String name, String location) throws ContractorException {

		List<Contractor> contractors = delegator.getContractors(name, location);
		List<ContractorRow> rowModels = new ArrayList<>();

		for (Contractor contractor : contractors) {
			ContractorRow rowModel = new ContractorRow(contractor);
			rowModels.add(rowModel);
		}
		model.setContractorRowModels(rowModels);
	}
}
