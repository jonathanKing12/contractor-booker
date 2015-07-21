package suncertify.db.datasource;

public interface DataSourceFactory {

    /**
     * Creates a DataSourceReader instance
     * 
     * @return - the DataSourceReader
     */
    DataSourceReader getDataSourceReader();

    /**
     * Creates a DataSourceWriter instance
     * 
     * @return - the DataSourceWriter
     */
    DataSourceWriter getDataSourceWritter();
}
