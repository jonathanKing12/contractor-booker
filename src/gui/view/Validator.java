package gui.view;

public class Validator {

	private final String EIGH_DIGIT_NUMBER = "\\d\\d\\d\\d\\d\\d\\d\\d";

	public boolean isCustomerIdValid(String customerId) {
		return customerId.matches(EIGH_DIGIT_NUMBER);
	}
}
