package ui.view.common;

import static java.awt.Toolkit.getDefaultToolkit;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class JFrameHelper {

    public int getFractionOfScreenWidth(int baseFraction, int numberOfFractionUnits) {
        Dimension screenSize = getScreenSize();
        int oneFractionUnit = screenSize.width / baseFraction;
        return oneFractionUnit * numberOfFractionUnits;
    }

    /**
     * 
     * @param baseFraction
     * @param numberOfFractionUnits
     * @return
     */
    public int getFractionOfScreenHeight(int baseFraction, int numberOfFractionUnits) {
        Dimension screenSize = getScreenSize();
        int oneFractionUnit = screenSize.height / baseFraction;
        return oneFractionUnit * numberOfFractionUnits;
    }

    /**
     * Centers the specified frame to the centers of the screen
     * 
     * @param frame
     *            - the frame.
     */
    public void centreMainScreen(JFrame frame) {
        int xPosition = getXPosition(frame);
        int yPosition = getYPosition(frame);
        frame.setLocation(xPosition, yPosition);
    }

    private Dimension getScreenSize() {
        Toolkit toolkit = getDefaultToolkit();
        return toolkit.getScreenSize();
    }

    private int getXPosition(JFrame frame) {
        Dimension screenSize = getScreenSize();

        int screenCentreXPosition = screenSize.width / 2;
        int mainScreenCentreXPosition = frame.getSize().width / 2;
        return (screenCentreXPosition - mainScreenCentreXPosition);
    }

    private int getYPosition(JFrame frame) {
        Dimension screenSize = getScreenSize();

        int screenCentreYPosition = screenSize.height / 2;
        int mainScreenCentreYPosition = frame.getSize().height / 2;
        return screenCentreYPosition - mainScreenCentreYPosition;
    }
}
