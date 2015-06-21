package settings;

import java.util.Map;

public interface AccessSettings {

	void setSettings(Map<SettingType, String> settings) throws SettingException;

	Map<SettingType, String> getSettings() throws SettingException;
}
