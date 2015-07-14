package ui.view.server;

import static java.lang.Boolean.TRUE;

import javax.swing.JToggleButton;

import ui.view.mergers.ServerMerger;

public class EnableState implements State {

	private static final String ENABLE = "enable";
	private State nextState;
	private JToggleButton button;

	EnableState(JToggleButton button) {
		this.button = button;
	}

	@Override
	public void setNextState(State nextState) {
		this.nextState = nextState;
	}

	@Override
	public boolean isEnabled() {
		return TRUE;
	}

	@Override
	public void doAction(StateContext context) {
		ServerMerger.getInstance().disable();
		button.setText(ENABLE);
		context.setState(nextState);
	}
}
