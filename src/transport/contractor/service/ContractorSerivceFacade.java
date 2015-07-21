package transport.contractor.service;

import java.util.ArrayList;
import java.util.List;

import suncertify.db.*;
import suncertify.db.filesource.FileStreamFactory;
import transport.contractor.ContracorParser;
import transport.contractor.Contractor;

public class ContractorSerivceFacade {

    private DB data;

    /**
     * Constructs a ContractorSerivceFacade instance
     */
    public ContractorSerivceFacade() {
        data = new Data(new FileStreamFactory());
    }

    /**
     * Retrieves all contractors who's name and location both match the beginning of the specified name and location.
     * 
     * @param name
     *            - the value used to match against the beginning of all contractor names. If null then all contractors will match
     * @param location
     *            - the value used to match against the beginning of all contractor locations. If null then all contractors will match
     * @return a List of contractors who name and location matched the specified values.
     * @throws RecordNotFoundException
     */
    public List<Contractor> getContractors(String name, String location)
            throws RecordNotFoundException {
        int[] contractorIds = getContractorsIds(name, location);

        List<Contractor> contractors = new ArrayList<>();
        for (int contractorId : contractorIds) {
            contractors.add(getContractor(contractorId));
        }
        return contractors;
    }

    /**
     * Books the specified contractor
     * 
     * @param contractor
     *            - the contractor that is to be booked
     * @throws ContractorException
     * @throws RecordNotFoundException
     * @throws SecurityException
     */
    public void bookContractor(Contractor contractor) throws ContractorException,
            RecordNotFoundException, SecurityException {
        long lockCookie = lockContractor(contractor);
        try {
            if (isContractorBooked(contractor)) {
                String errorMessage = createErrorMessage(contractor);
                throw new ContractorException(errorMessage);
            }
            bookContractor(contractor, lockCookie);
        } finally {
            unlockContractor(contractor, lockCookie);
        }
    }

    private int[] getContractorsIds(String name, String location) {
        String nameSearchCritea = getSearchCritea(name);
        String locationSearchCritea = getSearchCritea(location);
        String[] contractorsCritea = { nameSearchCritea, locationSearchCritea, null, null, null,
                null };
        return data.find(contractorsCritea);
    }

    private Contractor getContractor(int contractorId) throws RecordNotFoundException {
        String[] contractorDetails = data.read(contractorId);
        ContracorParser parser = new ContracorParser();
        return parser.toContractor(contractorId, contractorDetails);
    }

    private long lockContractor(Contractor contractor) throws RecordNotFoundException,
            SecurityException {
        int contractorId = contractor.getContractorId();
        return data.lock(contractorId);
    }

    private void unlockContractor(Contractor contractor, long lockCookie)
            throws RecordNotFoundException, SecurityException {
        int contractorId = contractor.getContractorId();
        data.unlock(contractorId, lockCookie);
    }

    private void bookContractor(Contractor contractor, long lockCookie)
            throws RecordNotFoundException {
        String[] contractorDetials = contractor.toArray();
        int contractorId = contractor.getContractorId();
        data.update(contractorId, contractorDetials, lockCookie);
    }

    private boolean isContractorBooked(Contractor contractor) throws RecordNotFoundException {
        int contractorId = contractor.getContractorId();
        Contractor storedContractor = getContractor(contractorId);
        return storedContractor.isBooked();
    }

    private String getSearchCritea(String property) {
        return (property.isEmpty()) ? null : property;
    }

    private String createErrorMessage(Contractor contractor) {
        int contractorId = contractor.getContractorId();
        return String.format("contractor %d is already booked", contractorId);
    }
}
