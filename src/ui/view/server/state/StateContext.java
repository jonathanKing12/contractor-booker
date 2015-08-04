package ui.view.server.state;

/**
 * Keeps track of the current state of the server.
 */
public class StateContext {

    private State currentState;

    /**
     * Constructs a instance of the StateContext with the specified currentState;
     * 
     * @param currentState
     *            - the currentState
     */
    public StateContext(State currentState) {
        this.currentState = currentState;
    }

    /**
     * Returns {@code true} if the server is enabled.
     * 
     * <p>
     * More specifically delegates to the {@link State#isServerRunning()} method of the currentState that was passed to the
     * {@link #setState(currentState)}
     * 
     * <p>
     * 
     * @return {@code true} if the server is enabled
     */
    public boolean isServerRunning() {
        return currentState.isServerRunning();
    }

    /**
     * invokes the {@link State#doAction} method on currentState instance that was passed into the {@link #setState(currentState)} method
     */
    public void doAction() {
        currentState.doAction(this);
    }

    /**
     * Sets the current state of the server to be the specified currentState
     */
    public void setState(State currentState) {
        this.currentState = currentState;
    }
}
