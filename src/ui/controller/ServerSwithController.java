package ui.controller;

import server.SererException;
import server.Server;
import ui.view.mergers.ServerMerger;

public class ServerSwithController {

    private Server server;
    private ServerMerger view;

    public ServerSwithController(ServerMerger serverMerger) {
        view = serverMerger;
        server = new Server();
    }

    public void enable() {
        try {
            server.enable();
        } catch (SererException e) {
            view.displayErrorMessage("error when enabling server", "error");
        }
    }

    public void disable() {
        try {
            server.disable();
        } catch (SererException e) {
            view.displayErrorMessage("error when disabling server", "error");
        }
    }
}
