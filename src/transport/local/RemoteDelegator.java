package transport.local;

import static settings.SettingType.IP_ADDRESS;
import static settings.SettingType.PORT_NUMBER;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import settings.SettableAccessor;
import settings.SettingType;
import settings.SettingsAccessor;
import transport.contractor.Contractor;
import transport.contractor.ContractorException;
import transport.remote.BookableService;

public class RemoteDelegator implements Delegator {

	private BookableService remoteSerivce;

	@Override
	public Contractor getContractor(int contractorId) throws ContractorException {
		try {
			return remoteSerivce.getContractor(contractorId);
		} catch (RemoteException e) {
			throw new ContractorException(e.getMessage());
		}
	}

	@Override
	public List<Contractor> getContractors(String name, String location) throws ContractorException {
		if (remoteSerivce == null) {
			connectToRemoteService();
		}

		try {
			return remoteSerivce.getContractors(name, location);
		} catch (RemoteException e) {
			throw new ContractorException(e.getMessage());
		}
	}

	@Override
	public void updateContractor(Contractor contractor) throws ContractorException {
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
			remoteSerivce = (BookableService) Naming.lookup(url);

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
