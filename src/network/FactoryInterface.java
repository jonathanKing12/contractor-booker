package network;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FactoryInterface extends Remote {
	String getMessageNumber(int number) throws RemoteException;

	RemoteServiceInterface getRemoteService() throws RemoteException, IOException;
}
