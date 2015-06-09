package network;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Factory extends UnicastRemoteObject implements FactoryInterface {

	public Factory() throws RemoteException {
	}

	@Override
	public String getMessageNumber(int number) throws RemoteException {
		return "the number is " + number;
	}

	@Override
	public RemoteServiceInterface getRemoteService() throws IOException {
		return new RemoteService();
	}

}
