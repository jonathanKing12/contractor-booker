package transport.network;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import network.FactoryInterface;
import network.RemoteServiceInterface;
import transport.contractor.Contractor;
import transport.contractor.ContractorException;

public class RemoteDelegator implements Delegator {

	private RemoteServiceInterface remoteSerivce;

	public RemoteDelegator() throws NotBoundException {
		getRemoteService();
	}

	@Override
	public Contractor getContractor(int contractorId) throws RemoteException {
		return remoteSerivce.getContractor(contractorId);
	}

	@Override
	public List<Contractor> getContractors(String name, String location) throws RemoteException {
		return remoteSerivce.getContractors(name, location);
	}

	@Override
	public void updateContractor(Contractor contractor) throws ContractorException {
		remoteSerivce.updateContractor(contractor);
	}

	private void getRemoteService() throws NotBoundException {
		FactoryInterface factory = (FactoryInterface) Naming.lookup("factory");
		remoteSerivce = factory.getRemoteService();
	}

	// public static void main(String[] args) throws NotBoundException, ContractorException {
	// Delegator delegator = new RemoteDelegator();
	// Contractor r = delegator.getContractor(27);
	// r.setOwner("33333");
	// delegator.updateContractor(r);
	//
	// Contractor u = delegator.getContractor(27);
	// }
}
