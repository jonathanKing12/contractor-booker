package client.controller;

import client.view.View;

public class ControllerFactory {

	public SettingsController getClientSettingsController(View view) {
		return new DataSourceSettingsController(view);
	}

	public BookableController getContractorController(View view) {
		return new ContractorController(view);
	}
}
