package ui.view.server.state;

public interface State {

    /**
     * Sets the next state of the server to be the specified state after the {@link #doAction(StateContext)} is called
     */
    void setNextState(State nextState);

    /**
     * Returns true if the server is enabled
     * 
     * @return {@true} if the server is enabled
     */
    boolean isServerRunning();

    /**
     * Carries out the following
     * <ul>
     * <li>
     * changes the state of the server (assuming it is not an instance of {@link KeepDisabledState})</li>
     * <li>
     * sets the specified context state to the state that was passed into the {@link #setNextState(State)} method</li>
     * <ul/>
     * 
     * @param context
     *            the context
     */
    void doAction(StateContext context);
}
