package settings;

import java.util.Map;

public interface SettableAccessor {

	void setSettings(Map<SettingType, String> settings) throws SettingException;

	Map<SettingType, String> getSettings() throws SettingException;
}
