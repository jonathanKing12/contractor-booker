package suncertify.db.table;

import static mockedcontent.TableHeaderContent.getInts;
import static org.mockito.Mockito.when;

import java.util.List;

import org.mockito.stubbing.OngoingStubbing;

import datasource.DataSourceException;
import datasource.DataSourceReader;

public class SetUpHeaderMock {

	public static void setUpMockedheader(DataSourceReader reader) throws DataSourceException {
		List<Integer> mockedInts = getInts();
		// List<Short> mockedShorts = getShorts();
		// System.out.println("mocked shorts " + mockedShorts);

		OngoingStubbing<Integer> stubReadInt = when(reader.readInt());
		mockReturnedData(mockedInts, stubReadInt);

		// OngoingStubbing<Short> stubReadShort = when(reader.readShort());
		// mockReturnedData(mockedShorts, stubReadShort);
	}

	private static <T> void mockReturnedData(List<T> items, OngoingStubbing<T> ongoingStub) {
		for (T mockedInt : items) {
			ongoingStub = ongoingStub.thenReturn(mockedInt);
		}
	}
}
