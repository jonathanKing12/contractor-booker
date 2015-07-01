package settings;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.util.*;

public class SettingsParser {

    /**
     * 
     */
    private static final int SETTING_TYPE_VALUE = 1;
    /**
     * 
     */
    private static final int SETTING_TYPE_INDEX = 0;
    private static final String EMPTY_VALUE = "";
    private static final String PROPERTY_DELIMETER = "=";

    List<String> convertToProperties(Map<SettingType, String> settings) {
        List<String> properties = new ArrayList<>();

        for (Map.Entry<SettingType, String> setting : settings.entrySet()) {
            String property = convertToProperty(setting);
            properties.add(property);
        }
        return properties;
    }

    Map<SettingType, String> convertToSettings(List<String> properties) {
        Map<SettingType, String> settings = new HashMap<>();

        for (String property : properties) {
            String[] setting = property.split(PROPERTY_DELIMETER);

            if (!hasSettingType(setting)) {
                continue;
            }

            SettingType settingType = getSettingType(setting);
            String settingValue = getSettingValue(setting);
            settings.put(settingType, settingValue);
        }
        return settings;
    }

    private boolean hasSettingType(String[] setting) {
        boolean hasSetting = FALSE;

        for (SettingType type : SettingType.values()) {
            if (type.toString().equals(setting[SETTING_TYPE_INDEX])) {
                hasSetting = TRUE;
            }
        }
        return hasSetting;
    }

    private SettingType getSettingType(String[] setting) {
        return SettingType.valueOf(setting[SETTING_TYPE_INDEX]);
    }

    private String getSettingValue(String[] setting) {
        if (setting.length == 2)
            return setting[SETTING_TYPE_VALUE];

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
