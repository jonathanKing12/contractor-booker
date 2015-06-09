package client.model;

import static java.lang.Boolean.FALSE;

import java.util.Map;

import transport.contractor.Contractor;

public class ContractorRowModel {
	private Contractor contractor;
	private boolean isSelected;

	public ContractorRowModel(Contractor contractor) {
		this.contractor = contractor;
	}

	public ContractorRowModel(Contractor contractor, boolean isSelected) {
		this(contractor);
		this.isSelected = isSelected;
	}

	public Map<String, Object> toMap() {
		return ContractorRowModelUtil.toMap(this);
	}

	@Override
	public int hashCode() {
		return getContractorRowId();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ContractorRowModel)) {
			return FALSE;
		}

		ContractorRowModel rowModel = (ContractorRowModel) object;
		return getContractorRowId() == rowModel.getContractorRowId();
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getName() {
		return contractor.getName();
	}

	public String getLocation() {
		return contractor.getLocation();
	}

	public String getSize() {
		return contractor.getSize();
	}

	public String getSpecialties() {
		return contractor.getSpecialties();
	}

	public String getRate() {
		return contractor.getRate();
	}

	public String getOwner() {
		return contractor.getOwner();
	}

	public int getContractorRowId() {
		return contractor.getContractorId();
	}

	public void setCustomerId(String customerId) {
		contractor.setOwner(customerId);
	}

	public Contractor getContractor() {
		return contractor;
	}
}
