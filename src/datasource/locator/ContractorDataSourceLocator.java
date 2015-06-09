package datasource.locator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class ContractorDataSourceLocator implements DataSourceLocator {

	private static final ContractorDataSourceLocator locator;

	private String propertiesFileName = "C:/db/suncertify.properties";
	private String propertyNameAndDilimeter = "root location:";
	private File propertiesFile;

	static {
		locator = new ContractorDataSourceLocator();
	}

	public static ContractorDataSourceLocator getInstance() {
		return locator;
	}

	private ContractorDataSourceLocator() {
		propertiesFile = new File(propertiesFileName);
	}

	@Override
	public void setLocation(String location) throws DataSourceLocationException {
		try (FileWriter writer = new FileWriter(propertiesFile)) {
			write(writer, location);
		} catch (IOException e) {
			throw new DataSourceLocationException(e.getMessage());
		}
	}

	@Override
	public String getLocation() throws DataSourceLocationException {
		try (BufferedReader reader = new BufferedReader(new FileReader(propertiesFile))) {
			return read(reader);
		} catch (IOException e) {
			throw new DataSourceLocationException(e.getMessage());
		}
	}

	private String read(BufferedReader reader) throws IOException {
		String line = reader.readLine();
		String[] property = line.split(propertyNameAndDilimeter);
		return property[1];
	}

	private void write(FileWriter writer, String location) throws IOException {
		StringBuilder builder = new StringBuilder(propertyNameAndDilimeter);
		builder.append(location);
		String property = builder.toString();
		writer.write(property);
	}

	public static void main(String[] args) throws DataSourceLocationException {
		new ContractorDataSourceLocator().setLocation("c:/db/bin/:a");
		System.out.println("done");
		String location = new ContractorDataSourceLocator().getLocation();
		System.out.println("the location is " + location);
	}
}
