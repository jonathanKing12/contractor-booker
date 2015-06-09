package transport.network;

import transport.contractor.ContractorException;

public interface ConfigurableDelegator {

	void setLocation(String location) throws ContractorException;

	String getLocation() throws ContractorException;

}
