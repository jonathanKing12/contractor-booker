package transport.contractor;

import java.util.List;

import suncertify.db.record.RecordNotFoundException;
import datasource.locator.DataSourceLocationException;

public class ContractorWrapper {

	private ContractorFacade contractorDataAccessObject;

	public ContractorWrapper(ContractorFacade contractorDataAccessObject) {
		this.contractorDataAccessObject = contractorDataAccessObject;
	}

	public List<Contractor> getContractors(String name, String location) throws ContractorException {
		try {
			return contractorDataAccessObject.getContractors(name, location);
		} catch (RecordNotFoundException e) {
			throw new ContractorException(e.getMessage());
		}
	}

	public Contractor getContractor(int contractorId) throws ContractorException {
		try {
			return contractorDataAccessObject.getContractor(contractorId);
		} catch (RecordNotFoundException e) {
			throw new ContractorException(e.getMessage());
		}
	}

	public void updateContractor(Contractor contractor) throws ContractorException {
		try {
			contractorDataAccessObject.updateContractor(contractor);
		} catch (RecordNotFoundException e) {
			throw new ContractorException(e.getMessage());
		}
	}

	public String getLocation() throws ContractorException {
		try {
			return contractorDataAccessObject.getLocation();
		} catch (DataSourceLocationException e) {
			throw new ContractorException(e.getMessage());
		}
	}

	public void setLocation(String location) throws ContractorException {
		try {
			contractorDataAccessObject.setLocation(location);
		} catch (DataSourceLocationException e) {
			throw new ContractorException(e.getMessage());
		}
	}
}
