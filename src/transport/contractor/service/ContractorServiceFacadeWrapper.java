package transport.contractor.service;

import java.util.List;

import suncertify.db.DataAccessException;
import suncertify.db.record.RecordNotFoundException;
import transport.contractor.Contractor;

public class ContractorServiceFacadeWrapper implements ContractorService {

	private ContractorSerivceFacade contractorFacade;

	public ContractorServiceFacadeWrapper() {
		this.contractorFacade = new ContractorSerivceFacade();
	}

	@Override
	public List<Contractor> getContractors(String name, String location) throws ContractorException {
		try {
			return contractorFacade.getContractors(name, location);
		} catch (RecordNotFoundException | DataAccessException e) {
			String errorMessage = createSearchErrorMessage(name, location, e);
			throw new ContractorException(errorMessage);
		}
	}

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
