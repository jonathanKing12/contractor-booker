package ui.model.contractor;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static javax.swing.event.TableModelEvent.DELETE;
import static javax.swing.event.TableModelEvent.UPDATE;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import ui.controller.api.BookableController;

public class ContractorRowUpdator implements TableModelListener {

	private ContractorTable tableModel;
	private BookableController controller;

	public ContractorRowUpdator(ContractorTable tableModel, BookableController controller) {
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
		ContractorRow recentModel = getMostRecentContractorRowModelUpdated(event);

		for (ContractorRow contractorRow : tableModel.getAllContractorRowModels()) {

			if (!isSelected(contractorRow)) {
				continue;
			}

			enableBookContractorButton = TRUE;

			if (isSameModel(recentModel, contractorRow)) {
				continue;
			}

			if (isSelected(recentModel)) {
				deselectModel(contractorRow);
				break;
			}
		}
		return enableBookContractorButton;
	}

	private boolean isSelected(ContractorRow rowModel) {
		return rowModel.isSelected();
	}

	private ContractorRow getMostRecentContractorRowModelUpdated(TableModelEvent event) {
		return tableModel.getModel(event.getFirstRow());
	}

	private void deselectModel(ContractorRow previousModel) {
		previousModel.setSelected(FALSE);
		tableModel.updateContractorRowModel(previousModel);
	}

	private boolean isSameModel(ContractorRow recentModel, ContractorRow previousModel) {
		return recentModel.equals(previousModel);
	}

	private void setEnableTheBookContractorButton(boolean enabled) {
		controller.enableBookContratorButton(enabled);
	}
}
