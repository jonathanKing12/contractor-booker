package transport.contractor.service.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import transport.contractor.Contractor;

/**
 * Used for searching and booking contractors remotely.
 */
public interface RemoteContractorService extends Remote {

    /**
     * Searches for all contractors who's name and location both match the beginning of the specified name and location.
     * 
     * @param name
     *            - the value used to match against the beginning of all contractor names. If null then all contractors will match
     * @param location
     *            - the value used to match against the beginning of all contractor locations. If null then all contractors will match
     * @return a List of contractors who name and location matched the specified values.
     * @throws RemoteException
     *             - if the search fails or a remote exception occurs.
     */
    List<Contractor> getContractors(String name, String location) throws RemoteException;

    /**
     * Books the specified contractor
     * 
     * @param contractor
     *            - the contractor that is to be booked
     * @throws RemoteException
     *             - if the booking has failed or a remote exception occurs.
     */
    void bookContractor(Contractor contractor) throws RemoteException;
}
