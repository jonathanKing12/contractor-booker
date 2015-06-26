package gui.model.contractor;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static javax.swing.event.TableModelEvent.DELETE;
import static javax.swing.event.TableModelEvent.UPDATE;
import gui.controller.api.BookableController;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

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

		for (ContractorRow previousModel : tableModel.getAllContractorRowModels()) {

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

	private boolean isSelected(ContractorRow rowModel) {
		return rowModel.isSelected();
	}

	private ContractorRow getMostRecentContractorRowModelUpdated(TableModelEvent event) {
		return tableModel.getModel(event.getFirstRow());
	}

	private void deselectedModel(ContractorRow previousModel) {
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
