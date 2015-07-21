package suncertify.db.record.lock;

public class InvalidLockCookieException extends SecurityException {

    /**
     * creates a new instance of InvalidLockCookieException
     */
    public InvalidLockCookieException() {
        super();
    }

    /**
     * creates a new instance of InvalidLockCookieException with the specified message
     * 
     * @param message
     *            - the message
     */
    public InvalidLockCookieException(String message) {
        super(message);
    }
}
