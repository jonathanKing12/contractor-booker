package integration.singleclient;

import mockedcontent.MockedDataSourceFactory;

import org.junit.Before;

import suncertify.db.DB;
import suncertify.db.Data;
import suncertify.db.datasource.DataSourceFactory;

public abstract class BaseTest {

	protected DB db;
	protected DataSourceFactory factory;

	@Before
	public void setup() {
		String fileName = getFileName();
		factory = new MockedDataSourceFactory("C:/db/" + fileName + ".db");
		db = new DbWithReserveLocks(new Data(factory));
	}

	protected abstract String getFileName();
}
