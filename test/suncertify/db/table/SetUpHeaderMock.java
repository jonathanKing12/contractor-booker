package suncertify.db.table;

import static mockedcontent.TableHeaderContent.getInts;
import static org.mockito.Mockito.when;

import java.util.List;

import org.mockito.stubbing.OngoingStubbing;

import suncertify.db.datasource.DataSourceException;
import suncertify.db.datasource.DataSourceReader;

public class SetUpHeaderMock {

	public static void setUpMockedheader(DataSourceReader reader) throws DataSourceException {
		List<Integer> mockedInts = getInts();
		OngoingStubbing<Integer> stubReadInt = when(reader.readInt());
		mockReturnedData(mockedInts, stubReadInt);
	}

	private static <T> void mockReturnedData(List<T> items, OngoingStubbing<T> ongoingStub) {
		for (T mockedInt : items) {
			ongoingStub = ongoingStub.thenReturn(mockedInt);
		}
	}
}
