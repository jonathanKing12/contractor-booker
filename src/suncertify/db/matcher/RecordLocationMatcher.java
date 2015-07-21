package suncertify.db.matcher;

import suncertify.db.record.Record;

public class RecordLocationMatcher extends RecordMatcher {

    /**
     * Constructs a RecordLocationMatcher instance with the specified searchCritea and recordMatcher
     * 
     * @param searchCritea
     *            - the searchCritea
     * @param recordMatcher
     *            - the recordMatcher
     */
    public RecordLocationMatcher(String searchCritea, RecordMatcher recordMatcher) {
        super(searchCritea, recordMatcher);
    }

    /**
     * Returns {@code true} if the specified record location starts with the specified searchCritea. The match is performed with all the characters in
     * record's name converted to lower case using {@link String#toLowerCase()} method
     * 
     * @param record
     *            - the record
     * @param searchCritea
     *            - the searchCritea
     * @return {@code true} if the specified record location starts with the specified searchCritea.
     */
    @Override
    boolean isRecordFieldMatchingCritea(Record record, String searchCritea) {
        String location = record.getLocation();
        return location.toLowerCase().startsWith(searchCritea);
    }
}
