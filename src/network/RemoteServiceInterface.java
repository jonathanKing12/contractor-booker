package network;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import transport.contractor.Contractor;

public interface RemoteServiceInterface extends Remote {

	Contractor getMessageNumber(int number) throws RemoteException;

	void updateContractor(Contractor contractor) throws IOException;

	List<Contractor> getContractors(String name, String location) throws IOException;

	Contractor getContractor(int contractorId) throws IOException;
}
