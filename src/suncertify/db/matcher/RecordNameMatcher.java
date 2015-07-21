package suncertify.db.matcher;

import suncertify.db.record.Record;

public class RecordNameMatcher extends RecordMatcher {

    /**
     * Constructs a RecordLocationMatcher instance with the specified searchCritea
     * 
     * @param searchCritea
     *            - the searchCritea
     */
    public RecordNameMatcher(String searchCritea) {
        super(searchCritea);
    }

    /**
     * Returns {@code true} if the specified record name starts with the specified searchCritea. The match is performed with all the characters in the
     * record's name converted to lower case using {@link String#toLowerCase()} method
     * 
     * @param record
     *            - the record
     * @param searchCritea
     *            - the searchCritea
     * @return {@code true} if the specified record name starts with the specified searchCritea.
     */
    @Override
    boolean isRecordFieldMatchingCritea(Record record, String searchCritea) {
        String name = record.getName();
        return name.toLowerCase().startsWith(searchCritea);
    }
}
