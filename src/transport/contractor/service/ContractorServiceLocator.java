package transport.contractor.service;

import runmode.RunModeHelper;
import transport.contractor.service.remote.RemoteContratorSerivceDelegator;

public class ContractorServiceLocator {

    private static ContractorServiceLocator INSTANCE;
    private ContractorService contractorService;
    private RunModeHelper helper;

    static {
        INSTANCE = new ContractorServiceLocator();
    }

    private ContractorServiceLocator() {
        helper = RunModeHelper.getInstance();
    }

    /**
     * gets the only instance of a ConractorServiceLocator.
     */
    public static ContractorServiceLocator getInstance() {
        return INSTANCE;
    }

    /**
     * Creates a ContractorService instance
     * 
     * @return the ContractorService
     */
    ContractorService getContractorService() {
        if (contractorService == null) {
            contractorService = createContractorService();
        }
        return contractorService;
    }

    private ContractorService createContractorService() {
        if (helper.isRunningInAloneMode()) {
            return new ContractorServiceFacadeWrapper();
        }
        return new RemoteContratorSerivceDelegator();
    }
}
