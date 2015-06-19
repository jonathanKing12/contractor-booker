package transport.network;

import java.io.IOException;
import java.util.List;

import transport.contractor.Contractor;
import transport.contractor.ContractorException;
import transport.contractor.ContractorFacadeWrapper;

public class LocalDelegator implements Delegator, ConfigurableDelegator {

	private ContractorFacadeWrapper contractorFacade;

	public LocalDelegator() throws IOException {
		// contractorFacade = new ContractorWrapper(new ContractorFacade());
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

	@Override
	public void setLocation(String location) throws ContractorException {
		contractorFacade.setLocation(location);
	}

	@Override
	public String getLocation() throws ContractorException {
		return contractorFacade.getLocation();
	}
}
