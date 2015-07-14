package transport.contractor.service;

import runmode.RunModeHelper;
import transport.contractor.service.remote.RemoteContratorSerivceDelegator;

public class ContractorServiceLocator {

	private RunModeHelper helper;

	public ContractorServiceLocator() {
		helper = RunModeHelper.getInstance();
	}

	public ContractorService getDelegate() {

		if (helper.isRunningInAloneMode()) {
			return new ContractorServiceFacadeWrapper();
		}
		return new RemoteContratorSerivceDelegator();
	}
}
