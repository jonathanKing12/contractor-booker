package client.controller;

import java.io.IOException;

import client.view.BookableView;

public class ControllerFactory {

	public ConfigurableController getConfigurableController() {
		return new ConfigurationController();
	}

	public BookableController getController(BookableView view) throws IOException {
		return new ContractorController(view);
	}

	public BookableController getContractorController(BookableView view) throws IOException {
		return new ContractorController(view);
	}

}
