package transport.contractor;

import java.util.List;

import setting.SettingException;
import suncertify.db.InvalidLockCookieException;
import suncertify.db.record.RecordNotFoundException;

public class ContractorFacadeWrapper {

	private ContractorFacade contractorFacade;

	public ContractorFacadeWrapper() {
		this.contractorFacade = new ContractorFacade();
	}

	public List<Contractor> getContractors(String name, String location) throws ContractorException {
		try {
			return contractorFacade.getContractors(name, location);
		} catch (RecordNotFoundException e) {
			throw new ContractorException(e.getMessage());
		}
	}

	public Contractor getContractor(int contractorId) throws ContractorException {
		try {
			return contractorFacade.getContractor(contractorId);
		} catch (RecordNotFoundException | InvalidLockCookieException e) {
			throw new ContractorException(e.getMessage());
		}
	}

	public void bookContractor(Contractor contractor) throws ContractorException {
		try {
			contractorFacade.bookContractor(contractor);
		} catch (RecordNotFoundException e) {
			throw new ContractorException(e.getMessage());
		}
	}

	public String getLocation() throws ContractorException {
		try {
			return contractorFacade.getLocation();
		} catch (SettingException e) {
			throw new ContractorException(e.getMessage());
		}
	}

	public void setLocation(String location) throws ContractorException {
		try {
			contractorFacade.setLocation(location);
		} catch (SettingException e) {
			throw new ContractorException(e.getMessage());
		}
	}
}
