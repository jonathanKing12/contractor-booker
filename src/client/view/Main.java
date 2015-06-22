package client.view;

import static java.lang.Boolean.TRUE;

import java.io.IOException;

import client.view.settings.*;

public class Main {

    public static void main(String[] args) throws IOException {
        new Main();
    }

    public Main() throws IOException {
        //MainWindow mainScreen = new ClientMainWindow(createSettingsDialogBox());
        MainWindow mainScreen = new ServerMainWindow(createSettingsDialogBox());
        mainScreen.setVisible(TRUE);
    }

    private SettingsDialogBox createSettingsDialogBox() {
        SettingsDialogBoxBuilder builder = new SettingsDialogBoxBuilder();
        builder.addSettingsTab(new PortNumberIpAddressSettingsTab());
        return builder.build();
    }
}
