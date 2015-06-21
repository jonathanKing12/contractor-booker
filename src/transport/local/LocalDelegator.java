package transport.local;

import java.util.List;

import transport.contractor.Contractor;
import transport.contractor.ContractorException;
import transport.contractor.ContractorFacadeWrapper;

public class LocalDelegator implements Delegator {

	private ContractorFacadeWrapper contractorFacade;

	public LocalDelegator() {
		contractorFacade = new ContractorFacadeWrapper();
	}

	@Override
	public Contractor getContractor(int contractorId) throws ContractorException {
		return contractorFacade.getContractor(contractorId);
	}

	@Override
	public List<Contractor> getContractors(String name, String location) throws ContractorException {
		return contractorFacade.getContractors(name, location);
	}

	@Override
	public void updateContractor(Contractor contractor) throws ContractorException {
		contractorFacade.bookContractor(contractor);
	}
}
