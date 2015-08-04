package ui.view.server;

import static java.awt.BorderLayout.NORTH;

import java.awt.BorderLayout;
import java.awt.Dimension;

import ui.view.common.*;
import ui.view.settings.SettingsDialog;

/**
 * Represents the Server GUI.
 */
public class ServerWindow extends MainWindow {

    public ServerWindow(SettingsDialog settings) {
        super(settings);
        this.setLayout(new BorderLayout());
        this.add(new HeaderPanel(), NORTH);
        this.add(new ServerButtonHandler(), BorderLayout.CENTER);
        setSizeAndPosition();
    }

    private void setSizeAndPosition() {
        WindowHelper presenter = new WindowHelper();
        int width = presenter.getFractionOfScreenWidth(3, 2);
        int height = presenter.getFractionOfScreenHeight(7, 3);
        this.setPreferredSize(new Dimension(width, height));
        this.pack();
        presenter.centreMainScreen(this);
    }
}
