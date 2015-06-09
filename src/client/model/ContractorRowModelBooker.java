package client.model;

import static java.lang.Boolean.FALSE;

import java.io.IOException;

import transport.contractor.ContractorException;
import transport.network.LocalDelegator;

public class ContractorRowModelBooker {

	private LocalDelegator delegator;
	private ContractorTableModel tableModel;

	public ContractorRowModelBooker(ContractorTableModel model, LocalDelegator delegator) {
		this.delegator = delegator;
		this.tableModel = model;
	}

	public void bookSelectedContractorWithCustomer(String customerId) throws IOException, ContractorException {

		for (ContractorRowModel rowModel : tableModel.getAllContractorRowModels()) {
			if (isSelected(rowModel)) {
				updateContractorRowModel(customerId, rowModel);
				break;
			}
		}
	}

	private boolean isSelected(ContractorRowModel rowModel) {
		return rowModel.isSelected();
	}

	private void updateContractorRowModel(String customerId, ContractorRowModel rowModel) throws IOException, ContractorException {
		rowModel.setSelected(FALSE);
		rowModel.setCustomerId(customerId);

		delegator.updateContractor(rowModel.getContractor());
		tableModel.updateContractorRowModel(rowModel);
	}
}
