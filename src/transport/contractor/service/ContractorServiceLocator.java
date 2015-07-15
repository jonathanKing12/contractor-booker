package transport.contractor.service;

import runmode.RunModeHelper;
import transport.contractor.service.remote.RemoteContratorSerivceDelegator;

public class ContractorServiceLocator {

    private RunModeHelper helper;

    /**
     * Constructs a BusinessContractorService instance
     */
    ContractorServiceLocator() {
        helper = RunModeHelper.getInstance();
    }

    /**
     * Creates a ContractorService instance
     * 
     * @return the ContractorService
     */
    ContractorService getContractorService() {

        if (helper.isRunningInAloneMode()) {
            return new ContractorServiceFacadeWrapper();
        }
        return new RemoteContratorSerivceDelegator();
    }
}
