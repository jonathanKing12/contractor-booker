package integration.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import settings.DataSourceSettingAccessor;
import settings.SettingType;

public class TestDataUtil {

	public static void backupDbFile(String fileName) throws IOException {
		Map<SettingType, String> settings = new HashMap<>();
		settings.put(SettingType.DIRECTORY, fileName + ".db");
		new DataSourceSettingAccessor().setSettings(settings);
		backupFile(fileName, ".db");
	}

	public static void backupPropertiesFile(String fileName) throws IOException {
		backupFile(fileName, ".properties");
	}

	public static void restorDbFile(String fileName) throws IOException {
		restorFile(fileName, ".db");
	}

	public static void restorPropertiesFile(String fileName) throws IOException {
		restorFile(fileName, ".properties");
	}

	private static void backupFile(String fileName, String extendsion) throws IOException {
		File source = getSourceFile(fileName + extendsion);
		File destination = getDestinationFile(fileName + "_bk" + extendsion);
		copySoureToDestination(source, destination);
	}

	private static void restorFile(String fileName, String extension) throws IOException {
		File source = getSourceFile(fileName + extension);
		deleteIfExists(source);

		File destination = getDestinationFile(fileName + "_bk" + extension);
		copySoureToDestination(destination, source);

		deleteIfExists(destination);
	}

	private static File getSourceFile(String fileName) {
		File source = new File("C:/db/" + fileName);
		return source;
	}

	private static File getDestinationFile(String fileName) {
		File destination = new File("C:/db/" + fileName);
		return destination;
	}

	private static void copySoureToDestination(File source, File destination) throws IOException {
		Files.copy(source.toPath(), destination.toPath());
	}

	private static void deleteIfExists(File destination) throws IOException {
		Files.deleteIfExists(destination.toPath());
	}
}
