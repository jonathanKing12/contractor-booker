package network;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import transport.contractor.Contractor;

public interface RemoteServiceInterface extends Remote {

	void updateContractor(Contractor contractor) throws RemoteException;

	List<Contractor> getContractors(String name, String location) throws RemoteException;

	Contractor getContractor(int contractorId) throws RemoteException;
}
