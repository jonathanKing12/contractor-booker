package client.controller;

import client.view.BookableView;

public class ControllerFactory {

	public SettingsController getClientSettingsController() {
		return new ClientSettingsController();
	}

	public BookableController getContractorController(BookableView view) {
		return new ContractorController(view);
	}
}
