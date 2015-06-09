package network;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import transport.contractor.Contractor;
import transport.contractor.ContractorBuilder;
import transport.contractor.ContractorException;
import transport.contractor.ContractorFacade;
import transport.contractor.ContractorWrapper;

public class RemoteService extends UnicastRemoteObject implements RemoteServiceInterface {

	private ContractorWrapper contractorFacade;

	public RemoteService() throws IOException {
		contractorFacade = new ContractorWrapper(new ContractorFacade());
	}

	@Override
	public Contractor getMessageNumber(int number) throws RemoteException {
		return new ContractorBuilder().addContractorId(1).addName("remote name").build();
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
			contractorFacade.updateContractor(contractor);
		} catch (ContractorException e) {
			throw new RemoteException(e.getMessage());
		}
	}
}
