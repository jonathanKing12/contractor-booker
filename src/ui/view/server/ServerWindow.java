package ui.view.server;

import static java.awt.BorderLayout.NORTH;

import java.awt.BorderLayout;
import java.awt.Dimension;

import ui.view.common.*;
import ui.view.settings.SettingsDialog;

public class ServerWindow extends MainWindow {

    public ServerWindow(SettingsDialog settings) {
        super(settings);
        this.setLayout(new BorderLayout());
        this.add(new HeaderPanel("Bodgitt and Scarper, LLC Home Improvement Broker"), NORTH);
        this.add(new ServerButtonHandler(), BorderLayout.CENTER);
        setSizeAndPosition();
    }

    private void setSizeAndPosition() {
        JFrameHelper presenter = new JFrameHelper();
        int width = presenter.getFractionOfScreenWidth(3, 2);
        int height = presenter.getFractionOfScreenHeight(7, 3);
        this.setPreferredSize(new Dimension(width, height));
        this.pack();
        presenter.centreMainScreen(this);
    }
}
