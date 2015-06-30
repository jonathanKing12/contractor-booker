package ui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import ui.view.mergers.ParentTracker;

public class MenuBar extends JMenuBar {

	private JMenuItem settings;
	private JMenuItem exit;
	private JMenu file;

	public MenuBar() {
		setUpFileMenu();
		addActionListeners();
		this.add(file);
	}

	private void setUpFileMenu() {
		file = new JMenu("file");
		file.setMnemonic(KeyEvent.VK_F);
		settings = new JMenuItem("settings");
		settings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		exit = new JMenuItem("exit");
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		file.add(settings);
		file.add(exit);
	}

	private void addActionListeners() {
		settings.addActionListener(getSettingsActionListener());
		exit.addActionListener(getExitActionListener());
	}

	private ActionListener getSettingsActionListener() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ParentTracker.getInstance().displaySettngsDialogBox();
			}
		};
		return listener;
	}

	private ActionListener getExitActionListener() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
		return listener;
	}
}
