package ui.model.contractor;

import static java.lang.Boolean.FALSE;
import transport.contractor.ContractorException;
import transport.delegaters.Delegator;

public class ContractorRowBooker {

	private Delegator delegator;
	private ContractorTable tableModel;

	public ContractorRowBooker(ContractorTable model, Delegator delegator) {
		this.delegator = delegator;
		this.tableModel = model;
	}

	public void bookSelectedContractorWithCustomer(String customerId) throws ContractorException {

		for (ContractorRow rowModel : tableModel.getAllContractorRowModels()) {
			if (isSelected(rowModel)) {
				updateContractorRowModel(customerId, rowModel);
				break;
			}
		}
	}

	private boolean isSelected(ContractorRow rowModel) {
		return rowModel.isSelected();
	}

	private void updateContractorRowModel(String customerId, ContractorRow rowModel) throws ContractorException {
		rowModel.setSelected(FALSE);
		rowModel.setCustomerId(customerId);

		delegator.updateContractor(rowModel.getContractor());
		tableModel.updateContractorRowModel(rowModel);
	}
}
