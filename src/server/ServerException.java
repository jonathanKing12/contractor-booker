package server;

/**
 * Occurs when the server fails to start or stop.
 */
public class ServerException extends Exception {

    /**
     * Constructs a instance of ServerException.
     */
    public ServerException() {
        super();
    }

    /**
     * Constructs a instance of ServerException with the .specified message
     * 
     * @param message
     *            - the message
     */
    public ServerException(String message) {
        super(message);
    }
}
