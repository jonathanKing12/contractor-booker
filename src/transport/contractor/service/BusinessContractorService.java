package transport.contractor.service;

import java.util.List;

import transport.contractor.Contractor;

/**
 * Delegates requests to a ContrctorService instance.
 */
public class BusinessContractorService {

    private ContractorService contractorService;

    /**
     * Constructs a BusinessContractorService instance
     */
    public BusinessContractorService() {
        ContractorServiceLocator locator = ContractorServiceLocator.getInstance();
        contractorService = locator.getContractorService();
    }

    /**
     * Searches for all contractors who's name and location both match the beginning of the specified name and location.
     * 
     * @param name
     *            - the value used to match against the beginning of all contractor names. If null then all contractors will match
     * @param location
     *            - the value used to match against the beginning of all contractor locations. If null then all contractors will match
     * @return a List of contractors who name and location matched the specified values.
     * @throws ContractorException
     *             - if the search fails
     */
    public List<Contractor> getContractors(String name, String location) throws ContractorException {
        return contractorService.getContractors(name, location);
    }

    /**
     * Books the specified contractor
     * 
     * @param contractor
     *            - the contractor that is to be booked
     * @throws ContractorException
     *             - if the booking has failed
     */
    public void bookContractor(Contractor contractor) throws ContractorException {
        contractorService.bookContractor(contractor);
    }
}
