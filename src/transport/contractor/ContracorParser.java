package transport.contractor;

import static constants.Constants.CONTRACTOR_DETAILS_COUNT;
import static constants.Constants.LOCATION_INDEX;
import static constants.Constants.NAME_INDEX;
import static constants.Constants.OWNER_INDEX;
import static constants.Constants.RATE_INDEX;
import static constants.Constants.SIZE_INDEX;
import static constants.Constants.SPECIALTIES_INDEX;

public class ContracorParser {

	public String[] toArray(Contractor contractor) {
		String[] array = new String[CONTRACTOR_DETAILS_COUNT];
		array[NAME_INDEX] = contractor.getName();
		array[LOCATION_INDEX] = contractor.getLocation();
		array[SPECIALTIES_INDEX] = contractor.getSpecialties();
		array[SIZE_INDEX] = contractor.getSize();
		array[RATE_INDEX] = contractor.getRate();
		array[OWNER_INDEX] = contractor.getOwner();
		return array;
	}

	public Contractor toContractor(int contractorId, String[] contractorDetials) {
		String name = contractorDetials[NAME_INDEX];
		String location = contractorDetials[LOCATION_INDEX];
		String specialties = contractorDetials[SPECIALTIES_INDEX];
		String size = contractorDetials[SIZE_INDEX];
		String rate = contractorDetials[RATE_INDEX];
		String owner = contractorDetials[OWNER_INDEX];

		ContractorBuilder builder = new ContractorBuilder();
		return builder.addName(name).addLocation(location).addSpecialties(specialties).addSize(size).addRate(rate).addOwner(owner)
				.addContractorId(contractorId).build();
	}
}
