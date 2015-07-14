package ui.view.server;

import static java.lang.Boolean.FALSE;

public class KeepDisabledState implements State {

	private State nextState;

	@Override
	public void setNextState(State nextState) {
		this.nextState = nextState;
	}

	@Override
	public boolean isEnabled() {
		return FALSE;
	}

	@Override
	public void doAction(StateContext context) {
		context.setState(nextState);
	}
}
