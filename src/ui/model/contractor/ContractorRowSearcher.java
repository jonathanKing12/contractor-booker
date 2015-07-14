package ui.model.contractor;

import java.util.ArrayList;
import java.util.List;

import transport.contractor.Contractor;
import transport.contractor.service.BusinessContractorService;
import transport.contractor.service.ContractorException;

public class ContractorRowSearcher {

	private BusinessContractorService delegator;
	private ContractorTable model;

	public ContractorRowSearcher(ContractorTable tableModel) {
		this.delegator = new BusinessContractorService();
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

	public void clearSearch() {
		model.clearAll();
	}
}
