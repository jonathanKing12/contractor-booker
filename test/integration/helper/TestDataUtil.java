package integration.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestDataUtil {

	public static void backupFile(String fileName) throws IOException {
		File source = getSourceFile(fileName);
		File destination = getDestinationFile(fileName);
		copySoureToDestination(source, destination);
	}

	public static void restorFile(String fileName) throws IOException {
		File source = getSourceFile(fileName);
		deleteIfExists(source);

		File destination = getDestinationFile(fileName);
		copySoureToDestination(destination, source);

		deleteIfExists(destination);
	}

	private static File getSourceFile(String fileName) {
		File source = new File("C:/db/" + fileName + ".db");
		return source;
	}

	private static File getDestinationFile(String fileName) {
		File destination = new File("C:/db/" + fileName + "_bk.db");
		return destination;
	}

	private static void copySoureToDestination(File source, File destination) throws IOException {
		Files.copy(source.toPath(), destination.toPath());
	}

	private static void deleteIfExists(File destination) throws IOException {
		Files.deleteIfExists(destination.toPath());
	}

}
