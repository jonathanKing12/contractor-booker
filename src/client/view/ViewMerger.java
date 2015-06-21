package client.view;

import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTable;

import settings.SettingType;
import client.controller.BookableController;
import client.controller.ControllerFactory;
import client.controller.SettingsController;
import client.view.settings.SettingsDialogBox;

public enum ViewMerger implements View {

	VIEW_MERGER_INSTACE;

	private SettingsController settingsController;
	private BookableController bookableController;
	private MessageBoxCreator messageBoxCreator;
	private SettingsDialogBox settingsDialogBox;
	private ButtonPanel buttonPanel;
	private ControllerFactory controllerFactory;

	private ViewMerger() {
		controllerFactory = new ControllerFactory();
		settingsController = controllerFactory.getClientSettingsController(this);
	}

	public void addSettingsDialogBox(SettingsDialogBox settingsDialogBox) {
		this.settingsDialogBox = settingsDialogBox;
	}

	public void addButtonPanel(ButtonPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	public void setUpForClientUse(JFrame mainFrame) {
		messageBoxCreator = new MessageBoxCreator(mainFrame);
		bookableController = controllerFactory.getContractorController(this);
	}

	@Override
	public void enableBookContratorButton(boolean enabled) {
		buttonPanel.enableBookContratorButton(enabled);
	}

	@Override
	public void displayErrorMessage(String errorMessage, String title) {
		messageBoxCreator.displayErrorMessageBox(errorMessage, title);
	}

	public Map<SettingType, String> getSettings() {
		return settingsController.getSettings();
	}

	public void saveSettingsToController(Map<SettingType, String> settings) {
		settingsController.saveSettings(settings);
	}

	public Map<SettingType, String> loadSettingsFromController() {
		return settingsController.getSettings();
	}

	public void displaySettngsDialogBox() {
		settingsDialogBox.display();
	}

	public void stopDisplayingSettingsDialogBox() {
		settingsDialogBox.stopDisplaying();
	}

	public void saveSettingsDialogBoxSettings() {
		settingsDialogBox.saveSettings();
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

	public String displayInputDialogBox(String request) {
		return messageBoxCreator.displayInputDialog(request);
	}
}
