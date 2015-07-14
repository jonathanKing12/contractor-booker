package transport.contractor.service.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import transport.contractor.Contractor;

public interface RemoteContractorService extends Remote {

	void bookContractor(Contractor contractor) throws RemoteException;

	List<Contractor> getContractors(String name, String location) throws RemoteException;
}
