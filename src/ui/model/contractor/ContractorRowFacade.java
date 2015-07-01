package ui.model.contractor;

import javax.swing.table.TableModel;

import runmode.RunModeHelper;
import suncertify.db.filesource.DatabaseFinder;
import transport.contractor.ContractorException;
import transport.delegaters.*;
import ui.controller.api.BookableController;
import ui.model.api.BookableModel;

public class ContractorRowFacade implements BookableModel {

    private ContractorRowSearcher searcher;
    private ContractorRowUpdator updator;
    private ContractorRowBooker booker;
    private ContractorTable model;

    public ContractorRowFacade(BookableController controller) {
        model = new ContractorTable();
        Delegator delegator = getDelegator();

        searcher = new ContractorRowSearcher(model, delegator);
        booker = new ContractorRowBooker(model, delegator);
        updator = new ContractorRowUpdator(model, controller);

        model.addTableModelListener(updator);
    }

    @Override
    public TableModel getTableModel() {
        return model;
    }

    @Override
    public void search(String name, String location) throws ContractorException {
        searcher.search(name, location);
    }

    @Override
    public void bookSelectedContractorWithCustomer(String customerId) throws ContractorException {
        booker.bookSelectedContractorWithCustomer(customerId);
    }

    private Delegator getDelegator() {
        RunModeHelper helper = RunModeHelper.getInstance();

        if (helper.isRunningInAloneMode()) {
            return new LocalDelegator();
        }

        return new RemoteDelegator();
    }

    @Override
    public void resetContractorSearch() {
        searcher.reset();
        DatabaseFinder.getInstance().clear();
    }
}
