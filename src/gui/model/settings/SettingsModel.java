package gui.model.settings;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import gui.controller.api.SettableController;
import gui.model.api.SettableModel;

import java.util.Map;
import java.util.Set;

import settings.SettableAccessor;
import settings.SettingException;
import settings.SettingType;
import settings.SettingsAccessor;

public class SettingsModel implements SettableModel {

	private SettableAccessor accessSettings;

	public SettingsModel(SettableController controller) {
		accessSettings = new SettingsAccessor();

	}

	@Override
	public boolean hasNoSettingsOfTypes(Set<SettingType> settingTypes) throws SettingException {

		boolean hasNoSettings = TRUE;
		Map<SettingType, String> settings = getSettings();

		for (SettingType settingType : settingTypes) {
			if (hasSettingOfType(settings, settingType)) {
				hasNoSettings = FALSE;
				break;
			}
		}
		return hasNoSettings;
	}

	private boolean hasSettingOfType(Map<SettingType, String> settings, SettingType settingType) {
		String settingValue = settings.get(settingType);
		return !settingValue.isEmpty();
	}

	@Override
	public void setSettings(Map<SettingType, String> settings) {
		accessSettings.setSettings(settings);
	}

	@Override
	public Map<SettingType, String> getSettings() throws SettingException {
		return accessSettings.getSettings();
	}
}
