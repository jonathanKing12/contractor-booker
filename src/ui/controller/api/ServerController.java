package ui.controller.api;

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
