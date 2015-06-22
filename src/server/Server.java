package server;

import static java.rmi.registry.LocateRegistry.createRegistry;
import static java.rmi.server.UnicastRemoteObject.exportObject;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import transport.remote.RemoteService;
import transport.remote.RemoteServiceInterface;

public class Server {

	private static int port = 1236;

	public static void register() throws RemoteException, MalformedURLException, InterruptedException {

		RemoteServiceInterface service = new RemoteService();
		RemoteServiceInterface serviceStub = (RemoteServiceInterface) exportObject(service, port);
		Registry registry = createRegistry(port);
		// Registry r=LocateRegistry.g
		registry.rebind("service", serviceStub);
		Thread.sleep(8000);
		// UnicastRemoteObject.unexportObject(service, Boolean.FALSE);
	}

	public static void main(String[] args) throws RemoteException, MalformedURLException, InterruptedException {
		new Server().register();
	}
	// registry.rebind("//localhost:" + port + "/factory", serviceStub)
}
