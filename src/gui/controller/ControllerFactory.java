package gui.controller;

import gui.controller.api.BookableController;
import gui.view.api.BookableView;
import gui.view.api.SettableView;

public class ControllerFactory {

	public SettingsController getClientSettingsController(SettableView settingsView) {
		return new SettingsController(settingsView);
	}

	public BookableController getContractorController(BookableView contractorViewMerger) {
		return new ContractorController(contractorViewMerger);
	}
}
