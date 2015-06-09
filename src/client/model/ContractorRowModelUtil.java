package client.model;

import static client.model.Column.LOCATION;
import static client.model.Column.NAME;
import static client.model.Column.OWNER;
import static client.model.Column.RATE;
import static client.model.Column.SELECTED;
import static client.model.Column.SIZE;
import static client.model.Column.SPECIALTIES;
import static constants.Constants.CONTRACTOR_ID_KEY;

import java.util.HashMap;
import java.util.Map;

import transport.contractor.Contractor;
import transport.contractor.ContractorBuilder;

public class ContractorRowModelUtil {

	public static ContractorRowModel toContractorRowModel(Map<String, Object> map) {

		boolean isSelected = getBoolean(map);
		String name = getString(map, NAME);
		String location = getString(map, LOCATION);
		String specialties = getString(map, SPECIALTIES);
		String size = getString(map, SIZE);
		String rate = getString(map, RATE);
		String owner = getString(map, OWNER);
		int contractorId = getInt(map, CONTRACTOR_ID_KEY);

		ContractorBuilder builder = new ContractorBuilder();
		Contractor contractor = builder.addName(name).addLocation(location).addSpecialties(specialties).addSize(size).addRate(rate).addOwner(owner)
				.addContractorId(contractorId).build();

		return new ContractorRowModel(contractor, isSelected);
	}

	public static Map<String, Object> toMap(ContractorRowModel rowModel) {
		final Map<String, Object> map = new HashMap<>();
		map.put(SELECTED.getName(), rowModel.isSelected());
		map.put(NAME.getName(), rowModel.getName());
		map.put(LOCATION.getName(), rowModel.getLocation());
		map.put(RATE.getName(), rowModel.getRate());
		map.put(SPECIALTIES.getName(), rowModel.getSpecialties());
		map.put(OWNER.getName(), rowModel.getOwner());
		map.put(SIZE.getName(), rowModel.getSize());
		map.put(CONTRACTOR_ID_KEY, rowModel.getContractorRowId());
		return map;
	}

	private static String getString(Map<String, Object> map, Column column) {
		return (String) map.get(column.getName());
	}

	private static int getInt(Map<String, Object> map, String property) {
		return (int) map.get(property);
	}

	private static Boolean getBoolean(Map<String, Object> map) {
		return (Boolean) map.get(SELECTED.getName());
	}

}
