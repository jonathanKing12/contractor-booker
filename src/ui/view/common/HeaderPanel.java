package ui.view.common;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Displays the Header in the MainWindow instance.
 */
public class HeaderPanel extends JPanel {

    private static final String MESSAGE = "Bodgitt and Scarper, LLC Home Improvement Broker";

    public HeaderPanel() {
        JLabel label = new JLabel(MESSAGE);
        this.add(label);
        this.setBackground(Color.lightGray);
    }
}
