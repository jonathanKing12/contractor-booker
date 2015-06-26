package gui.view.api;

import java.util.Set;

import settings.SettingType;

public interface SettableTab {

	void saveSettings();

	String getTtile();

	void loadSettings();

	Set<SettingType> getSettingsTypes();
}
