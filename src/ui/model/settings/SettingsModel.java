package ui.model.settings;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.util.Map;
import java.util.Set;

import settings.SettableAccessor;
import settings.SettingException;
import settings.SettingType;
import settings.SettingsAccessor;
import ui.controller.api.SettableController;
import ui.model.api.SettableModel;

public class SettingsModel implements SettableModel {

	private SettableAccessor accessSettings;

	public SettingsModel(SettableController controller) {
		accessSettings = new SettingsAccessor();
	}

	@Override
	public boolean doesAnyOfSettingsTypesExist(Set<SettingType> settingTypes)
			throws SettingException {

		boolean hasNoSettings = FALSE;
		Map<SettingType, String> settings = getSettings();

		for (SettingType settingType : settingTypes) {
			if (hasSettingOfType(settings, settingType)) {
				hasNoSettings = TRUE;
				break;
			}
		}
		return hasNoSettings;
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
