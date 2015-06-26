package gui.view.windows;

import static gui.view.mergers.ViewMerger.VIEW_MERGER_INSTACE;
import static java.lang.Boolean.TRUE;
import gui.view.MenuBar;
import gui.view.mergers.SettableMerger;
import gui.view.settings.SettingsDialogBox;

import java.awt.BorderLayout;
import java.util.Set;

import javax.swing.JFrame;

import settings.SettingType;

public class MainWindow extends JFrame {

	private SettingsDialogBox settings;

	MainWindow(SettingsDialogBox settings) {
		this.settings = settings;
		settings.setLocationRelativeTo(this);

		addMenuBar();
		setMainScreenSize();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		VIEW_MERGER_INSTACE.addMainWindow(this);
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
