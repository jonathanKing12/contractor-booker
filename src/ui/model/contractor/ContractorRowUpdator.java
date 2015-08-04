package ui.model.contractor;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static javax.swing.event.TableModelEvent.DELETE;
import static javax.swing.event.TableModelEvent.UPDATE;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import ui.controller.api.ContractorController;

/**
 * Used for selecting and unselecting ContractorRow instances for booking.
 */
public class ContractorRowUpdator implements TableModelListener {

    private ContractorTable contractorTable;
    private ContractorController contractorController;

    /**
     * Constructs a instance of ContractorRowUpdator with the specified contractorTable and contractorController.
     * 
     * @param contractorTable
     *            - the contractorTable
     * @param contractorController
     *            - the contractorController
     */
    public ContractorRowUpdator(ContractorTable contractorTable,
            ContractorController contractorController) {
        this.contractorTable = contractorTable;
        this.contractorController = contractorController;
    }

    /**
     * Updates the ContractorTable and the book contractor button.
     *
     * <p>
     * If the event is an update event. Finds the most recent contratorRow updated, if it is selected unSelect the second most recent updated
     * contratorRow (if that was selected as well). This means only one contratorRow in the ContractorTable can be selected at a given time. The book
     * contractor button is enabled if the most recent contratorRow updated is selected.
     * <p>
     * 
     * <p>
     * If the event is delete event the book contractor button is disabled.
     */
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
        ContractorRow recentUpdatedModel = getMostRecentContractorRowModelUpdated(event);

        for (ContractorRow contractorRow : contractorTable.getAllContractorRowModels()) {

            if (!isSelected(contractorRow)) {
                continue;
            }

            enableBookContractorButton = TRUE;

            if (isSameModel(recentUpdatedModel, contractorRow)) {
                continue;
            }

            if (isSelected(recentUpdatedModel)) {
                unSelectModel(contractorRow);
                break;
            }
        }
        return enableBookContractorButton;
    }

    private boolean isSelected(ContractorRow rowModel) {
        return rowModel.isSelected();
    }

    private ContractorRow getMostRecentContractorRowModelUpdated(TableModelEvent event) {
        return contractorTable.getModel(event.getFirstRow());
    }

    private void unSelectModel(ContractorRow contractorRow) {
        contractorRow.setSelected(FALSE);
        contractorTable.updateContractorRowModel(contractorRow);
    }

    private boolean isSameModel(ContractorRow recentModel, ContractorRow contractorRow) {
        return recentModel.equals(contractorRow);
    }

    private void setEnableTheBookContractorButton(boolean enabled) {
        contractorController.enableBookContratorButton(enabled);
    }
}
