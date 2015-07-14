package ui.view.server;

public interface State {

	void setNextState(State nextState);

	boolean isEnabled();

	void doAction(StateContext context);
}
