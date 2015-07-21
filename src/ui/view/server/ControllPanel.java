package ui.view.server;

import static java.lang.Boolean.TRUE;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

import ui.view.MessageBoxPresenter;
import ui.view.mergers.ServerMerger;

public class ControllPanel extends JPanel {

    private static final String ENABLE = "enable";
    private StateContext stateContext;
    private State keepDisabled;

    public ControllPanel() {
        ServerMerger.getInstance().setControlPanel(this);
        this.setBackground(Color.cyan);

        JToggleButton enableDisable = creteEnableDisableButton();
        stateContext = setUpStates(enableDisable);
        this.add(enableDisable);
    }

    private StateContext setUpStates(JToggleButton enableDisable) {
        State enable = new EnableState(enableDisable);
        State disable = new DisableState(enableDisable);
        keepDisabled = new KeepDisabledState();

        enable.setNextState(disable);
        disable.setNextState(enable);
        keepDisabled.setNextState(disable);

        return new StateContext(disable);
    }

    private JToggleButton creteEnableDisableButton() {
        JToggleButton enableDisable = new JToggleButton(ENABLE);
        ActionListener listener = getActionListener();
        enableDisable.addActionListener(listener);
        return enableDisable;
    }

    private ActionListener getActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                stateContext.doAction();
            }
        };
    }

    public boolean disableServer() {
        if (!stateContext.isCurrentStateEnabled()) {
            stateContext.setState(keepDisabled);
            return TRUE;
        }

        MessageBoxPresenter presenter = new MessageBoxPresenter();
        boolean isOkayToDisableServer = presenter.dispayYesNoDialogBox("is okay to restart server",
                "restart server");

        if (isOkayToDisableServer) {
            stateContext.doAction();
        }

        return isOkayToDisableServer;
    }

    public void enableServer() {
        stateContext.doAction();
    }
}
