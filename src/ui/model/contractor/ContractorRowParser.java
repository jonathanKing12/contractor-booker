package ui.model.contractor;

import static constants.Constants.CONTRACTOR_ID_KEY;
import static ui.model.contractor.Column.*;

import java.util.HashMap;
import java.util.Map;

import transport.contractor.Contractor;
import transport.contractor.ContractorBuilder;

/**
 * Converts a ContractorRow instance to a map and vise versa.
 */
public class ContractorRowParser {

    /**
     * Returns a ContractorRow representation of the specified map.
     * 
     * @param map
     *            - the map
     * @return the ContractorRow
     */
    public ContractorRow toContractorRow(Map<String, Object> map) {

        boolean isSelected = getBoolean(map);
        String name = getString(map, NAME);
        String location = getString(map, LOCATION);
        String specialties = getString(map, SPECIALTIES);
        String size = getString(map, SIZE);
        String rate = getString(map, RATE);
        String owner = getString(map, OWNER);
        int contractorId = getInt(map, CONTRACTOR_ID_KEY);

        ContractorBuilder builder = new ContractorBuilder();
        Contractor contractor = builder.addName(name).addLocation(location)
                .addSpecialties(specialties).addSize(size).addRate(rate).addOwner(owner)
                .addContractorId(contractorId).build();

        return new ContractorRow(contractor, isSelected);
    }

    /**
     * Returns a map representation of the specified contractorRow.
     * 
     * @param contractorRow
     *            - the contractorRow
     * @return the map
     */
    public Map<String, Object> toMap(ContractorRow contractorRow) {

        final Map<String, Object> map = new HashMap<>();
        map.put(SELECTED.getName(), contractorRow.isSelected());
        map.put(NAME.getName(), contractorRow.getName());
        map.put(LOCATION.getName(), contractorRow.getLocation());
        map.put(RATE.getName(), contractorRow.getRate());
        map.put(SPECIALTIES.getName(), contractorRow.getSpecialties());
        map.put(OWNER.getName(), contractorRow.getOwner());
        map.put(SIZE.getName(), contractorRow.getSize());
        map.put(CONTRACTOR_ID_KEY, contractorRow.getContractorRowId());
        return map;
    }

    private String getString(Map<String, Object> map, Column column) {
        return (String) map.get(column.getName());
    }

    private int getInt(Map<String, Object> map, String property) {
        return (int) map.get(property);
    }

    private Boolean getBoolean(Map<String, Object> map) {
        return (Boolean) map.get(SELECTED.getName());
    }
}
