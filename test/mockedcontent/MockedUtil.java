package mockedcontent;

import static org.mockito.Mockito.when;

import java.util.List;

import org.mockito.stubbing.OngoingStubbing;

import suncertify.db.datasource.DataSourceException;
import suncertify.db.datasource.DataSourceReader;

public class MockedUtil {

	public static void mockShorts(DataSourceReader reader, List<Short> shorts) throws DataSourceException {
		OngoingStubbing<Short> stubReadShort = when(reader.readShort());
		mockReturnedData(shorts, stubReadShort);
	}

	private static <T> void mockReturnedData(List<T> items, OngoingStubbing<T> ongoingStub) {
		for (T mockedInt : items) {
			ongoingStub = ongoingStub.thenReturn(mockedInt);
		}
	}

}
