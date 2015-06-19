package network;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import transport.contractor.Contractor;
import transport.contractor.ContractorException;
import transport.contractor.ContractorFacade;
import transport.contractor.ContractorFacadeWrapper;

public class RemoteService extends UnicastRemoteObject implements RemoteServiceInterface {

	private ContractorFacadeWrapper contractorFacade;

	public RemoteService() throws IOException {
		contractorFacade = new ContractorFacadeWrapper(new ContractorFacade());
	}

	@Override
	public Contractor getContractor(int contractorId) throws RemoteException {
		try {
			return contractorFacade.getContractor(contractorId);
		} catch (ContractorException e) {
			throw new RemoteException(e.getMessage());
		}
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
	public void updateContractor(Contractor contractor) throws RemoteException {
		try {
			contractorFacade.bookContractor(contractor);
		} catch (ContractorException e) {
			throw new RemoteException(e.getMessage());
		}
	}
}
