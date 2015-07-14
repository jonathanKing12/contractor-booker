package transport.contractor.service;

import java.util.List;

import transport.contractor.Contractor;

public interface ContractorService {

	List<Contractor> getContractors(String name, String location) throws ContractorException;

	void bookContractor(Contractor contractor) throws ContractorException;
}
