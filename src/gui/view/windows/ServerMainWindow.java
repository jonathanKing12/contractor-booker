package gui.view.windows;

import gui.view.ControllPanel;
import gui.view.settings.SettingsDialogBox;

public class ServerMainWindow extends MainWindow {

	public ServerMainWindow(SettingsDialogBox settings) {
		super(settings);
		this.add(new ControllPanel());
		this.pack();
	}

	private void centreMainScreen() {
		JFramePresenter presenter = new JFramePresenter();
		presenter.centreMainScreen(this);
	}
}
