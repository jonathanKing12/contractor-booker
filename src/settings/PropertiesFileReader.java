package settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertiesFileReader {

	private File settingsFile;

	PropertiesFileReader(File settingsFile) {
		this.settingsFile = settingsFile;
	}
	
	Map<SettingType, String> convertToSettings(List<String> lines) {
		Map<SettingType, String> settings = new HashMap<>();

		for (String line : lines) {
			String[] setting = line.split("=");
			SettingType settingType = SettingType.valueOf(setting[0]);

			String settingValue = "";
			if (setting.length == 2)
				settingValue = setting[1];
			settings.put(settingType, settingValue);
		}
		return settings;
	}

	 List<String> readLines() throws SettingException {

		try (BufferedReader reader = getBufferedReader()) {
			return readLines(reader);
		} catch (IOException e) {
			String errorMessage = createErrorMessage(e);
			throw new SettingException(errorMessage);
		}
	}

	private BufferedReader getBufferedReader() throws FileNotFoundException {
		return new BufferedReader(new FileReader(settingsFile));
	}

	private List<String> readLines(BufferedReader reader) throws IOException {
		List<String> lines = new ArrayList<>();

		String line;
		while ((line = reader.readLine()) != null) {
			lines.add(line);
		}
		return lines;
	}

	private String createErrorMessage(IOException e) {
		String suppressedMessage = createSuppressedMessage(e);
		String message = " error when reading settings \n %s \n %s";
		return String.format(message, e.getMessage(), suppressedMessage);
	}

	private String createSuppressedMessage(IOException e) {
		StringBuilder builder = new StringBuilder();
		for (Throwable suppressedException : e.getSuppressed()) {
			builder.append(suppressedException.getMessage());
			builder.append("\n");
		}
		return builder.toString();
	}
}
