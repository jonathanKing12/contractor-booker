package ui.view.server.state;

import static constants.Constants.SERVER_IS_STOPPED;
import static constants.Constants.START_SERVER;
import static java.lang.Boolean.TRUE;
import ui.view.server.ServerButtonHandler;
import ui.view.server.ServerMediator;

public class StartState implements State {

    private ServerButtonHandler serverButtonHandler;
    private ServerMediator serverMediator;
    private State stopState;

    /**
     * Constructs an instance of StartState with the specified serverButtonHandler.
     * 
     * @param serverButtonHandler
     *            - the serverButtonHandler
     */
    public StartState(ServerButtonHandler serverButtonHandler) {
        this.serverButtonHandler = serverButtonHandler;
        serverMediator = ServerMediator.getInstance();
    }

    /**
     * Sets the next state of the server to be the specified state after the {@link #doAction(StateContext)} is called
     */
    @Override
    public void setNextState(State nextState) {
        this.stopState = nextState;
    }

    /**
     * Returns {@code true} as the server is currently running.
     * 
     * @return {@code true} as the server is currently running
     */
    @Override
    public boolean isServerRunning() {
        return TRUE;
    }

    /**
     * Carries out the following
     * <ul>
     * <li>
     * Stops the server</li>
     * <li>
     * Updates the server screen to inform the user that the server is stopped'</li>
     * <li>
     * sets the specified context state to the state that was passed into the {@link #setNextState(State)} method</li>
     * <ul/>
     * 
     */
    @Override
    public void doAction(StateContext context) {
        if (serverMediator.stopServer()) {
            serverButtonHandler.setServerButtonText(START_SERVER);
            serverButtonHandler.setServerMessageText(SERVER_IS_STOPPED);
            context.setState(stopState);
        }

    }
}
