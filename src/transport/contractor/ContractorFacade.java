package transport.contractor;

import static transport.contractor.ContracorUtills.toContractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import suncertify.db.Data;
import suncertify.db.record.RecordNotFoundException;
import datasource.locator.DataSourceLocationException;
import datasource.locator.DataSourceLocator;
import datasource.locator.DataSourceLocatorFactory;

public class ContractorFacade {

	private Data data;
	private DataSourceLocator dataSourceLocator;

	public ContractorFacade() throws IOException {
		data = new Data();
		dataSourceLocator = getDataSourceLocator();
	}

	public Contractor getContractor(int contractorId) throws RecordNotFoundException {
		String[] contractorDetails;
		contractorDetails = data.read(contractorId);
		return toContractor(contractorId, contractorDetails);

	}

	public List<Contractor> getContractors(String name, String location) throws RecordNotFoundException {
		String first = (name.isEmpty()) ? null : name;
		String second = (location.isEmpty()) ? null : location;

		String[] contractorDetails = { first, second, null, null, null, null };
		int[] ids = data.find(contractorDetails);

		List<Contractor> contractors = new ArrayList<>();
		for (int id : ids) {
			contractors.add(getContractor(id));
		}
		return contractors;

	}

	public void updateContractor(Contractor contractor) throws SecurityException, RecordNotFoundException {
		int contractorId = contractor.getContractorId();
		String[] contractorDetials = contractor.toArray();
		long cookie = data.lock(contractorId);
		data.update(contractorId, contractorDetials, cookie);
	}

	public void setLocation(String location) throws DataSourceLocationException {
		dataSourceLocator.setLocation(location);
		// data = new Data();
	}

	public String getLocation() throws DataSourceLocationException {
		return dataSourceLocator.getLocation();
	}

	private DataSourceLocator getDataSourceLocator() {
		DataSourceLocatorFactory factory = new DataSourceLocatorFactory();
		return factory.getDataSourceLocator();
	}

	// public void createContractorTest(Contractor contractor) throws IOException {
	// String[] contractorDetials = contractor.toArray();
	// contractorDetials[0] = "12ab";
	// contractorDetials[1] = "12ab";
	// data.create(contractorDetials);
	// }

	// public static void main(String[] args) throws IOException {
	// ContractorDataAccessObject converter = new ContractorDataAccessObject();
	// Contractor contractor = converter.getContractor(1);
	// converter.createContractorTest(contractor);
	// }
}
