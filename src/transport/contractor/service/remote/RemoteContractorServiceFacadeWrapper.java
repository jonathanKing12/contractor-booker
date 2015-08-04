package transport.contractor.service.remote;

import java.rmi.RemoteException;
import java.util.List;

import transport.contractor.Contractor;
import transport.contractor.service.ContractorException;
import transport.contractor.service.ContractorServiceFacadeWrapper;

/**
 * Wraps a ContractorServiceFacadeWrapper instance and re throws it's exceptions as RemoteException.
 */
public class RemoteContractorServiceFacadeWrapper implements RemoteContractorService {

    private ContractorServiceFacadeWrapper contractorFacade;

    /**
     * Constructs a RemoteContractorServiceFacadeWrapper instance
     */
    public RemoteContractorServiceFacadeWrapper() {
        contractorFacade = new ContractorServiceFacadeWrapper();
    }

    /**
     * Searches for all contractors who's name and location both match the beginning of the specified name and location.
     * 
     * @param name
     *            - the value used to match against the beginning of all contractor names. If null then all contractors will match
     * @param location
     *            - the value used to match against the beginning of all contractor locations. If null then all contractors will match
     * @return a List of contractors who name and location matched the specified values.
     * @throws RemoteException
     *             - if the search fails or a remote exception occurs.
     */
    @Override
    public List<Contractor> getContractors(String name, String location) throws RemoteException {
        try {
            return contractorFacade.getContractors(name, location);
        } catch (ContractorException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * Books the specified contractor
     * 
     * @param contractor
     *            - the contractor that is to be booked
     * @throws RemoteException
     *             - if the booking has failed or a remote exception occurs.
     */
    @Override
    public void bookContractor(Contractor contractor) throws RemoteException {
        try {
            contractorFacade.bookContractor(contractor);
        } catch (ContractorException e) {
            throw new RemoteException(e.getMessage());
        }
    }
}
