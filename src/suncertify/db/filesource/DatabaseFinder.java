package suncertify.db.filesource;

import static settings.SettingType.DIRECTORY;

import java.io.File;
import java.util.Map;

import settings.*;

public class DatabaseFinder {

    private static DatabaseFinder INSTANCE;
    private File dataBaseFile;

    static {
        INSTANCE = new DatabaseFinder();
    }

    private DatabaseFinder() {

    }

    File getDatabaseFile() throws SettingException {
        if (dataBaseFile == null) {
            dataBaseFile = createDatabaseFile();
        }
        return dataBaseFile;
    }

    public static DatabaseFinder getInstance() {
        return INSTANCE;
    }

    private File createDatabaseFile() {
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

    public void clear() {
        dataBaseFile = null;
    }
}
