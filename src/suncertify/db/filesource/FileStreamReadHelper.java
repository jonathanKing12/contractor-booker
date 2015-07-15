package suncertify.db.filesource;

import java.io.DataInputStream;
import java.io.IOException;

public class FileStreamReadHelper {

    /**
     * Fills the specified array of bytes from the specified stream
     * 
     * @param data
     *            - the array
     * @param inputStream
     *            - the stream t
     * @throws IOException
     *             - if an IOException occurs
     */
    synchronized void fillByteArrayFromStream(final byte[] data, final DataInputStream inputStream)
            throws IOException {
        inputStream.read(data);
    }

    /**
     * Reads an integer from the specified stream
     * 
     * @param inputStream
     *            - the stream
     * @return the integer
     * @throws IOException
     *             - if an IOException occurs
     */
    synchronized int readIntFromStream(DataInputStream inputStream) throws IOException {
        return inputStream.readInt();
    }

    /**
     * Reads an short from the specified stream
     * 
     * @param inputStream
     *            - the stream
     * @return the short
     * @throws IOException
     *             - if an IOException occurs
     */
    synchronized short readShortFromStream(DataInputStream inputStream) throws IOException {
        return inputStream.readShort();
    }

    /**
     * Moves forward in the stream by the specified distance
     * 
     * @param inputStream
     *            - the stream
     * @param distance
     *            - the distance
     * @throws IOException
     *             - if an IOException occurs
     */
    synchronized void moveForwardAccrossStreamBy(int distance, DataInputStream inputStream)
            throws IOException {
        inputStream.skipBytes(distance);
    }

    /**
     * Returns an estimate of the amount of bytes that can be read from the specified steam
     * 
     * @param inputStream
     *            - the stream
     * @return the amount of available bytes
     * @throws IOException
     *             - if an IOException occurs
     */
    synchronized int getAvailableBytes(DataInputStream inputStream) throws IOException {
        return inputStream.available();
    }
}
