package transport.contractor;

import java.util.List;

import suncertify.db.InvalidLockCookieException;
import suncertify.db.record.RecordNotFoundException;

public class ContractorFacadeWrapper {

	private ContractorFacade contractorFacade;

	public ContractorFacadeWrapper() {
		this.contractorFacade = new ContractorFacade();
	}

	public List<Contractor> getContractors(String name, String location) throws ContractorException {
		try {
			return contractorFacade.getContractors(name, location);
		} catch (RecordNotFoundException e) {
			String errorMessage = createSearchErrorMessage(name, location, e);
			throw new ContractorException(errorMessage);
		}
	}

	public Contractor getContractor(int contractorId) throws ContractorException {
		try {
			return contractorFacade.getContractor(contractorId);
		} catch (RecordNotFoundException | InvalidLockCookieException e) {
			String errorMessage = createGetContractorErrorMessage(contractorId, e);
			throw new ContractorException(errorMessage);
		}
	}

	public void bookContractor(Contractor contractor) throws ContractorException {
		try {
			contractorFacade.bookContractor(contractor);
		} catch (RecordNotFoundException e) {
			String errorMessage = createBookErrorMessage(contractor, e);
			throw new ContractorException(errorMessage);
		}
	}

	private String createSearchErrorMessage(String name, String location, RecordNotFoundException e) {
		String format = " error when searching for contractors who's names and addresses begins with %s and %s \n %s";
		return String.format(format, name, location, e.getMessage());
	}

	private String createGetContractorErrorMessage(int contractorId, Exception e) {
		String format = " error when retreiving contractor with id  %d \n %s";
		return String.format(format, contractorId, e.getMessage());
	}

	private String createBookErrorMessage(Contractor contractor, RecordNotFoundException e) {
		String format = " error when booking contractor with id  %d \n %s";
		int contractorId = contractor.getContractorId();
		return String.format(format, contractorId, e.getMessage());
	}
}
