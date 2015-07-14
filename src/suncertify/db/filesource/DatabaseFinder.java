package suncertify.db.filesource;

import static settings.SettingType.DIRECTORY;

import java.io.File;
import java.util.Map;

import settings.SettableAccessor;
import settings.SettingException;
import settings.SettingType;
import settings.SettingsAccessor;

public class DatabaseFinder {

	File getDatabaseFile() throws SettingException {
		String directoryName = getDirectoryName();
		return new File(createFileName(directoryName));
	}

	private String getDirectoryName() {
		SettableAccessor settingsAccessor = new SettingsAccessor();
		Map<SettingType, String> settings = settingsAccessor.getSettings();
		return settings.get(DIRECTORY);
	}

	private String createFileName(String directoryName) {
		StringBuilder builder = new StringBuilder();
		builder.append(directoryName);
		builder.append("/");
		builder.append("db-write2.db");
		return builder.toString();
	}
}
