package settings;

import java.io.*;
import java.util.*;

public class PropertiesFileWriter {

    private File settingsFile;

    PropertiesFileWriter(File settingsFile) {
        this.settingsFile = settingsFile;
    }

    List<String> convertToLines(Map<SettingType, String> settings) {
        List<String> lines = new ArrayList<>();

        for (Map.Entry<SettingType, String> setting : settings.entrySet()) {
            String line = convertToLine(setting);
            lines.add(line);
        }
        return lines;
    }

    void writeLines(List<String> lines) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(settingsFile))) {
            writeLines(writer, lines);
        } catch (IOException e) {
            String errorMessage = createErrorMessage(e);
            throw new SettingException(errorMessage);
        }
    }

    private String convertToLine(Map.Entry<SettingType, String> setting) {
        StringBuilder builder = new StringBuilder();

        SettingType settingType = setting.getKey();
        builder.append(settingType);
        builder.append("=");

        String settingValue = setting.getValue();
        builder.append(settingValue);
        return builder.toString();
    }

    private void writeLines(PrintWriter writer, List<String> lines) throws IOException {
        for (String line : lines) {
            writer.println(line);
        }
    }

    private String createErrorMessage(IOException e) {
        String suppressedMessage = createSuppressedMessage(e);
        String message = " error when writing settings \n %s \n %s";
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
