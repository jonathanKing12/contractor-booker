package ui.view.windows;

import static java.lang.Boolean.TRUE;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import ui.view.MenuBar;
import ui.view.ParentTracker;
import ui.view.settings.SettingsDialog;

public class MainWindow extends JFrame {

    private SettingsDialog settings;

    MainWindow(SettingsDialog settings) {
        this.settings = settings;
        settings.setLocationRelativeTo(this);

        addMenuBar();
        setMainScreenSize();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        ParentTracker.getInstance().addMainWindow(this);
    }

    private void setMainScreenSize() {
        JFramePresenter presenter = new JFramePresenter();
        presenter.setMainScreenSize(this);
    }

    private void addMenuBar() {
        this.setJMenuBar(new MenuBar());
    }

    public void display() {
        boolean isShowing = this.isShowing();
        this.setVisible(TRUE);

        if (!isShowing && settings.isToBeDisplayed()) {
            ParentTracker tracker = ParentTracker.getInstance();
            tracker.displaySettngsDialogBox();
        }
    }
}
