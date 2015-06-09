package client.model;

import java.io.IOException;

import transport.contractor.ContractorException;
import transport.network.ConfigurableDelegator;
import transport.network.LocalDelegator;

public class ConfigurationModel implements ConfigurableModel {

	private ConfigurableDelegator delegator;

	public ConfigurationModel() throws IOException {
		delegator = new LocalDelegator();
	}

	@Override
	public void setLocation(String location) throws ContractorException {
		delegator.setLocation(location);
	}

	@Override
	public String getLocation() throws ContractorException {
		return delegator.getLocation();
	}
}
