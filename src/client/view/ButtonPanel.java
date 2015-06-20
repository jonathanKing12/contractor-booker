package client.view;

import static client.view.Validator.isCustomerIdValid;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel implements ActionListener {

	private JButton bookContractor;
	private ViewMerger merger;

	public ButtonPanel() {
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		setUpMerger();
		setpBookButton();
		this.setBackground(Color.cyan);
	}

	private void setUpMerger() {
		merger = ViewMerger.getInstance();
		merger.addButtonPanel(this);
	}

	private void setpBookButton() {
		bookContractor = new JButton("Book contractor");
		enableBookContratorButton(false);
		bookContractor.addActionListener(this);
		this.add(bookContractor);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		proceedToBookContractor();
	}

	public void enableBookContratorButton(final boolean enabled) {
		bookContractor.setEnabled(enabled);
	}

	private boolean isOkayButtonSelected(String customerId) {
		return customerId != null;
	}

	private String displayMessageBoxToGetCustomerId() {
		return merger.displayInputDialogBox("enter customer ID");
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

		merger.bookSelectedContractorWithCustomer(customerId);
	}

	private void informUserAndProceedToBookContractorAgain(String customerId) {
		String errorMessage = getErrorMessage(customerId);
		merger.displayMessageBox(errorMessage, "Invalid customer ID");
		proceedToBookContractor();
	}

	private String getErrorMessage(String customerId) {
		StringBuilder builder = new StringBuilder();

		if (customerId.isEmpty()) {
			builder.append("No customer ID has been entered");
		} else {
			builder.append("' ").append(customerId).append(" ' is not a valid customer ID.");
		}

		builder.append("\n A customer's ID has only eight digits.");

		return builder.toString();
	}
}
