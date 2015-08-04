package ui.model.contractor;

import java.util.ArrayList;
import java.util.List;

import transport.contractor.Contractor;
import transport.contractor.service.BusinessContractorService;
import transport.contractor.service.ContractorException;

/**
 * Used for Searching for ContractorRow instances.
 */
public class ContractorRowSearcher {

    private BusinessContractorService delegator;
    private ContractorTable contractorTable;

    /**
     * Constructs a instance of ContractorRowSearcher with the specified contractorTable
     * 
     * @param contractorTable
     *            - the contractorTable
     */
    public ContractorRowSearcher(ContractorTable contractorTable) {
        this.delegator = new BusinessContractorService();
        this.contractorTable = contractorTable;
    }

    /**
     * Searches for all Contractors that have the specified name and location.
     * 
     * <p>
     * Delegates to {@link BusinessContractorService#getContractors(name, location)}. Replaces the contents of this objects's contractorTable instance
     * with what is returned from that getContractors method
     * </p>
     * 
     * @param name
     *            - the name
     * @param location
     *            - the location
     * @throws ContractorException
     *             if a ContractorException occurs
     */
    public void search(String name, String location) throws ContractorException {

        List<Contractor> contractors = delegator.getContractors(name, location);
        List<ContractorRow> rowModels = convertToContractorRowModels(contractors);
        contractorTable.setContractorRow(rowModels);
    }

    /**
     * Clears the model
     */
    public void clearSearchResults() {
        contractorTable.clear();
    }

    private List<ContractorRow> convertToContractorRowModels(List<Contractor> contractors) {
        List<ContractorRow> rowModels = new ArrayList<>();

        for (Contractor contractor : contractors) {
            ContractorRow rowModel = new ContractorRow(contractor);
            rowModels.add(rowModel);
        }
        return rowModels;
    }
}
