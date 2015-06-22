/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package client.view;

import static java.lang.Boolean.TRUE;

import java.awt.Color;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import server.Server;

public class ControllPanel extends JPanel {

    private JToggleButton onOffSwitch;
    private boolean isTurnedOn;
    private Server server = new Server();

    ControllPanel() {
        this.setBackground(Color.cyan);

        onOffSwitch = new JToggleButton("turn on");
        onOffSwitch.setSelected(TRUE);
        onOffSwitch.addChangeListener(getChangedListener());
        this.add(onOffSwitch);
    }

    private ChangeListener getChangedListener() {
        return new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent arg0) {
                System.out.println("turned on");
                isTurnedOn = !isTurnedOn;
                changedOnOffButtonText(isTurnedOn);

                if (isTurnedOn) {
                    try {
                        server.register();
                    } catch (RemoteException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

        };
    }

    private void changedOnOffButtonText(boolean isTurnedOn) {
        String text = isTurnedOn ? "turn off" : "turn on";
        onOffSwitch.setText(text);
    }
}
