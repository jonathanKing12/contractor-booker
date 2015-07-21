package suncertify.db.record.lock;

public class LockInterruptException extends RuntimeException {

    /**
     * creates a new instance of LockInterruptException
     */
    public LockInterruptException() {
        super();
    }

    /**
     * creates a new instance of LockInterruptException with the specified message
     * 
     * @param message
     *            - the message
     */
    public LockInterruptException(String message) {
        super(message);
    }
}
