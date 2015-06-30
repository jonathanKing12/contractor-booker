package ui.view.windows;

import static java.awt.Toolkit.getDefaultToolkit;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class JFramePresenter {

	void setMainScreenSize(JFrame frame) {
		Dimension screenSize = getScreenSize(frame);
		int onetenth = screenSize.width / 10;
		int ninetenths = onetenth * 9;

		int onetwentieth = screenSize.height / 20;
		int eighteenTweentieths = onetwentieth * 18;
		frame.setPreferredSize(new Dimension(ninetenths, eighteenTweentieths));
	}

	void centreMainScreen(JFrame frame) {
		int xPosition = getXPosition(frame);
		int yPosition = getYPosition(frame);
		frame.setLocation(xPosition, yPosition);
	}

	private Dimension getScreenSize(JFrame frame) {
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
