package transport.contractor.service.remote;

import java.rmi.RemoteException;
import java.util.List;

import transport.contractor.Contractor;
import transport.contractor.service.ContractorException;
import transport.contractor.service.ContractorServiceFacadeWrapper;

public class RemoteContractorServiceFacadeWrapper implements RemoteContractorService {

	private ContractorServiceFacadeWrapper contractorFacade;

	public RemoteContractorServiceFacadeWrapper() {
		contractorFacade = new ContractorServiceFacadeWrapper();
	}

	@Override
	public List<Contractor> getContractors(String name, String location) throws RemoteException {
		try {
			return contractorFacade.getContractors(name, location);
		} catch (ContractorException e) {
			throw new RemoteException(e.getMessage());
		}
	}

	@Override
	public void bookContractor(Contractor contractor) throws RemoteException {
		try {
			contractorFacade.bookContractor(contractor);
		} catch (ContractorException e) {
			throw new RemoteException(e.getMessage());
		}
	}
}
