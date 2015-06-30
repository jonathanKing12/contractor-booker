package ui.view.mergers;

import ui.controller.ControllerFactory;
import ui.controller.ServerSwithController;
import ui.view.MessageBoxPresenter;

public class ServerMerger {

	private static final ServerMerger INSTANE;
	private ServerSwithController controller;

	static {
		INSTANE = new ServerMerger();
	}

	private ServerMerger() {
		ControllerFactory factory = new ControllerFactory();
		controller = new ServerSwithController(this);
	}

	public static ServerMerger getInstance() {
		return INSTANE;
	}

	public void displayErrorMessage(String errorMessage, String title) {
		MessageBoxPresenter presenter = new MessageBoxPresenter();
		presenter.displayErrorMessageBox(errorMessage, title);
	}

	public void enable() {
		controller.enable();
	}

	public void disable() {
		controller.disable();
	}
}
