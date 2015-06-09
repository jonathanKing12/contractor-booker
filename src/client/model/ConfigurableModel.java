package client.model;

import transport.contractor.ContractorException;

public interface ConfigurableModel {

	void setLocation(String fileName) throws ContractorException;

	String getLocation() throws ContractorException;

}
