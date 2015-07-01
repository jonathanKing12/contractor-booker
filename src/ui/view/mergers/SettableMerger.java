package ui.view.mergers;

import java.util.Map;
import java.util.Set;

import settings.SettingType;
import ui.controller.ControllerFactory;
import ui.controller.api.SettableController;
import ui.view.MessageBoxPresenter;
import ui.view.api.SettableView;

public class SettableMerger implements SettableView {

    private SettableController settingsController;

    public SettableMerger() {
        ControllerFactory factory = new ControllerFactory();
        settingsController = factory.getClientSettingsController(this);
    }

    @Override
    public Map<SettingType, String> loadSettings() {
        return settingsController.getSettings();
    }

    @Override
    public void saveSettings(Map<SettingType, String> settings) {
        settingsController.saveSettings(settings);
    }

    @Override
    public boolean isAllSettingsMissing(Set<SettingType> settings) {
        return settingsController.isAllSettingsMissing(settings);
    }

    @Override
    public void displayErrorMessage(String errorMessage, String title) {
        MessageBoxPresenter presenter = new MessageBoxPresenter();
        presenter.displayErrorMessageBox(errorMessage, title);
    }
}
