package gui.view.mergers;

import static gui.view.mergers.ViewMerger.VIEW_MERGER_INSTACE;
import gui.controller.ControllerFactory;
import gui.controller.api.SettableController;
import gui.view.MessageBoxPresenter;
import gui.view.api.SettableView;

import java.util.Map;
import java.util.Set;

import settings.SettingType;

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

	@Override
	public void requestForSettingsTobeSet() {
		VIEW_MERGER_INSTACE.displaySettngsDialogBox();
	}

	public void displaySettingsDialogIfControllerHasNotSettingsOfTypes(Set<SettingType> settings) {
		settingsController.displaySettingsDialogIfHasNotSettingsOfTypes(settings);
	}

	@Override
	public void displayErrorMessage(String errorMessage, String title) {
		messageBoxPresenter.displayErrorMessageBox(errorMessage, title);
	}

	@Override
	public void displaySettingsDialogBox() {
		VIEW_MERGER_INSTACE.displaySettngsDialogBox();
	}
}
