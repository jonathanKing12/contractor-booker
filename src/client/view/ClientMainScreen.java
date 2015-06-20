package client.view;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

import java.io.IOException;

import client.view.settings.SettingsDialogBox;

public class ClientMainScreen extends MainScreen {

	public ClientMainScreen(SettingsDialogBox settings) throws IOException {
		super(settings);

		add(new FilterPanel(), NORTH);
		add(new TablePanel(), CENTER);
		add(new ButtonPanel(), SOUTH);
		this.pack();

		centreMainScreen();
	}

	private void centreMainScreen() {
		JFramePresenter presenter = new JFramePresenter();
		presenter.centreMainScreen(this);
	}
}
