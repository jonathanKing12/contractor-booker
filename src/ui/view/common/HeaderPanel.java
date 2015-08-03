package ui.view.common;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeaderPanel extends JPanel {

    public HeaderPanel(String headerMessage) {
        JLabel headerLabel = new JLabel(headerMessage);
        this.add(headerLabel);
        this.setBackground(Color.lightGray);
    }
}
