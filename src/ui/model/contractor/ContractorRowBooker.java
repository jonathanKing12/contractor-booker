package ui.model.contractor;

import static java.lang.Boolean.FALSE;
import transport.contractor.service.BusinessContractorService;
import transport.contractor.service.ContractorException;

public class ContractorRowBooker {

	private BusinessContractorService delegator;
	private ContractorTable tableModel;

	public ContractorRowBooker(ContractorTable model) {
		this.delegator = new BusinessContractorService();
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

	private void updateContractorRowModel(String customerId, ContractorRow rowModel)
			throws ContractorException {
		rowModel.setSelected(FALSE);
		rowModel.setCustomerId(customerId);

		delegator.bookContractor(rowModel.getContractor());
		tableModel.updateContractorRowModel(rowModel);
	}
}
