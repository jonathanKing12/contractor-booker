package ui.view.mergers;

import java.util.Map;
import java.util.Set;

import settings.SettingType;
import ui.controller.ControllerFactory;
import ui.controller.api.SettableController;
import ui.view.MessageBoxPresenter;
import ui.view.ParentTracker;
import ui.view.api.SettableView;

public class SettableMerger implements SettableView {

	private static SettableMerger INSTANCE;
	private SettableController settingsController;

	static {
		INSTANCE = new SettableMerger();
	}

	private SettableMerger() {
		ControllerFactory factory = new ControllerFactory();
		settingsController = factory.getClientSettingsController(this);
	}

	public static SettableMerger getInstance() {
		return INSTANCE;
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

	public void displaySettingsDialogIfControllerHasNotSettingsOfTypes(Set<SettingType> settings) {
		settingsController.displaySettingsDialogIfNoSettingsTypesExist(settings);
	}

	@Override
	public void displayErrorMessage(String errorMessage, String title) {
		MessageBoxPresenter presenter = new MessageBoxPresenter();
		presenter.displayErrorMessageBox(errorMessage, title);
	}

	@Override
	public void displaySettingsDialogBox() {
		ParentTracker.getInstance().displaySettngsDialogBox();
	}
}
