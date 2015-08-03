package ui.model.contractor;

import static java.lang.Boolean.FALSE;

import java.util.Map;

import transport.contractor.Contractor;

public class ContractorRow {
    private Contractor contractor;
    private boolean isSelected;

    /**
     * Constructs a instance of the ContractorRow with the specified contractor.
     * 
     * @param contractor
     *            - the contractor
     */
    public ContractorRow(Contractor contractor) {
        this.contractor = contractor;
    }

    /**
     * Constructs a instance of the ContractorRow with the specified contractor and isSelected.
     * 
     * @param contractor
     *            - the contractor
     * @param isSelected
     *            - Set to {@code true} if the instance is selected
     */
    public ContractorRow(Contractor contractor, boolean isSelected) {
        this(contractor);
        this.isSelected = isSelected;
    }

    /**
     * Converts this object to a map.
     * <p>
     * Delegates to {@link ContractorRowParser#toMap(ContractorRow)}
     * </p>
     * 
     * @return the map
     */
    public Map<String, Object> toMap() {
        ContractorRowParser parser = new ContractorRowParser();
        return parser.toMap(this);
    }

    /**
     * Returns the hash code value for this object.
     * 
     * @return the has code
     */
    @Override
    public int hashCode() {
        return getContractorRowId();
    }

    /**
     * Returns true if the specified object is a ContractorRow that has the same ID as this object.
     * <p>
     * This object being the object that is refereed to by the key work 'this'
     * </p>
     * 
     * @param object
     *            - the object that is compared against the this object.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ContractorRow)) {
            return FALSE;
        }

        ContractorRow rowModel = (ContractorRow) object;
        return getContractorRowId() == rowModel.getContractorRowId();
    }

    /**
     * Returns {@code true} if this object is selected.
     * 
     * @return {@code true} if this object is selected
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Set this object selected state to the specified isSelected.
     * 
     * @param isSelected
     *            {@code true} if this object is selected
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * Returns this object's name.
     * 
     * @return this object's name
     */
    public String getName() {
        return contractor.getName();
    }

    /**
     * Returns this object's location.
     * 
     * @return this object's location
     */
    public String getLocation() {
        return contractor.getLocation();
    }

    /**
     * Returns this object's size.
     * 
     * @return this object's size
     */
    public String getSize() {
        return contractor.getSize();
    }

    /**
     * Returns this object's specialties.
     * 
     * @return this object's specialties
     */
    public String getSpecialties() {
        return contractor.getSpecialties();
    }

    /**
     * Returns this object's rate.
     * 
     * @return this object's rate
     */
    public String getRate() {
        return contractor.getRate();
    }

    /**
     * Returns this object's owner.
     * 
     * @return this object's owner
     */
    public String getOwner() {
        return contractor.getOwner();
    }

    /**
     * Returns this object's ID.
     * 
     * @return this object's ID
     */
    public int getContractorRowId() {
        return contractor.getContractorId();
    }

    /**
     * sets the customer ID of the this object.
     * 
     * @param customerId
     *            - the customer ID
     */
    public void setCustomerId(String customerId) {
        contractor.setOwner(customerId);
    }

    /**
     * Returns this object's contractor.
     * 
     * @return this object's contractor
     */
    public Contractor getContractor() {
        return contractor;
    }
}
