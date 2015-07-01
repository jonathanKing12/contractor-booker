package ui.model.settings;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.util.Map;
import java.util.Set;

import settings.*;
import ui.controller.api.SettableController;
import ui.model.api.SettableModel;

public class SettingsModel implements SettableModel {

    private SettableAccessor accessSettings;

    public SettingsModel(SettableController controller) {
        accessSettings = new SettingsAccessor();
    }

    @Override
    public boolean isAllSettingsMissing(Set<SettingType> settingTypes) throws SettingException {

        boolean allSettingsMissing = TRUE;
        Map<SettingType, String> settings = getSettings();

        for (SettingType settingType : settingTypes) {
            if (hasSettingOfType(settings, settingType)) {
                allSettingsMissing = FALSE;
                break;
            }
        }
        return allSettingsMissing;
    }

    @Override
    public void setSettings(Map<SettingType, String> settings) {
        accessSettings.setSettings(settings);
    }

    @Override
    public Map<SettingType, String> getSettings() throws SettingException {
        return accessSettings.getSettings();
    }

    private boolean hasSettingOfType(Map<SettingType, String> settings, SettingType settingType) {
        String settingValue = settings.get(settingType);
        return !settingValue.isEmpty();
    }
}
