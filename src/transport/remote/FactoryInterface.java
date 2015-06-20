package transport.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FactoryInterface extends Remote {

	RemoteServiceInterface getRemoteService() throws RemoteException;
}
