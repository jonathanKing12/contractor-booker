package suncertify.db;

public class InvalidLockCookieException extends SecurityException {

	public InvalidLockCookieException() {
		super();
	}

	public InvalidLockCookieException(String message) {
		super(message);
	}

}
