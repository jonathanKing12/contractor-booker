package client.view;

import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTable;

import setting.SettingType;
import client.controller.BookableController;
import client.controller.ControllerFactory;
import client.controller.SettingsController;
import client.view.settings.SettingsDialogBox;

public class ViewMerger implements BookableView {

	private static ViewMerger instance;

	private SettingsController settingsController;
	private BookableController bookableController;
	private MessageBoxCreator messageBoxCreator;
	private SettingsDialogBox settings;
	private ButtonPanel buttonPanel;
	private ControllerFactory controllerFactory;

	static {
		instance = new ViewMerger();
	}

	public static ViewMerger getInstance() {
		return instance;
	}

	private ViewMerger() {
		controllerFactory = new ControllerFactory();
	}

	public void addSettingsDialogBox(SettingsDialogBox settings) {
		this.settings = settings;
	}

	public Map<SettingType, String> getSettings() {
		return settingsController.getSettings();
	}

	public void saveSettings(Map<SettingType, String> settings) {
		settingsController.saveSettings(settings);
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

	public void setUpConnectionToClient(JFrame mainFrame) {
		messageBoxCreator = new MessageBoxCreator(mainFrame);
		bookableController = controllerFactory.getContractorController(this);
		settingsController = controllerFactory.getClientSettingsController();
	}

	public String displayInputDialogBox(String request) {
		return messageBoxCreator.displayInputDialog(request);
	}

	public void displayMessageBox(String errorMessage, String title) {
		messageBoxCreator.displayErrorMessageBox(errorMessage, title);
	}
}
