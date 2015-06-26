package gui.view;

import gui.view.mergers.BookableMerger;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel implements ActionListener {

	private static final String ERROR_TITLE = "Invalid customer ID";
	private JButton bookContractor;

	public ButtonPanel() {
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		setUpMerger();
		setpBookButton();
		this.setBackground(Color.cyan);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		proceedToBookContractor();
	}

	public void enableBookContratorButton(final boolean enabled) {
		bookContractor.setEnabled(enabled);
	}

	private void setUpMerger() {
		BookableMerger merger = BookableMerger.getInstance();
		merger.addButtonPanel(this);
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
		MessageBoxPresenter presenter = MessageBoxPresenter.getInstance();
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

		bookSelectedContractorWithCustomer(customerId);
	}

	private boolean isCustomerIdValid(String customerId) {
		Validator validator = new Validator();
		return validator.isCustomerIdValid(customerId);
	}

	private void informUserAndProceedToBookContractorAgain(String customerId) {
		String errorMessage = getErrorMessage(customerId);
		displayErrorMessage(errorMessage);
		proceedToBookContractor();
	}

	private void displayErrorMessage(String errorMessage) {
		MessageBoxPresenter presenter = MessageBoxPresenter.getInstance();
		presenter.displayErrorMessageBox(errorMessage, ERROR_TITLE);
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

	private void bookSelectedContractorWithCustomer(String customerId) {
		BookableMerger merger = BookableMerger.getInstance();
		merger.bookSelectedContractorWithCustomer(customerId);
	}
}
