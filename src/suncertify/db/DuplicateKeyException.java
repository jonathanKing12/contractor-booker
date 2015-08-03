package suncertify.db;

/**
 * Occurs when two Records have the same name and location.
 */
public class DuplicateKeyException extends Exception {

    /**
     * Constructs a instance of DuplicateKeyException
     */
    public DuplicateKeyException() {
        super();
    }

    /**
     * Constructs a instance of DuplicateKeyException with the specified message
     * 
     * @param message
     *            - the message
     */
    public DuplicateKeyException(String message) {
        super(message);
    }
}
