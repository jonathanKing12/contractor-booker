package transport.contractor.service;

import java.util.List;

import transport.contractor.Contractor;

public class BusinessContractorService {

	private ContractorService delegate;

	public BusinessContractorService() {
		ContractorServiceLocator locator = new ContractorServiceLocator();
		delegate = locator.getDelegate();
	}

	public List<Contractor> getContractors(String name, String location) throws ContractorException {
		return delegate.getContractors(name, location);
	}

	public void bookContractor(Contractor contractor) throws ContractorException {
		delegate.bookContractor(contractor);
	}
}
