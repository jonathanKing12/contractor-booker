package ui.view.contractor;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.view.common.MessageBoxPresenter;
import ui.view.common.UserInputValidator;

public class ContractorButtonPanel extends JPanel implements ActionListener {

    private static final String ERROR_TITLE = "Invalid customer ID";
    private JButton bookContractor;
    private ContractorMediator mediator;

    /**
     * Constructs a instance of ContractorButtonPanel
     */
    public ContractorButtonPanel() {
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        setUpMediator();
        setpBookButton();
        this.setBackground(Color.cyan);
    }

    /**
     * Books the selected contractor.
     * 
     * <p>
     * Ask the user for a customer ID, validates it and then books the selected contractor with that customer ID If the customer ID is not valid then
     * the user will be asked again to enter a valid customer ID. If the user cancels then the contractor is not booked.
     * </p>
     * 
     * @param event
     *            - the event that triggered the booking action
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        proceedToBookContractor();
    }

    /**
     * Enables the book contractor button if the specified enabled is {@code true};
     * 
     * @param enabled
     *            - the enabled
     */
    public void enableBookContratorButton(final boolean enabled) {
        bookContractor.setEnabled(enabled);
    }

    private void setUpMediator() {
        mediator = ContractorMediator.getInstance();
        mediator.addButtonPanel(this);
    }

    private void setpBookButton() {
        bookContractor = new JButton("Book contractor");
        enableBookContratorButton(false);
        bookContractor.addActionListener(this);
        this.add(bookContractor);
    }

    private boolean isOkayButtonSelected(String customerId) {
        return customerId != null;
    }

    private String displayMessageBoxToGetCustomerId() {
        MessageBoxPresenter presenter = new MessageBoxPresenter();
        return presenter.displayInputDialog("enter customer ID");
    }

    private void proceedToBookContractor() {
        String customerId = displayMessageBoxToGetCustomerId();

        if (!isOkayButtonSelected(customerId)) {
            return;
        }

        if (!isCustomerIdValid(customerId)) {
            informUserAndProceedToBookContractorAgain(customerId);
            return;
        }

        mediator.bookSelectedContractorWithCustomer(customerId);
    }

    private boolean isCustomerIdValid(String customerId) {
        UserInputValidator validator = new UserInputValidator();
        return validator.isCustomerIdValid(customerId);
    }

    private void informUserAndProceedToBookContractorAgain(String customerId) {
        String errorMessage = getErrorMessage(customerId);
        displayErrorMessage(errorMessage);
        proceedToBookContractor();
    }

    private void displayErrorMessage(String errorMessage) {
        MessageBoxPresenter presenter = new MessageBoxPresenter();
        presenter.displayErrorMessageDialog(errorMessage, ERROR_TITLE);
    }

    private String getErrorMessage(String customerId) {
        String startOfMessage = createStartOfMessage(customerId);
        return appendEndOfMessage(startOfMessage);
    }

    private String createStartOfMessage(String customerId) {
        StringBuilder builder = new StringBuilder();

        if (customerId.isEmpty()) {
            builder.append("No customer ID has been entered");
        } else {
            builder.append("' ").append(customerId).append(" ' is not a valid customer ID.");
        }

        return builder.toString();
    }

    private String appendEndOfMessage(String startOfMessage) {
        StringBuilder builder = new StringBuilder();
        builder.append(startOfMessage);
        builder.append("\n A customer's ID has only eight digits.");
        return builder.toString();
    }
}
