package suncertify.db;

public class LockInterruptException extends RuntimeException {

	public LockInterruptException() {
		super();
	}

	public LockInterruptException(String message) {
		super(message);
	}
}
