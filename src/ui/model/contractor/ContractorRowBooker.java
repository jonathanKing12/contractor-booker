package ui.model.contractor;

import static java.lang.Boolean.FALSE;
import transport.contractor.service.BusinessContractorService;
import transport.contractor.service.ContractorException;

/**
 * Used for booking ContractorRow instances.
 */
public class ContractorRowBooker {

    private BusinessContractorService delegator;
    private ContractorTable contractorTable;

    /**
     * Constructs a instance of ContractorRowBooker with the specified contractorTable.
     * 
     * @param contractorTable
     *            - the contractorTable
     */
    public ContractorRowBooker(ContractorTable contractorTable) {
        this.delegator = new BusinessContractorService();
        this.contractorTable = contractorTable;
    }

    /**
     * Books the selected contractor to the specified customer. If no contractor is selected then no contractor get booked.
     * 
     * @param customerId
     *            - the ID of the specified customer
     * @throws ContractorException
     *             if a ContractorException occurs
     */
    public void bookSelectedContractorWithCustomer(String customerId) throws ContractorException {

        for (ContractorRow rowModel : contractorTable.getAllContractorRowModels()) {
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
        contractorTable.updateContractorRowModel(rowModel);
    }
}
