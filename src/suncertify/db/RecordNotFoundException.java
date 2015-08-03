package suncertify.db;

/**
 * Occurs when a Record does not exist or is marked as deleted in the database.
 */
public class RecordNotFoundException extends Exception {

    /**
     * Constructs a instance of RecordNotFoundException
     */
    public RecordNotFoundException() {
        super();
    }

    /**
     * Constructs a instance of RecordNotFoundException with the specified message
     * 
     * @param message
     *            - the message
     */
    public RecordNotFoundException(String message) {
        super(message);
    }
}
