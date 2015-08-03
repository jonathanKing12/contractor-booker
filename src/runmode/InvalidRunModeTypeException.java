package runmode;

/**
 * Occurs when the Users specifies a invalid run mode.
 *
 */
public class InvalidRunModeTypeException extends Exception {

    /**
     * creates a new instance of InvalidRunModeTypeException
     */
    public InvalidRunModeTypeException() {
        super();
    }

    /**
     * creates a new instance of InvalidRunModeTypeException with the specified message
     * 
     * @param message
     *            - the message
     */
    public InvalidRunModeTypeException(String message) {
        super(message);
    }
}
