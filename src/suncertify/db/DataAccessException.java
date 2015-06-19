package suncertify.db;

public class DataAccessException extends RuntimeException {

	public DataAccessException() {
		super();
	}

	public DataAccessException(String errorMessage) {
		super(errorMessage);
	}
}
