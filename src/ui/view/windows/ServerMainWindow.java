package ui.view.windows;

import ui.view.server.ControllPanel;
import ui.view.settings.SettingsDialog;

public class ServerMainWindow extends MainWindow {

    public ServerMainWindow(SettingsDialog settings) {
        super(settings);
        this.add(new ControllPanel());
        this.pack();
    }

    private void centreMainScreen() {
        JFramePresenter presenter = new JFramePresenter();
        presenter.centreMainScreen(this);
    }
}
