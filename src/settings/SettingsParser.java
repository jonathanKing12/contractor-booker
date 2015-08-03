package settings;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.util.*;

/**
 * Parses the settings to properties and the properties to settings
 *
 */
public class SettingsParser {

    private static final String EMPTY_VALUE = "";
    private static final int SETTING_TYPE_INDEX = 0;
    private static final int SETTING_VALUE_INDEX = 1;
    private static final String PROPERTY_DELIMETER = "=";
    private static final int SIZE_OF_NON_EMPTY_SETTING = 2;

    /**
     * Converts the specified settings into properties
     * 
     * @param settings
     *            - the settings
     * @return the properties
     */
    List<String> convertToProperties(Map<SettingType, String> settings) {
        List<String> properties = new ArrayList<>();

        for (Map.Entry<SettingType, String> setting : settings.entrySet()) {
            String property = convertToProperty(setting);
            properties.add(property);
        }
        return properties;
    }

    /**
     * Converts the specified properties into settings
     * 
     * @param properties
     *            - the properties
     * @return the settings
     */
    Map<SettingType, String> convertToSettings(List<String> properties) {
        Map<SettingType, String> settings = new HashMap<>();

        for (String property : properties) {
            String[] setting = property.split(PROPERTY_DELIMETER);

            if (!isSettingTypeValid(setting)) {
                continue;
            }

            SettingType settingType = getSettingType(setting);
            String settingValue = getSettingValue(setting);
            settings.put(settingType, settingValue);
        }
        return settings;
    }

    private boolean isSettingTypeValid(String[] setting) {
        boolean isSettingTypeValid = FALSE;

        for (SettingType settingType : SettingType.values()) {
            if (isSettingHaveSettingType(setting, settingType)) {
                isSettingTypeValid = TRUE;
            }
        }
        return isSettingTypeValid;
    }

    private boolean isSettingHaveSettingType(String[] setting, SettingType settingType) {
        String actualSettingType = setting[SETTING_TYPE_INDEX];
        String expectedSettingType = settingType.toString();
        return expectedSettingType.equals(actualSettingType);
    }

    private SettingType getSettingType(String[] setting) {
        return SettingType.valueOf(setting[SETTING_TYPE_INDEX]);
    }

    private String getSettingValue(String[] setting) {
        if (setting.length == SIZE_OF_NON_EMPTY_SETTING)
            return setting[SETTING_VALUE_INDEX];

        return EMPTY_VALUE;
    }

    private String convertToProperty(Map.Entry<SettingType, String> setting) {
        StringBuilder builder = new StringBuilder();

        SettingType settingType = setting.getKey();
        builder.append(settingType);
        builder.append(PROPERTY_DELIMETER);

        String settingValue = setting.getValue();
        builder.append(settingValue);
        return builder.toString();
    }
}
