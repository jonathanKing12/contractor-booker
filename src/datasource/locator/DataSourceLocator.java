package datasource.locator;



public interface DataSourceLocator {

	void setLocation(String location) throws DataSourceLocationException;

	String getLocation() throws DataSourceLocationException;

}
