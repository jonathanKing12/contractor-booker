package transport.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import transport.contractor.Contractor;

public interface RemoteServiceInterface extends Remote {

	void bookContractor(Contractor contractor) throws RemoteException;

	List<Contractor> getContractors(String name, String location) throws RemoteException;

	Contractor getContractor(int contractorId) throws RemoteException;

	void setLocation(String location) throws RemoteException;

	String getLocation() throws RemoteException;
}
