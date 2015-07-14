package ui.view.server;

import static java.lang.Boolean.FALSE;

import javax.swing.JToggleButton;

import ui.view.mergers.ServerMerger;

public class DisableState implements State {

	private static final String DISABLE = "disable";
	private State nextState;
	private JToggleButton button;

	DisableState(JToggleButton button) {
		this.button = button;
	}

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
		ServerMerger.getInstance().enable();
		button.setText(DISABLE);
		context.setState(nextState);
	}
}
