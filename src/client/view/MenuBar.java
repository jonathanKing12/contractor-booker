package client.view;

import static client.view.ViewMerger.VIEW_MERGER_INSTACE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
		settings = new JMenuItem("settings");
		exit = new JMenuItem("exit");
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
				VIEW_MERGER_INSTACE.displaySettngsDialogBox();
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
