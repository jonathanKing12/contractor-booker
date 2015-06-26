package settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsParser {

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
			SettingType settingType = getSettingType(setting);
			String settingValue = getSettingValue(setting);
			settings.put(settingType, settingValue);
		}
		return settings;
	}

	private SettingType getSettingType(String[] setting) {
		return SettingType.valueOf(setting[0]);
	}

	private String getSettingValue(String[] setting) {
		if (setting.length == 2)
			return setting[1];
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
