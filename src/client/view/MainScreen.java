package client.view;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.Toolkit.getDefaultToolkit;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;

import javax.swing.JFrame;

import client.view.settings.SettingsDialogBox;

public class MainScreen extends JFrame implements BookableView {

	FilterPanel filterPanel;
	TablePanel tablePanel;
	ButtonPanel buttonPanel;
	SettingsDialogBox settings;

	public MainScreen() throws IOException {
		settings = new SettingsDialogBox(this);
		addMenuBar();
		setMainScreenSize();
		createPanels();
		addPanels();

		this.pack();
		centreMainScreen();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		display();

		this.addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent e) {

			}

			@Override
			public void componentResized(ComponentEvent e) {
				Dimension size = MainScreen.this.getPreferredSize().getSize();
				tablePanel.resizeTable(size.width, size.height);

			}

			@Override
			public void componentMoved(ComponentEvent e) {
			}

			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});

	}

	private void setMainScreenSize() {
		Dimension screenSize = getScreenSize();
		int half = screenSize.width / 2;

		int oneEight = screenSize.height / 8;
		int fiveEights = oneEight * 5;
		this.setPreferredSize(new Dimension(half, fiveEights));
	}

	@Override
	public void enableBookContratorButton(boolean enabled) {
		buttonPanel.enableBookContratorButton(enabled);
	}

	private void centreMainScreen() {
		int xPosition = getXPosition();
		int yPosition = getYPosition();
		this.setLocation(xPosition, yPosition);
	}

	private int getXPosition() {
		Dimension screenSize = getScreenSize();

		int screenCentreXPosition = screenSize.width / 2;
		int mainScreenCentreXPosition = this.getSize().width / 2;
		return (screenCentreXPosition - mainScreenCentreXPosition);
	}

	private int getYPosition() {
		Dimension screenSize = getScreenSize();

		int screenCentreYPosition = screenSize.height / 2;
		int mainScreenCentreYPosition = this.getSize().height / 2;
		return screenCentreYPosition - mainScreenCentreYPosition;
	}

	private Dimension getScreenSize() {
		Toolkit toolkit = getDefaultToolkit();
		return toolkit.getScreenSize();
	}

	private void addMenuBar() {
		this.setJMenuBar(new MenuBar());
	}

	private void createPanels() throws IOException {
		filterPanel = new FilterPanel();
		Dimension size = this.getPreferredSize().getSize();
		tablePanel = new TablePanel(size.width, size.height);
		buttonPanel = new ButtonPanel(this);
	}

	private void addPanels() {
		getContentPane().setLayout(new BorderLayout());
		add(filterPanel, BorderLayout.NORTH);
		add(tablePanel, CENTER);
		add(buttonPanel, SOUTH);
	}

	private void display() {
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		new MainScreen();
	}
}
