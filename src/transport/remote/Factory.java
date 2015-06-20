package transport.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Factory extends UnicastRemoteObject implements FactoryInterface {

	public Factory() throws RemoteException {
	}

	@Override
	public RemoteServiceInterface getRemoteService() throws RemoteException {
		return new RemoteService();
	}
}
