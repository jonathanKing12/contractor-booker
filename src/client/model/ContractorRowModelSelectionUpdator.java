package client.model;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static javax.swing.event.TableModelEvent.DELETE;
import static javax.swing.event.TableModelEvent.UPDATE;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import client.controller.BookableController;

public class ContractorRowModelSelectionUpdator implements TableModelListener {

	private ContractorTableModel tableModel;
	private BookableController controller;

	public ContractorRowModelSelectionUpdator(ContractorTableModel tableModel, BookableController controller) {
		this.tableModel = tableModel;
		this.controller = controller;
	}

	@Override
	public void tableChanged(TableModelEvent event) {
		if (event.getType() == UPDATE) {
			boolean setToEnabled = updateModel(event);
			setEnableTheBookContractorButton(setToEnabled);
		}

		if (event.getType() == DELETE) {
			setEnableTheBookContractorButton(FALSE);
		}
	}

	private boolean updateModel(TableModelEvent event) {
		boolean enableBookContractorButton = FALSE;
		ContractorRowModel recentModel = getMostRecentContractorRowModelUpdated(event);

		for (ContractorRowModel previousModel : tableModel.getAllContractorRowModels()) {

			if (!isSelected(previousModel)) {
				continue;
			}

			enableBookContractorButton = TRUE;

			if (isSameModel(recentModel, previousModel)) {
				continue;
			}

			if (isSelected(recentModel)) {
				deselectedModel(previousModel);
				break;
			}
		}
		return enableBookContractorButton;
	}

	private boolean isSelected(ContractorRowModel rowModel) {
		return rowModel.isSelected();
	}

	private ContractorRowModel getMostRecentContractorRowModelUpdated(TableModelEvent event) {
		return tableModel.getModel(event.getFirstRow());
	}

	private void deselectedModel(ContractorRowModel previousModel) {
		previousModel.setSelected(FALSE);
		tableModel.updateContractorRowModel(previousModel);
	}

	private boolean isSameModel(ContractorRowModel recentModel, ContractorRowModel previousModel) {
		return recentModel.equals(previousModel);
	}

	private void setEnableTheBookContractorButton(boolean enabled) {
		controller.enableBookContratorButton(enabled);
	}

}
