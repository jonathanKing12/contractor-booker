package ui.view.common;

import java.awt.event.*;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    private JMenuItem settings;
    private JMenuItem exit;
    private JMenu file;

    /**
     * Constructs a instance of MenuBar.
     */
    public MenuBar() {
        setUpFileMenu();
        addActionListeners();
        this.add(file);
    }

    private void setUpFileMenu() {
        file = new JMenu("file");
        file.setMnemonic(KeyEvent.VK_F);
        settings = new JMenuItem("settings");
        settings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        exit = new JMenuItem("exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        file.add(settings);
        file.addSeparator();
        file.add(exit);
    }

    private void addActionListeners() {
        settings.addActionListener(getSettingsActionListener());
        exit.addActionListener(getExitActionListener());
    }

    private ActionListener getSettingsActionListener() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ParentTracker.getInstance().displaySettngsDialog();
            }
        };
        return listener;
    }

    private ActionListener getExitActionListener() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        return listener;
    }
}
