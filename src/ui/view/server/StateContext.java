package ui.view.server;

public class StateContext {

	private State currentState;

	StateContext(State currentState) {
		this.currentState = currentState;
	}

	boolean isCurrentStateEnabled() {
		return currentState.isEnabled();
	}

	void doAction() {
		currentState.doAction(this);
	}

	void setState(State currentState) {
		this.currentState = currentState;
	}
}
