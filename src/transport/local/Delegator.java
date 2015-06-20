package transport.local;

import java.util.List;

import transport.contractor.Contractor;
import transport.contractor.ContractorException;

public interface Delegator {

	Contractor getContractor(int contractorId) throws ContractorException;

	List<Contractor> getContractors(String name, String location) throws ContractorException;

	void updateContractor(Contractor contractor) throws ContractorException;

	void setLocation(String location) throws ContractorException;

	String getLocation() throws ContractorException;
}
