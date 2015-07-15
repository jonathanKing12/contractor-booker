package suncertify.db.matcher;

import static java.lang.Boolean.TRUE;
import suncertify.db.record.Record;

public abstract class RecordMatcher {

    private RecordMatcher recordMatcher;
    private String searchCritea;

    /**
     * creates a RecordMatcher instance with the specified searchCritea
     * 
     * @param searchCritea
     *            - the searchCritea
     */
    public RecordMatcher(String searchCritea) {
        this.searchCritea = searchCritea;
    }

    /**
     * creates a RecordMatcher instance with the specified searchCritea and recordMatcher
     * 
     * @param searchCritea
     *            - the searchCritea
     * @param recordMatcher
     *            - the recordMatcher
     */
    public RecordMatcher(String searchCritea, RecordMatcher recordMatcher) {
        this(searchCritea);
        this.recordMatcher = recordMatcher;
    }

    /**
     * Returns {@code true} if the specified record matches this instance's searchCritea. If this instance has a recordMatcher then that
     * recordMatcher's searchCritea must also match the record for {@code true} to be returned. If there is a long chain of RecordMatchers then all
     * recordMatchers in that chain must match the record for {@code true} to be returned
     * 
     * @param record
     *            - the record
     * @return {@code true} if the record matches the searchCritea of all recordMatchers in the chain.
     */
    public boolean matches(Record record) {
        boolean isMatching = TRUE;

        if (recordMatcher != null) {
            isMatching = recordMatcher.matches(record);
        }

        if (isMatching && searchCritea != null) {
            isMatching = isRecordFieldMatchingCritea(record, searchCritea.toLowerCase());
        }
        return isMatching;
    }

    /**
     * Returns {@code true} if the specified record matches this specified searchCritea.
     * 
     * @param record
     *            - the record
     * @param searchCritea
     *            - the searchCritea
     * @return {@code true} if the record matches searchCritea
     */
    abstract boolean isRecordFieldMatchingCritea(Record record, String searchCritea);

}
