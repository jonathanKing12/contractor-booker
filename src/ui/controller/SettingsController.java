package ui.controller;

import static java.lang.Boolean.TRUE;

import java.util.Map;
import java.util.Set;

import settings.SettingException;
import settings.SettingType;
import ui.controller.api.SettableController;
import ui.model.api.SettableModel;
import ui.model.settings.SettingsModel;
import ui.view.api.SettableView;

public class SettingsController implements SettableController {

    private SettableModel model;
    private SettableView view;

    public SettingsController(SettableView view) {
        model = new SettingsModel(this);
        this.view = view;
    }

    @Override
    public void saveSettings(Map<SettingType, String> settings) {
        model.setSettings(settings);
    }

    @Override
    public Map<SettingType, String> getSettings() {
        return model.getSettings();
    }

    @Override
    public boolean isAllSettingsMissing(Set<SettingType> settingsTypes) {
        boolean allSettingsMissing = TRUE;
        try {
            allSettingsMissing = model.isAllSettingsMissing(settingsTypes);
        } catch (SettingException e) {
            view.displayErrorMessage(e.getMessage(), "error");
        }
        return allSettingsMissing;
    }
}
