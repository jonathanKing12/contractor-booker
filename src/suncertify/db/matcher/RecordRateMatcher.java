package suncertify.db.matcher;

import static constants.Constants.INDEX_OF_FIRST_DIGIT;
import static java.lang.Double.parseDouble;
import suncertify.db.record.Record;

public class RecordRateMatcher extends RecordMatcher {

    /**
     * Constructs a RecordLocationMatcher instance with the specified searchCritea and recordMatcher
     * 
     * @param searchCritea
     *            - the searchCritea
     * @param recordMatcher
     *            - the recordMatcher
     */
    public RecordRateMatcher(String critea, RecordMatcher recordMatcher) {
        super(critea, recordMatcher);
    }

    /**
     * Returns {@code true} if the specified record rate is equal to or less than the specified searchCritea. The match ignores the first character of
     * the record's rate
     * 
     * @param record
     *            - the record
     * @param searchCritea
     *            - the searchCritea
     * @return {@code true} if the specified record rate is equal to or less than the specified searchCritea
     */
    @Override
    boolean isRecordFieldMatchingCritea(Record record, String searchCritea) {

        String hourlyRateString = record.getHourlyRate().substring(INDEX_OF_FIRST_DIGIT);
        double hourlyRate = parseDouble(hourlyRateString);
        double criteaRate = parseDouble(searchCritea);
        return hourlyRate <= criteaRate;
    }
}
