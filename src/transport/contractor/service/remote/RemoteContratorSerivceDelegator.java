package transport.contractor.service.remote;

import static constants.Constants.REMOTE_OBJECT_NAME;
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

    private RemoteContractorService remoteContractorSerivce;

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
            return remoteContractorSerivce.getContractors(name, location);
        } catch (RemoteException e) {
            String errorMessage = createGetContractorsErrorMessage(name, location);
            throw new ContractorException(errorMessage);
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
            remoteContractorSerivce.bookContractor(contractor);
        } catch (RemoteException e) {
            String errorMessage = createBookingErrorMessage(contractor);
            throw new ContractorException(errorMessage);
        }
    }

    private void connectToRemoteService() throws ContractorException {

        try {
            Map<SettingType, String> settings = getConnectionSettings();
            String ipAddress = settings.get(IP_ADDRESS);
            String portNumber = settings.get(PORT_NUMBER);

            String url = createUrl(ipAddress, portNumber);
            remoteContractorSerivce = (RemoteContractorService) Naming.lookup(url);

        } catch (MalformedURLException | RemoteException | NotBoundException | SettingsException e) {
            throw new ContractorException("failed to connect to server");
        }
    }

    private Map<SettingType, String> getConnectionSettings() throws SettingsException {
        SettingsService settableAccess = new SettingsFacade();
        return settableAccess.loadSettings();
    }

    private String createUrl(String ipAddress, String portNumber) {
        StringBuilder builder = new StringBuilder("//");
        builder.append(ipAddress);
        builder.append(":");
        builder.append(portNumber);
        builder.append("/");
        builder.append(REMOTE_OBJECT_NAME);
        return builder.toString();
    }

    private String createGetContractorsErrorMessage(String name, String location) {
        StringBuilder builder = new StringBuilder();
        builder.append("Failed to find contractors that meed the search criteria\n");
        builder.append("The server could possibly be stopped");
        return builder.toString();
    }

    private String createBookingErrorMessage(Contractor contractor) {
        StringBuilder builder = new StringBuilder("Failed to book contractor ");
        builder.append(contractor.getName());
        builder.append("\n Check the server is still running");
        builder.append("\n or redo another search");
        return builder.toString();
    }
}
