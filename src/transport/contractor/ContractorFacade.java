package transport.contractor;

import static transport.contractor.ContracorUtills.toContractor;

import java.util.ArrayList;
import java.util.List;

import suncertify.db.Data;
import suncertify.db.record.RecordNotFoundException;
import filesource.FileStreamFactory;

public class ContractorFacade {

	private Data data;

	public ContractorFacade() {
		data = new Data(new FileStreamFactory());
	}

	Contractor getContractor(int contractorId) throws RecordNotFoundException {
		String[] contractorDetails = data.read(contractorId);
		return toContractor(contractorId, contractorDetails);
	}

	List<Contractor> getContractors(String name, String location) throws RecordNotFoundException {
		int[] contractorIds = getContractorsIds(name, location);

		List<Contractor> contractors = new ArrayList<>();
		for (int contractorId : contractorIds) {
			contractors.add(getContractor(contractorId));
		}
		return contractors;
	}

	void bookContractor(Contractor contractor) throws ContractorException, RecordNotFoundException, SecurityException {
		long lockCookie = lockContractor(contractor);
		try {
			if (isContractorBooked(contractor)) {
				throw new ContractorException();
			}
			bookContractor(contractor, lockCookie);
		} finally {
			unlockContractor(contractor, lockCookie);
		}
	}

	private long lockContractor(Contractor contractor) throws RecordNotFoundException, SecurityException {
		int contractorId = contractor.getContractorId();
		return data.lock(contractorId);
	}

	private void unlockContractor(Contractor contractor, long lockCookie) throws RecordNotFoundException, SecurityException {
		int contractorId = contractor.getContractorId();
		data.unlock(contractorId, lockCookie);
	}

	private void bookContractor(Contractor contractor, long lockCookie) throws RecordNotFoundException {
		String[] contractorDetials = contractor.toArray();
		int contractorId = contractor.getContractorId();
		data.update(contractorId, contractorDetials, lockCookie);
	}

	private boolean isContractorBooked(Contractor contractor) throws RecordNotFoundException {
		int contractorId = contractor.getContractorId();
		Contractor storedContractor = getContractor(contractorId);
		return storedContractor.isBooked();
	}

	private int[] getContractorsIds(String name, String location) {
		String nameSearchCritea = getSearchCritea(name);
		String locationSearchCritea = getSearchCritea(location);
		String[] contractorsCritea = { nameSearchCritea, locationSearchCritea, null, null, null, null };
		return data.find(contractorsCritea);
	}

	private String getSearchCritea(String property) {
		return (property.trim().isEmpty()) ? null : property;
	}
}
