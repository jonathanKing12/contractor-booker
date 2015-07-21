package suncertify.db.datasource;

public class DataSourceException extends Exception {

    /**
     * creates a new instance of DataSourceException
     */
    public DataSourceException() {
        super();
    }

    /**
     * creates a new instance of DataSourceException with the specified message
     * 
     * @param message
     *            - the message
     */
    public DataSourceException(String message) {
        super(message);
    }
}
