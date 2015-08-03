package server;

import static constants.Constants.REMOTE_OBJECT_NAME;
import static java.lang.Boolean.FALSE;
import static java.rmi.registry.LocateRegistry.createRegistry;
import static java.rmi.server.UnicastRemoteObject.exportObject;
import static java.rmi.server.UnicastRemoteObject.unexportObject;
import static settings.SettingType.PORT_NUMBER;

import java.rmi.*;
import java.rmi.registry.Registry;

import settings.*;
import transport.contractor.service.remote.RemoteContractorService;
import transport.contractor.service.remote.RemoteContractorServiceFacadeWrapper;

/**
 * Represents the Server. Used to start an stop the server and register the remote service.
 */
public class Server {

    private RemoteContractorService service;
    private Registry registry;

    /**
     * Constructs a instance of Server.
     */
    public Server() {
        service = new RemoteContractorServiceFacadeWrapper();
    }

    /**
     * Starts the Server instance.
     * 
     * @throws ServerException
     *             if the server fails to start
     */
    public void start() throws ServerException {
        try {
            setUpRegistryAndServiceStub();
        } catch (RemoteException | SettingsException e) {
            throw new ServerException("Failed to start server");
        }

    }

    /**
     * Stops the Server instance. Waits till all Clients requests have finished executing on the server.
     * 
     * @throws ServerException
     *             if the server fails to stop
     */
    public void stop() throws ServerException {
        try {
            while (!unexportObject(service, FALSE)) {
            }
            while (!unexportObject(registry, FALSE)) {
            }
        } catch (NoSuchObjectException e) {
            throw new ServerException("Failed to stop server");
        }
    }

    private void setUpRegistryAndServiceStub() throws RemoteException, AccessException,
            ServerException, SettingsException {

        int portNumber = getPortNumber();
        RemoteContractorService remoteContractorService = (RemoteContractorService) exportObject(
                service, portNumber);
        registry = createRegistry(portNumber);
        registry.rebind(REMOTE_OBJECT_NAME, remoteContractorService);
    }

    private int getPortNumber() throws ServerException, SettingsException {
        SettingsService sett = new SettingsFacade();
        String portNumber = sett.loadSettings().get(PORT_NUMBER);

        try {
            return Integer.parseInt(portNumber);
        } catch (NumberFormatException e) {
            String messageFormat = "Failed to start server as the port number: %s is not a number";
            String errorMessage = String.format(messageFormat, portNumber);
            throw new ServerException(errorMessage);
        }
    }

}
