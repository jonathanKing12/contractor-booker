package transport.contractor.service;

import java.util.List;

import suncertify.db.RecordNotFoundException;
import suncertify.db.access.DataAccessException;
import transport.contractor.Contractor;

public class ContractorServiceFacadeWrapper implements ContractorService {

    private ContractorSerivceFacade contractorFacade;

    /**
     * Creates a ContractorServiceFacadeWrapper instance
     */
    public ContractorServiceFacadeWrapper() {
        this.contractorFacade = new ContractorSerivceFacade();
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
    @Override
    public List<Contractor> getContractors(String name, String location) throws ContractorException {
        try {
            return contractorFacade.getContractors(name, location);
        } catch (RecordNotFoundException | DataAccessException e) {
            String errorMessage = createSearchErrorMessage(name, location, e);
            throw new ContractorException(errorMessage);
        }
    }

    /**
     * Books the specified contractor
     * 
     * @param contractor
     *            - the contractor that is to be booked
     * @throws ContractorException
     *             - if the booking has failed
     */
    @Override
    public void bookContractor(Contractor contractor) throws ContractorException {
        try {
            contractorFacade.bookContractor(contractor);
        } catch (RecordNotFoundException | DataAccessException e) {
            String errorMessage = createBookErrorMessage(contractor, e);
            throw new ContractorException(errorMessage);
        }
    }

    private String createSearchErrorMessage(String name, String location, Exception e) {
        String format = " error when searching for contractors who's names and addresses begins with %s and %s \n %s";
        return String.format(format, name, location, e.getMessage());
    }

    private String createBookErrorMessage(Contractor contractor, Exception e) {
        String format = " error when booking contractor with id  %d \n %s";
        int contractorId = contractor.getContractorId();
        return String.format(format, contractorId, e.getMessage());
    }
}
