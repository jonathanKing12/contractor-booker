package transport.local;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import transport.contractor.Contractor;
import transport.contractor.ContractorException;
import transport.remote.RemoteServiceInterface;

public class RemoteDelegator implements Delegator {

	private RemoteServiceInterface remoteSerivce;

	public RemoteDelegator() throws NotBoundException, RemoteException, MalformedURLException {
		getRemoteService();
	}

	@Override
	public Contractor getContractor(int contractorId) throws ContractorException {
		try {
			return remoteSerivce.getContractor(contractorId);
		} catch (RemoteException e) {
			throw new ContractorException(e.getMessage());
		}
	}

	@Override
	public List<Contractor> getContractors(String name, String location) throws ContractorException {
		try {
			return remoteSerivce.getContractors(name, location);
		} catch (RemoteException e) {
			throw new ContractorException(e.getMessage());
		}
	}

	@Override
	public void updateContractor(Contractor contractor) throws ContractorException {
		try {
			remoteSerivce.bookContractor(contractor);
		} catch (RemoteException e) {
			throw new ContractorException(e.getMessage());
		}
	}

	void getRemoteService() throws NotBoundException, RemoteException, MalformedURLException {
		remoteSerivce = (RemoteServiceInterface) Naming.lookup("//localhost:1236/service");
		// remoteSerivce = factory.getRemoteService();
		System.out.println(remoteSerivce == null);
	}

	public static void main(String[] args) throws NotBoundException, ContractorException, RemoteException, MalformedURLException {
		RemoteDelegator delegator = new RemoteDelegator();
		Contractor r = delegator.getContractor(24);
		// r = delegator.getContractor(24);
		// r = delegator.getContractor(24);
		// r = delegator.getContractor(24);
		// r.setOwner("33333");
		System.out.println(r.getContractorId());
		System.out.println("done");
		delegator.getRemoteService();
		System.out.println(r.getContractorId());
		System.out.println("exit");
		// Contractor u = delegator.getContractor(27);
	}
}
