package suncertify.db.filesource;

import static settings.SettingType.DIRECTORY;

import java.io.File;
import java.util.Map;

import settings.SettingsAccessor;
import settings.SettingException;
import settings.SettingType;
import settings.SettableAccessor;

public class DatabaseFinder {

	File getDatabaseFile() throws SettingException {
		SettableAccessor settingsAccessor = new SettingsAccessor();
		Map<SettingType, String> settings = settingsAccessor.getSettings();
		String fileName = settings.get(DIRECTORY);
		return new File(fileName + "/db-write2.db");
	}
}
