package transport.contractor.service;

/**
 * Occurs when retrieving and booking contractors operations fail.
 *
 */
public class ContractorException extends Exception {

    /**
     * Constructs a ContractorException instance
     */
    public ContractorException() {
    }

    /**
     * Constructs a ContractorException instance with the specified message
     * 
     * @param message
     *            - the message
     */
    public ContractorException(String message) {
        super(message);
    }

}
