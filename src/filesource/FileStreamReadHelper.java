package filesource;

import java.io.DataInputStream;
import java.io.IOException;

public class FileStreamReadHelper {

	synchronized int fillByteArrayFromStream(final byte[] data, final DataInputStream inputStream) throws IOException {
		return inputStream.read(data);
	}

	synchronized int readIntFromStream(DataInputStream inputStream) throws IOException {
		return inputStream.readInt();
	}

	synchronized short readShortFromStream(DataInputStream inputStream) throws IOException {
		return inputStream.readShort();
	}

	synchronized void moveForwardInStreamBy(DataInputStream inputStream, int distance) throws IOException {
		inputStream.skipBytes(distance);
	}

	synchronized int getAvailableBytes(DataInputStream inputStream) throws IOException {
		return inputStream.available();
	}
}
