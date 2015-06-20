package client.view;

import static java.awt.Toolkit.getDefaultToolkit;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class JFramePresenter {

	void setMainScreenSize(JFrame frame) {
		Dimension screenSize = getScreenSize(frame);
		int half = screenSize.width / 2;

		int oneEight = screenSize.height / 8;
		int fiveEights = oneEight * 5;
		frame.setPreferredSize(new Dimension(half, fiveEights));
	}

	void centreMainScreen(JFrame frame) {
		int xPosition = getXPosition(frame);
		int yPosition = getYPosition(frame);
		frame.setLocation(xPosition, yPosition);
	}

	Dimension getScreenSize(JFrame frame) {
		Toolkit toolkit = getDefaultToolkit();
		return toolkit.getScreenSize();
	}

	private int getXPosition(JFrame frame) {
		Dimension screenSize = getScreenSize(frame);

		int screenCentreXPosition = screenSize.width / 2;
		int mainScreenCentreXPosition = frame.getSize().width / 2;
		return (screenCentreXPosition - mainScreenCentreXPosition);
	}

	private int getYPosition(JFrame frame) {
		Dimension screenSize = getScreenSize(frame);

		int screenCentreYPosition = screenSize.height / 2;
		int mainScreenCentreYPosition = frame.getSize().height / 2;
		return screenCentreYPosition - mainScreenCentreYPosition;
	}
}
