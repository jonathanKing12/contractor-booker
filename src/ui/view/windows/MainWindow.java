package ui.view.windows;

import static java.lang.Boolean.TRUE;

import java.awt.BorderLayout;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.UIManager;

import settings.SettingType;
import ui.view.MenuBar;
import ui.view.mergers.SettableMerger;
import ui.view.mergers.ParentTracker;
import ui.view.settings.SettingsDialog;

public class MainWindow extends JFrame {

	private SettingsDialog settings;

	MainWindow(SettingsDialog settings) {
		UIManager.put("Button.defaultButtonFollowsFocus", TRUE);
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

		if (!isShowing) {
			displaySettingsDialogIfControllerHasNotSettingsOfTypes();
		}
	}

	private void displaySettingsDialogIfControllerHasNotSettingsOfTypes() {
		Set<SettingType> settingTypes = settings.getSettingsTypes();
		SettableMerger merger = SettableMerger.getInstance();
		merger.displaySettingsDialogIfControllerHasNotSettingsOfTypes(settingTypes);
	}
}
