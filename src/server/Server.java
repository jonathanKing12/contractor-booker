package server;

import static java.rmi.Naming.rebind;
import static java.rmi.registry.LocateRegistry.createRegistry;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import transport.remote.RemoteService;

public class Server {

    private static int port = 1234;
    private boolean registed;

    public void register() throws RemoteException, MalformedURLException {
        if (registed) {
            return;
        }

        registed = true;
        createRegistry(port);
        //FactoryInterface factory = new Factory();
        System.out.println("factory created");
        rebind("factory", new RemoteService());
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        new Server().register();
    }
}
