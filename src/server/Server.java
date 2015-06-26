package server;

import static java.lang.Boolean.FALSE;
import static java.rmi.registry.LocateRegistry.createRegistry;
import static java.rmi.server.UnicastRemoteObject.exportObject;
import static java.rmi.server.UnicastRemoteObject.unexportObject;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import transport.remote.BookableService;
import transport.remote.ContractorService;

public class Server {

	private int port = 1236;
	private BookableService service;
	Registry registry;

	public Server() {
		service = new ContractorService();
	}

	public void enable() {
		try {
			BookableService serviceStub = (BookableService) exportObject(service, port);
			registry = createRegistry(port);
			registry.rebind("service", serviceStub);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void disable() {
		try {
			while (!unexportObject(service, FALSE)) {
			}
			while (!unexportObject(registry, FALSE)) {
			}
		} catch (NoSuchObjectException e) {
			e.printStackTrace();
		}
	}
}
