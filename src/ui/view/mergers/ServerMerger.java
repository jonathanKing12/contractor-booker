package ui.view.mergers;

import ui.controller.ControllerFactory;
import ui.controller.ServerSwithController;
import ui.view.MessageBoxPresenter;

public class ServerMerger {

	private static final ServerMerger serverMerger;
	private MessageBoxPresenter messageBoxPresenter;
	private ServerSwithController controller;

	static {
		serverMerger = new ServerMerger();
	}

	private ServerMerger() {
		ControllerFactory factory = new ControllerFactory();
		controller = new ServerSwithController(this);
		messageBoxPresenter = MessageBoxPresenter.getInstance();
	}

	public static ServerMerger getInstance() {
		return serverMerger;
	}

	public void displayErrorMessage(String errorMessage, String title) {
		messageBoxPresenter.displayErrorMessageBox(errorMessage, title);
	}

	public void enable() {
		controller.enable();
	}

	public void disable() {
		controller.disable();
	}
}
