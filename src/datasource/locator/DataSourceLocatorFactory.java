package datasource.locator;

public class DataSourceLocatorFactory {

	public DataSourceLocator getDataSourceLocator() {
		return ContractorDataSourceLocator.getInstance();
	}
}
