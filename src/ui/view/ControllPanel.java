package ui.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

import ui.view.mergers.ServerMerger;

public class ControllPanel extends JPanel {

	private static final String DISABLE = "disable";
	private static final String ENABLE = "enable";
	private JToggleButton enableDisableSwitch;
	private boolean isEnabled;

	public ControllPanel() {
		this.setBackground(Color.cyan);
		enableDisableSwitch = new JToggleButton(ENABLE);
		setEnableDisableSwitchActionListener();
		this.add(enableDisableSwitch);
	}

	private void setEnableDisableSwitchActionListener() {
		ActionListener listener = getActionListener();
		enableDisableSwitch.addActionListener(listener);
	}

	private ActionListener getActionListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				enableServer();
			}
		};
	}

	private void changedButtonText() {
		String text = isEnabled ? DISABLE : ENABLE;
		enableDisableSwitch.setText(text);
	}

	private void enableServer() {
		isEnabled = !isEnabled;
		changedButtonText();

		if (isEnabled) {
			ServerMerger.getInstance().enable();
		} else {
			ServerMerger.getInstance().disable();
		}
	}
}
