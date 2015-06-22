package settings;

import static org.junit.Assert.assertTrue;
import static settings.SettingType.DIRECTORY;
import static settings.SettingType.IP_ADDRESS;
import static settings.SettingType.PORT_NUMBER;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class DataSourceSettingReaderTest {

	private File settingsFile;
	private DataSourceSettingsReader reader;

	@Test
	public void shouldReadNoProperties() {
		reader = createReader("zeroProperties");
		Map<SettingType, String> settings = reader.getSettings();
		assertTrue(settings.isEmpty());
	}

	@Test
	public void shouldReadOneProperties() {
		reader = createReader("oneProperty");
		Map<SettingType, String> actualSettings = reader.getSettings();
		assertTrue(actualSettings.size() == 1);

		Map<SettingType, String> expectedSettings = new HashMap<>();
		expectedSettings.put(DIRECTORY, "c:/location to the directory 123 %- ");

		assertMapsAreEqual(expectedSettings, actualSettings);
	}

	@Test
	public void shouldReadAllProperties() {
		reader = createReader("allProperties");

		Map<SettingType, String> actualSettings = reader.getSettings();
		assertTrue(actualSettings.size() == 3);

		Map<SettingType, String> expectedSettings = new HashMap<>();
		expectedSettings.put(DIRECTORY, "c:/location to the directory 123 %-");
		expectedSettings.put(PORT_NUMBER, "8084");
		expectedSettings.put(IP_ADDRESS, "1.2.123.0");
		assertMapsAreEqual(expectedSettings, actualSettings);
	}

	@Test(expected = SettingException.class)
	public void shouldFail() {
		try {
			reader = createReader("I do not exist");
			reader.getSettings();
		} catch (SettingException e) {
			throw e;
		}
	}

	private DataSourceSettingsReader createReader(String fileName) {
		settingsFile = new File("C:/db/" + fileName + ".properties");
		return new DataSourceSettingsReader(settingsFile);
	}

	private void assertMapsAreEqual(Map<SettingType, String> expected, Map<SettingType, String> actual) {
		assertTrue(expected.entrySet().containsAll(actual.entrySet()));
	}
}
