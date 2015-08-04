package ui.view.server.state;

import static java.lang.Boolean.FALSE;
import ui.view.server.ServerButtonHandler;
import ui.view.server.ServerMediator;
import constants.Constants;

/**
 * Used to indicate the Server is stopped.
 */
public class StopState implements State {

    private ServerButtonHandler serverButtonHandler;
    private ServerMediator serverMediator;
    private State enableState;

    /**
     * Constructs an instance of DisableState with the specified serverButtonHandler.
     * 
     * @param serverButtonHandler
     *            - the serverButtonHandler
     */
    public StopState(ServerButtonHandler serverButtonHandler) {
        this.serverButtonHandler = serverButtonHandler;
        serverMediator = ServerMediator.getInstance();
    }

    /**
     * Sets the next state of the server to be the specified state after the {@link #doAction(StateContext)} is called
     */
    @Override
    public void setNextState(State enableState) {
        this.enableState = enableState;
    }

    /**
     * Returns {@code false} as the server is currently stopped
     * 
     * @return {@code false} as the server is currently stopped
     */
    @Override
    public boolean isServerRunning() {
        return FALSE;
    }

    /**
     * Carries out the following
     * <ul>
     * <li>
     * Starts the server</li>
     * <li>
     * Updates the server screen to inform the user that the server is stopped'</li>
     * <li>
     * sets the specified context state to the state that was passed into the {@link #setNextState(State)} method</li>
     * <ul/>
     * 
     */
    @Override
    public void doAction(StateContext context) {
        if (!serverMediator.startServer()) {
            return;
        }
        serverButtonHandler.setServerButtonText(Constants.STOP_SERVER);
        serverButtonHandler.setServerMessageText(Constants.SERVER_IS_RUNNING);
        context.setState(enableState);
    }
}
