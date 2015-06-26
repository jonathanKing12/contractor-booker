package gui.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

import server.Server;

public class ControllPanel extends JPanel {

	private static final String DISABLE = "disable";
	private static final String ENABLE = "enable";
	private JToggleButton enableDisableSwitch;
	private boolean isEnabled;
	private Server server;

	public ControllPanel() {
		server = new Server();
		this.setBackground(Color.cyan);
		enableDisableSwitch = new JToggleButton(ENABLE);
		this.add(enableDisableSwitch);
		setEnableDisableSwitchActionListener();
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

	private void changedOnOffButtonText() {
		String text = isEnabled ? DISABLE : ENABLE;
		enableDisableSwitch.setText(text);
	}

	private void enableServer() {
		isEnabled = !isEnabled;
		changedOnOffButtonText();

		if (isEnabled) {
			server.enable();
		} else {
			server.disable();
		}
	}
}
