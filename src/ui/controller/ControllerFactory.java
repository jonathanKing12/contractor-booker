package ui.controller;

import ui.controller.api.BookableController;
import ui.view.api.BookableView;
import ui.view.api.SettableView;

public class ControllerFactory {

	public SettingsController getClientSettingsController(SettableView settingsView) {
		return new SettingsController(settingsView);
	}

	public BookableController getContractorController(BookableView contractorViewMerger) {
		return new ContractorController(contractorViewMerger);
	}
}
