package client.view;

public class Validator {

	private static final String EIGH_DIGIT_NUMBER = "\\d\\d\\d\\d\\d\\d\\d\\d";

	public static boolean isCustomerIdValid(String customerId) {
		return customerId.matches(EIGH_DIGIT_NUMBER);
	}
}
