package server;

import static java.rmi.Naming.rebind;
import static java.rmi.registry.LocateRegistry.createRegistry;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import transport.remote.Factory;
import transport.remote.FactoryInterface;

public class Server {

	private static int port = 1099;

	public static void register() throws RemoteException, MalformedURLException {
		createRegistry(port);
		FactoryInterface factory = new Factory();
		rebind("factory", factory);
	}

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		new Server().register();
	}
}
