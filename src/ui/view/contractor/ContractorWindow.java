package ui.view.contractor;

import static java.awt.BorderLayout.*;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import ui.view.common.*;
import ui.view.settings.SettingsDialog;

public class ContractorWindow extends MainWindow {

    /**
     * Constructs a instance of ContractorWindow with the specified settingsDialog
     * 
     * @param settingsDialog
     *            - the settingsDialog
     */
    public ContractorWindow(SettingsDialog settingsDialog) {
        super(settingsDialog);
        this.setTitle("Bodgitt and Scarper, LLC Home Improvement Broker");
        addPanels();
        setSizeAndPosition();
    }

    private void addPanels() {
        JPanel northPanel = createNorthPanel();
        add(northPanel, NORTH);

        add(new ContractorTablePanel(), CENTER);
        add(new ContractorButtonPanel(), SOUTH);
    }

    private JPanel createNorthPanel() {
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(new HeaderPanel("Bodgitt and Scarper, LLC Home Improvement Broker"), NORTH);
        northPanel.add(new ContractorSearchPanel(), SOUTH);
        return northPanel;
    }

    private void setSizeAndPosition() {
        JFrameHelper presenter = new JFrameHelper();
        int ninetenths = presenter.getFractionOfScreenWidth(10, 9);
        int eighteenTweentieths = presenter.getFractionOfScreenHeight(20, 18);
        this.setPreferredSize(new Dimension(ninetenths, eighteenTweentieths));
        this.pack();
        presenter.centreMainScreen(this);
    }
}
