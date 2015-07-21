package suncertify.db;

public interface DB {

    String[] read(int recordNumber) throws RecordNotFoundException;

    void update(int recNo, String[] data, long lockCookie) throws RecordNotFoundException,
            SecurityException;

    void delete(int recNo, long lockCookie) throws RecordNotFoundException, SecurityException;

    int[] find(String[] data);

    int create(String[] data) throws DuplicateKeyException;

    long lock(int recNo) throws RecordNotFoundException;

    void unlock(int recNo, long lockCookie) throws RecordNotFoundException, SecurityException;
}
