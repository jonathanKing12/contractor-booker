package server;

import static java.lang.Boolean.FALSE;
import static java.rmi.registry.LocateRegistry.createRegistry;
import static java.rmi.server.UnicastRemoteObject.exportObject;
import static java.rmi.server.UnicastRemoteObject.unexportObject;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import settings.SettableAccessor;
import settings.SettingType;
import settings.SettingsAccessor;
import transport.contractor.service.remote.RemoteContractorService;
import transport.contractor.service.remote.RemoteContractorServiceFacadeWrapper;

public class Server {

	private RemoteContractorService service;
	private Registry registry;

	public Server() {
		service = new RemoteContractorServiceFacadeWrapper();
	}

	public void enable() throws SererException {
		try {
			SettableAccessor sett = new SettingsAccessor();
			int portNumber = Integer.parseInt(sett.getSettings().get(SettingType.PORT_NUMBER));
			RemoteContractorService serviceStub = (RemoteContractorService) exportObject(service, portNumber);
			registry = createRegistry(portNumber);
			registry.rebind("service", serviceStub);
		} catch (RemoteException e) {
			throw new SererException("error when disabling server");
		}
	}

	public void disable() throws SererException {
		try {
			while (!unexportObject(service, FALSE)) {
			}
			while (!unexportObject(registry, FALSE)) {
			}
		} catch (NoSuchObjectException e) {
			throw new SererException("error when disabling server");
		}
	}
}
