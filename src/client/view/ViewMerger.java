package client.view;

import java.io.IOException;

import javax.swing.JTable;

import client.controller.BookableController;
import client.controller.ConfigurableController;
import client.controller.ControllerFactory;
import client.view.settings.SettingsDialogBox;

public class ViewMerger implements BookableView {

	private static ViewMerger instance;

	private ConfigurableController configurableController;
	private BookableController bookableController;
	private SettingsDialogBox settings;
	private ButtonPanel buttonPanel;

	static {
		instance = new ViewMerger();
	}

	public static ViewMerger getInstance() {
		return instance;
	}

	private ViewMerger() {
		ControllerFactory factory = new ControllerFactory();
		// configurableController = factory.getConfigurableController();
		try {
			bookableController = factory.getContractorController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addSettingsDialogBox(SettingsDialogBox settings) {
		this.settings = settings;
	}

	String getFileName() {
		return configurableController.getFileName();
	}

	void setFileName(String fileName) {
		configurableController.setFileName(fileName);
	}

	public void displaySettngsDialogBox() {
		settings.display();
	}

	@Override
	public void enableBookContratorButton(boolean enabled) {
		buttonPanel.enableBookContratorButton(enabled);
	}

	public void bookSelectedContractorWithCustomer(String customerId) {
		bookableController.bookSelectedContractorWithCustomer(customerId);
	}

	public void search(String name, String location) {
		bookableController.search(name, location);
	}

	public JTable getJTableWithModel() {
		return bookableController.getJTableWithModel();
	}

	public void addButtonPanel(ButtonPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}
}
