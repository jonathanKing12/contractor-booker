package suncertify.db.matcher;

import static constants.Constants.INDEX_OF_FIRST_DIGIT;
import static java.lang.Boolean.FALSE;
import static java.lang.Double.parseDouble;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import suncertify.db.record.Record;

public class RecordRateMatcher extends RecordMatcher {

	private static final String DECIMAL_NUMBER_PATTERN = "\\d+.*\\d+";
	private Pattern decimalPattern;

	public RecordRateMatcher(String critea) {
		super(critea);
		decimalPattern = Pattern.compile(DECIMAL_NUMBER_PATTERN);
	}

	public RecordRateMatcher(String critea, RecordMatcher recordMatcher) {
		super(critea, recordMatcher);
		decimalPattern = Pattern.compile(DECIMAL_NUMBER_PATTERN);
	}

	@Override
	boolean isRecordFieldMatchingCritea(Record record, String critea) {
		if (!isADecimalNumber(critea)) {
			return FALSE;
		}

		String hourlyRate = record.getHourlyRate().substring(INDEX_OF_FIRST_DIGIT);
		return isHoullyRateSmallerOrEqualToCritea(hourlyRate, critea);
	}

	private boolean isADecimalNumber(String critea) {
		Matcher m = decimalPattern.matcher(critea);
		return m.matches();
	}

	private boolean isHoullyRateSmallerOrEqualToCritea(String hourlyRateString, String criteaString) {
		double hourlyRate = parseDouble(hourlyRateString);
		double critea = parseDouble(criteaString);
		return hourlyRate <= critea;
	}
}
