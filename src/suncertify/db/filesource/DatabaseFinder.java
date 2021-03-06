package suncertify.db.filesource;

import static settings.SettingType.DIRECTORY;

import java.io.File;
import java.util.Map;

import settings.*;

public class DatabaseFinder {

    private static final String FILE_NAME = "db.db";

    /**
     * Gets the database file
     * 
     * @return the database file
     * @throws SettingException
     *             if failed to read the database directory
     */
    File getDatabaseFile() throws SettingsException {
        String directoryName = getDirectoryName();
        return new File(createFileName(directoryName));
    }

    private String getDirectoryName() throws SettingsException {
        SettingsService settingsAccessor = new SettingsFacade();
        Map<SettingType, String> settings = settingsAccessor.loadSettings();
        return settings.get(DIRECTORY);
    }

    private String createFileName(String directoryName) {
        StringBuilder builder = new StringBuilder();
        builder.append(directoryName);
        builder.append("/");
        builder.append(FILE_NAME);
        return builder.toString();
    }
}
