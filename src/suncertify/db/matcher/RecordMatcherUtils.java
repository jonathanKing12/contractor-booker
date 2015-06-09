package suncertify.db.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecordMatcherUtils {

	private static Pattern numberPattern = Pattern.compile("\\d+");

	public static boolean isNumeric(String maxSize) {
		Matcher m = numberPattern.matcher(maxSize);
		return m.matches();
	}

	public static boolean isNumberSmallerOrEqualToMaxNumber(String numberString, String maxNumberString) {
		int maxSize = Integer.parseInt(maxNumberString);
		int number = Integer.parseInt(numberString);
		return number <= maxSize;
	}
}
