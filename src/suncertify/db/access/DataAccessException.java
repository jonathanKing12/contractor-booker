package suncertify.db.access;

/**
 * Occurs when there is a problem accessing the data source.
 */
public class DataAccessException extends RuntimeException {

    /**
     * Constructs a instance of DataAccessException.
     */
    public DataAccessException() {
        super();
    }

    /**
     * Constructs a instance of DataAccessException with the specified message.
     * 
     * @param message
     *            - the message
     */
    public DataAccessException(String message) {
        super(message);
    }
}
