package ui.model.api;

import java.util.Map;
import java.util.Set;

import settings.SettingType;

public interface SettableModel {

	void setSettings(Map<SettingType, String> settings);

	Map<SettingType, String> getSettings();

	boolean isAllSettingsMissing(Set<SettingType> settings);
}
