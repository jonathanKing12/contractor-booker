package transport.local;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import transport.contractor.Contractor;
import transport.contractor.ContractorException;
import transport.remote.FactoryInterface;
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

	@Override
	public void setLocation(String location) throws ContractorException {
		try {
			remoteSerivce.setLocation(location);
		} catch (RemoteException e) {
			throw new ContractorException(e.getMessage());
		}
	}

	@Override
	public String getLocation() throws ContractorException {
		try {
			return remoteSerivce.getLocation();
		} catch (RemoteException e) {
			throw new ContractorException(e.getMessage());
		}
	}

	private void getRemoteService() throws NotBoundException, RemoteException, MalformedURLException {
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
