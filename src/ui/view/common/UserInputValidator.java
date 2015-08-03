package ui.view.common;

public class UserInputValidator {

    private final String EIGH_DIGIT_NUMBER = "\\d\\d\\d\\d\\d\\d\\d\\d";

    /**
     * Returns {@code true} if the specified customerId is a eight digit number.
     * 
     * @param customerId
     *            - the customerId
     * @return {@code true} if the specified customerId is a eight digit number
     */
    public boolean isCustomerIdValid(String customerId) {
        return customerId.matches(EIGH_DIGIT_NUMBER);
    }
}
