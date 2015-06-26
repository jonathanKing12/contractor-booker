package gui.view.windows;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import gui.view.ButtonPanel;
import gui.view.FilterPanel;
import gui.view.TablePanel;
import gui.view.settings.SettingsDialogBox;

import java.io.IOException;

public class ClientMainWindow extends MainWindow {

	public ClientMainWindow(SettingsDialogBox settings) throws IOException {
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
