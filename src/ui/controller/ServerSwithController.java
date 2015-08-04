package ui.controller;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import server.Server;
import server.ServerException;
import ui.controller.api.ServerController;
import ui.view.common.MessageBoxPresenterView;

/**
 * Forwards request to the Server instance.
 */
public class ServerSwithController implements ServerController {

    private static final String SERVER_ERROR_TITLE = "Server error";
    private MessageBoxPresenterView messageBoxPresenterView;
    private Server server;

    /**
     * Constructs a instance of ServerSwithController with the specified serverView
     * 
     * @param messageBoxPresenterView
     *            - the messageBoxPresenterView
     */
    public ServerSwithController(MessageBoxPresenterView messageBoxPresenterView) {
        this.messageBoxPresenterView = messageBoxPresenterView;
        server = new Server();
    }

    /**
     * Starts the server.
     * 
     * <p>
     * Delegates the call to {@link Server#start()} if a {@link ServerException} is thrown then
     * {@link MessageBoxPresenterView#displayErrorMessage(message, title)} is invoked.
     * </p>
     * 
     * @return {@code true} if the server has successfully started.
     */
    @Override
    public boolean startServer() {
        try {
            server.start();
            return TRUE;
        } catch (ServerException e) {
            displayErrorMessage(e);
        }
        return FALSE;
    }

    /**
     * Stops the server.
     * 
     * <p>
     * Delegates the call to {@link Server#stop()} if a {@link ServerException} is thrown then
     * {@link MessageBoxPresenterView#displayErrorMessage(message, title)} is invoked.
     * </p>
     * 
     * @return {@code true} if the server has successfully stopped.
     */
    @Override
    public boolean stopServer() {
        try {
            server.stop();
            return TRUE;
        } catch (ServerException e) {
            displayErrorMessage(e);
        }
        return FALSE;
    }

    private void displayErrorMessage(ServerException e) {
        messageBoxPresenterView.displayErrorMessageDialog(e.getMessage(), SERVER_ERROR_TITLE);
    }
}
