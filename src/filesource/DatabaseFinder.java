package filesource;

import static settings.SettingType.DIRECTORY;

import java.io.File;
import java.util.Map;

import settings.DataSourceSettingAccessor;
import settings.SettingsAccessor;
import settings.SettingException;
import settings.SettingType;

public class DatabaseFinder {

	File getDatabaseFile() throws SettingException {
		SettingsAccessor settingsAccessor = new DataSourceSettingAccessor();
		Map<SettingType, String> settings = settingsAccessor.getSettings();
		String fileName = settings.get(DIRECTORY);
		return new File(fileName);
	}
}
