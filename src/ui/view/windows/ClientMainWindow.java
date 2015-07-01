package ui.view.windows;

import static java.awt.BorderLayout.*;
import ui.view.*;
import ui.view.settings.SettingsDialog;

public class ClientMainWindow extends MainWindow {

    public ClientMainWindow(SettingsDialog settings) {
        super(settings);
        addPanels();
        this.pack();
        centreMainScreen();
    }

    private void addPanels() {
        add(new FilterPanel(), NORTH);
        add(new TablePanel(), CENTER);
        add(new ButtonPanel(), SOUTH);
    }

    private void centreMainScreen() {
        JFramePresenter presenter = new JFramePresenter();
        presenter.centreMainScreen(this);
    }
}
