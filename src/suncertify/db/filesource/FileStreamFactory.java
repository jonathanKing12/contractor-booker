package suncertify.db.filesource;

import suncertify.db.datasource.DataSourceFactory;

public class FileStreamFactory implements DataSourceFactory {

    /**
     * Creates a FileStreamReader instance
     * 
     * @return - the FileStreamReader
     */
    @Override
    public FileStreamReader getDataSourceReader() {
        return new FileStreamReader();
    }

    /**
     * Creates a FileStreamWriter instance
     * 
     * @return - the FileStreamWriter
     */
    @Override
    public FileStreamWriter getDataSourceWritter() {
        return new FileStreamWriter();
    }
}
