package suncertify.db;

/**
 * Provides read write and locking operations to the database.
 */
public interface DB {

    /**
     * Reads a record from the file. Returns an array where each element is a record value.
     * 
     * @param recNo
     *            - the number of the record that is read.
     * @return Returns a array where each element is a record value.
     * @throws RecordNotFoundException
     *             if the record does not exist or is marked as deleted in the database file
     */
    String[] read(int recNo) throws RecordNotFoundException;

    /**
     * Modifies the fields of a record. The new value for field n appears in data[n].Throws SecurityException if the record is locked with a cookie
     * other than lockCookie.
     * 
     * @param recNo
     *            - the number of the record that is to be updated.
     * @param data
     *            - the array where each element contains the new value for the record
     * @param lockCookie
     *            - the lockCookie
     * @throws RecordNotFoundException
     *             if the record does not exist or is marked as deleted in the database file
     * @throws SecurityException
     *             if the record is locked with a cookie other than lockCookie
     */
    void update(int recNo, String[] data, long lockCookie) throws RecordNotFoundException,
            SecurityException;

    /**
     * Deletes a record, making the record number and associated disk storage available for reuse.Throws SecurityException if the record is locked
     * with a cookie other than lockCookie
     * 
     * @param recNo
     *            - the number of the record that is deleted.
     * @param lockCookie
     *            - the lockCookie
     * @throws RecordNotFoundException
     *             if the record does not exist or is marked as deleted in the database file
     * @throws SecurityException
     *             if the record is locked with a cookie other than lockCookie
     */
    void delete(int recNo, long lockCookie) throws RecordNotFoundException, SecurityException;

    /**
     * Returns an array of record numbers that match the specified criteria. Field n in the database file is described by criteria[n]. A null value in
     * criteria[n] matches any field value. A non-null value in criteria[n] matches any field value that begins with criteria[n]. (For example, "Fred"
     * matches "Fred" or "Freddy".)
     * 
     * @param data
     *            - the criteria
     * @return the array of record numbers.
     */
    int[] find(String[] data);

    /**
     * Creates a new record in the database (possibly reusing a deleted entry). Inserts the given data, and returns the record number of the new
     * record.
     * 
     * @param data
     *            - the contents of the record that is to be created
     * @return the number of the newly created record
     * @throws DuplicateKeyException
     *             if a record with the same name and location already exist.
     */
    int create(String[] data) throws DuplicateKeyException;

    /**
     * Locks a record so that it can only be updated or deleted by this client. Returned value is a cookie that must be used when the record is
     * unlocked, updated, or deleted. If the specified record is already locked by a different client the current thread gives up the CPU an consumes
     * no CPU cycles until the record is unlocked.
     * 
     * @param recNo
     *            - the number of the record that is to be locked
     * @return - the cookie
     * @throws RecordNotFoundException
     *             if a record with the same name and location already exist.
     */
    long lock(int recNo) throws RecordNotFoundException;

    /**
     * Releases the lock on a record. Cookie must be the cookie returned when the record was locked; otherwise throws SecurityException.
     * 
     * @param recNo
     *            - the number of the record that is to be unlocked
     * @param cookie
     *            - the cookie
     * @throws RecordNotFoundException
     *             if a record with the same name and location already exist.
     * @throws SecurityException
     *             if the record is locked with a cookie other than lockCookie
     */
    void unlock(int recNo, long cookie) throws RecordNotFoundException, SecurityException;
}
