package ui.view.mergers;

import java.util.Map;
import java.util.Set;

import settings.SettingType;
import ui.controller.ControllerFactory;
import ui.controller.api.SettableController;
import ui.view.MessageBoxPresenter;
import ui.view.api.SettableView;

public class SettableMerger implements SettableView {

	private static SettableMerger settableMerger;
	private SettableController settingsController;
	private MessageBoxPresenter messageBoxPresenter;

	private SettableMerger() {
		ControllerFactory factory = new ControllerFactory();
		settingsController = factory.getClientSettingsController(this);
		messageBoxPresenter = MessageBoxPresenter.getInstance();
	}

	public static SettableMerger getInstance() {
		if (settableMerger == null) {
			settableMerger = new SettableMerger();
		}
		return settableMerger;
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

	// @Override
	// public void requestForSettingsTobeSet() {
	// ViewMerger.getInstance().displaySettngsDialogBox();
	// }

	public void displaySettingsDialogIfControllerHasNotSettingsOfTypes(Set<SettingType> settings) {
		settingsController.displaySettingsDialogIfNoSettingsTypesExist(settings);
	}

	@Override
	public void displayErrorMessage(String errorMessage, String title) {
		messageBoxPresenter.displayErrorMessageBox(errorMessage, title);
	}

	@Override
	public void displaySettingsDialogBox() {
		ParentTracker.getInstance().displaySettngsDialogBox();
	}
}
