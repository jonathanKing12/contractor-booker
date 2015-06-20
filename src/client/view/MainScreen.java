package client.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import client.view.settings.SettingsDialogBox;

public class MainScreen extends JFrame {

	MainScreen(SettingsDialogBox settings) {
		settings.setLocationRelativeTo(this);

		addMenuBar();
		setMainScreenSize();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		ViewMerger.getInstance().setUpConnectionToClient(this);
	}

	private void setMainScreenSize() {
		JFramePresenter presenter = new JFramePresenter();
		presenter.setMainScreenSize(this);
	}

	private void addMenuBar() {
		this.setJMenuBar(new MenuBar());
	}
}
