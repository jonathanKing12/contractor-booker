package ui.view.windows;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import ui.view.ButtonPanel;
import ui.view.FilterPanel;
import ui.view.TablePanel;
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
