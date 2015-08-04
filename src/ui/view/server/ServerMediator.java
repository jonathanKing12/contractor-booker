package ui.view.server;

import ui.controller.ControllerFactory;
import ui.controller.api.ServerController;
import ui.view.common.MessageBoxPresenter;

/**
 * Mediates Server operations between various instances in view and the ServerController instance.
 */
public class ServerMediator {

    private static final ServerMediator INSTANE;
    private ServerController controller;
    private ServerButtonHandler serverButtonHandler;

    static {
        INSTANE = new ServerMediator();
    }

    private ServerMediator() {
        ControllerFactory factory = new ControllerFactory();
        controller = factory.getServerController(new MessageBoxPresenter());
    }

    public static ServerMediator getInstance() {
        return INSTANE;
    }

    public void toggleServerButtonState() {
        serverButtonHandler.toggleServerButtonState();
    }

    /**
     * Returns {@code true} if the server button is enabled.
     * <p>
     * Delegates to {@link ServerButtonHandler#isServerButtonEnabled()}
     * </p>
     * 
     * @return {@code true} if the server button is enabled.
     */
    public boolean isServerButtonEnabled() {
        return serverButtonHandler.isServerButtonEnabled();
    }

    public boolean startServer() {
        return controller.startServer();
    }

    public boolean stopServer() {
        return controller.stopServer();
    }

    public void setServerButtonHandler(ServerButtonHandler serverButtonHandler) {
        this.serverButtonHandler = serverButtonHandler;
    }
}
