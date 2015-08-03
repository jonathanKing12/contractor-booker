package ui.view.server;

import static constants.Constants.SERVER_IS_STOPPED;
import static constants.Constants.START_SERVER;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import ui.view.server.state.*;

public class ServerButtonHandler extends JPanel {

    private StateContext stateContext;
    private JButton serverButton;
    private JLabel serverMessage;

    /**
     * Creates a instance of ServerButtonHandler.
     */
    public ServerButtonHandler() {
        ServerMediator.getInstance().setServerButtonHandler(this);
        this.setBackground(Color.cyan);

        serverButton = createServerButton();
        serverMessage = new JLabel(SERVER_IS_STOPPED);
        stateContext = setUpStopStartStates();

        addComponents(serverButton, serverMessage);
    }

    /**
     * Sets the server button text to the specified text.
     * 
     * @param text
     *            - the text
     */
    public void setServerButtonText(String text) {
        serverButton.setText(text);
    }

    /**
     * Sets the server message text to the specified text.
     * 
     * @param text
     *            - the text
     */
    public void setServerMessageText(String message) {
        serverMessage.setText(message);
    }

    /**
     * Starts the server if it is stopped otherwise stops the server.
     * 
     * <p>
     * Starting the server enables the server button and stopping the server disables it
     * </p>
     */
    public void toggleServerButtonState() {
        stateContext.doAction();
    }

    /**
     * Returns {@code true} if the server button is currently enabled.
     * 
     * <p>
     * When the server button is enabled then the server is running.
     * </p>
     * 
     * @return {@code true} if the server button is currently enabled
     */
    public boolean isServerButtonEnabled() {
        return stateContext.isServerRunning();
    }

    private StateContext setUpStopStartStates() {
        State startState = new StartState(this);
        State stopState = new StopState(this);

        startState.setNextState(stopState);
        stopState.setNextState(startState);

        return new StateContext(stopState);
    }

    private JButton createServerButton() {
        JButton serverButton = new JButton(START_SERVER);
        ActionListener listener = getActionListener();
        serverButton.addActionListener(listener);
        return serverButton;
    }

    private ActionListener getActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                stateContext.doAction();
            }
        };
    }

    private void addComponents(JButton serverButton, JLabel serverMessage) {
        Component topStruct = Box.createVerticalStrut(50);
        Component middleStruct = Box.createVerticalStrut(10);

        Box box = Box.createVerticalBox();
        box.add(topStruct);
        box.add(serverMessage);
        box.add(middleStruct);
        box.add(serverButton);
        this.add(box);
    }
}
