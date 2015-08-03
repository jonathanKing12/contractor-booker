package ui.controller;

import ui.controller.api.*;
import ui.view.common.MessageBoxPresenterView;
import ui.view.contractor.ContractorMediatorView;

public class ControllerFactory {

    /**
     * Creates a instance of SettableController with the specified settingsView;
     * 
     * @param messageMediatorView
     *            the messageMediatorView
     * @return the SettableController instance
     */
    public SettableController getSettingsController(MessageBoxPresenterView messageMediatorView) {
        return new SettingsController(messageMediatorView);
    }

    /**
     * Creates a instance of ContractorController with the specified contractorMediatorView;
     * 
     * @param contractorMediatorView
     *            the contractorMediatorView
     * @return the ContractorController instance
     */
    public ContractorController getContractorController(
            ContractorMediatorView contractorMediatorView) {
        return new ContractorTableController(contractorMediatorView);
    }

    /**
     * Creates a instance of ServerController with the specified serverView;
     * 
     * @param messageMediatorView
     *            the messageMediatorView
     * @return the ServerController instance
     */
    public ServerController getServerController(MessageBoxPresenterView messageMediatorView) {
        return new ServerSwithController(messageMediatorView);
    }
}
