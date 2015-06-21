package client.view;

import static client.view.ViewMerger.VIEW_MERGER_INSTACE;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import client.view.settings.SettingsDialogBox;

public class MainWindow extends JFrame {

	MainWindow(SettingsDialogBox settings) {
		settings.setLocationRelativeTo(this);

		addMenuBar();
		setMainScreenSize();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		VIEW_MERGER_INSTACE.setUpForClientUse(this);
	}

	private void setMainScreenSize() {
		JFramePresenter presenter = new JFramePresenter();
		presenter.setMainScreenSize(this);
	}

	private void addMenuBar() {
		this.setJMenuBar(new MenuBar());
	}
}
