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

import client.view.settings.SettingsDialogBox;

public class ServerMainWindow extends MainWindow {

    public ServerMainWindow(SettingsDialogBox settings) {
        super(settings);
        this.add(new ControllPanel());
        this.pack();
    }

    private void centreMainScreen() {
        JFramePresenter presenter = new JFramePresenter();
        presenter.centreMainScreen(this);
    }

}
