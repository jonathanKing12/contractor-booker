package transport.contractor.service.remote;

import static settings.SettingType.IP_ADDRESS;
import static settings.SettingType.PORT_NUMBER;

import java.net.MalformedURLException;
import java.rmi.*;
import java.util.List;
import java.util.Map;

import settings.*;
import transport.contractor.Contractor;
import transport.contractor.service.ContractorException;
import transport.contractor.service.ContractorService;

public class RemoteContratorSerivceDelegator implements ContractorService {

    private RemoteContractorService remoteSerivce;

    /**
     * Searches for all contractors who's name and location both match the beginning of the specified name and location.
     * 
     * @param name
     *            - the value used to match against the beginning of all contractor names. If null then all contractors will match
     * @param location
     *            - the value used to match against the beginning of all contractor locations. If null then all contractors will match
     * @return a List of contractors who name and location matched the specified values.
     * @throws ContractorException
     *             - if the search fails
     */
    @Override
    public List<Contractor> getContractors(String name, String location) throws ContractorException {
        connectToRemoteService();
        try {
            return remoteSerivce.getContractors(name, location);
        } catch (RemoteException e) {
            throw new ContractorException(e.getMessage());
        }
    }

    /**
     * Books the specified contractor
     * 
     * @param contractor
     *            - the contractor that is to be booked
     * @throws ContractorException
     *             - if the booking has failed
     */
    @Override
    public void bookContractor(Contractor contractor) throws ContractorException {
        try {
            remoteSerivce.bookContractor(contractor);
        } catch (RemoteException e) {
            throw new ContractorException(e.getMessage());
        }
    }

    private void connectToRemoteService() throws ContractorException {

        try {
            Map<SettingType, String> settings = getConnectionSettings();
            String ipAddress = settings.get(IP_ADDRESS);
            String portNumber = settings.get(PORT_NUMBER);

            String url = createUrl(ipAddress, portNumber);
            remoteSerivce = (RemoteContractorService) Naming.lookup(url);

        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            throw new ContractorException("failed to connect to server");
        }
    }

    private Map<SettingType, String> getConnectionSettings() {
        SettableAccessor settableAccess = new SettingsAccessor();
        return settableAccess.getSettings();
    }

    private String createUrl(String ipAddress, String portNumber) {
        StringBuilder builder = new StringBuilder("//");
        builder.append(ipAddress);
        builder.append(":");
        builder.append(portNumber);
        builder.append("/service");
        return builder.toString();
    }
}
