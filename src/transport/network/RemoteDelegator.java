package transport.network;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.List;

import network.FactoryInterface;
import network.RemoteServiceInterface;
import transport.contractor.Contractor;

public class RemoteDelegator implements Delegator {

	private RemoteServiceInterface remoteSerivce;

	public RemoteDelegator() throws NotBoundException, IOException {
		getRemoteService();
	}

	private void getRemoteService() throws NotBoundException, IOException {
		System.out.println("begin");
		FactoryInterface factory = (FactoryInterface) Naming.lookup("factory");
		remoteSerivce = factory.getRemoteService();
		System.out.println(remoteSerivce == null);
	}

	public static void main(String[] args) throws NotBoundException, IOException {
		Delegator delegator = new RemoteDelegator();
		Contractor r = delegator.getContractor(27);
		r.setOwner("33333");
		delegator.updateContractor(r);

		Contractor u = delegator.getContractor(27);
	}

	@Override
	public Contractor getContractor(int contractorId) throws IOException {
		return remoteSerivce.getContractor(contractorId);
	}

	@Override
	public List<Contractor> getContractors(String name, String location) throws IOException {
		return remoteSerivce.getContractors(name, location);
	}

	@Override
	public void updateContractor(Contractor contractor) throws IOException {
		remoteSerivce.updateContractor(contractor);
	}
}
