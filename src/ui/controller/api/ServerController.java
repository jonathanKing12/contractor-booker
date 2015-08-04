package ui.controller.api;

/**
 * Represents a Controller for handling the Server.
 */
public interface ServerController {

    /**
     * Enables the server.
     */
    boolean startServer();

    /**
     * Disables the server.
     */
    boolean stopServer();
}
