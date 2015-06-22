package settings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static settings.SettingType.DIRECTORY;
import static settings.SettingType.IP_ADDRESS;
import static settings.SettingType.PORT_NUMBER;
import integration.helper.TestDataUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataSourceSettingsWriterTest {

	private PropertiesFileWriter writer;

	@Before
	public void setUp() throws IOException {
		TestDataUtil.backupPropertiesFile("writeproperties");
		writer = new PropertiesFileWriter(new File("C:/db/writeproperties.properties"));
	}

	@Test
	public void shouldWriteOneSetting() throws SettingException, FileNotFoundException, IOException {
		Map<SettingType, String> settings = new HashMap<>();
		settings.put(DIRECTORY, "testing123");
		writer.writeSettings(settings);
		assertLinesWrittenToFile(Arrays.asList("DIRECTORY=testing123"));
	}

	@Test
	public void shouldWriteAllSetting() throws SettingException, FileNotFoundException, IOException {
		Map<SettingType, String> settings = new HashMap<>();
		settings.put(DIRECTORY, "old directory");
		settings.put(PORT_NUMBER, "6789");
		settings.put(IP_ADDRESS, "1.1.1.1");

		writer.writeSettings(settings);

		List<String> expectedLines = Arrays.asList("DIRECTORY=old directory", "PORT_NUMBER=6789", "IP_ADDRESS=1.1.1.1");
		assertLinesWrittenToFile(expectedLines);
	}

	@After
	public void tearDown() throws IOException {
		TestDataUtil.restorPropertiesFile("writeproperties");
	}

	private void assertLinesWrittenToFile(List<String> expectedlines) throws SettingException, FileNotFoundException, IOException {
		String line;
		int i = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader("C:/db/writeproperties.properties"))) {
			while ((line = reader.readLine()) != null) {
				i++;
				assertTrue(expectedlines.contains(line));
			}
		}
		assertEquals(i, expectedlines.size());
	}
}
