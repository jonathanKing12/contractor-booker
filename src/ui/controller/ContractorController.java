package ui.controller;

import java.io.IOException;

import javax.swing.JTable;

import transport.contractor.ContractorException;
import ui.controller.api.BookableController;
import ui.model.api.BookableModel;
import ui.model.contractor.ContractorRowFacade;
import ui.view.api.BookableView;

public class ContractorController implements BookableController {

    private BookableModel model;
    private BookableView view;

    public ContractorController(BookableView contractorViewMerger) {
        this.view = contractorViewMerger;
        model = new ContractorRowFacade(this);
    }

    @Override
    public JTable getJTableWithModel() {
        return new JTable(model.getTableModel());
    }

    @Override
    public void search(String name, String location) {
        try {
            model.search(name, location);
        } catch (IOException | ContractorException e) {
            view.displayErrorMessage(e.getMessage(), "Error when searching");
        }
    }

    @Override
    public void bookSelectedContractorWithCustomer(String customerId) {
        try {
            model.bookSelectedContractorWithCustomer(customerId);
        } catch (IOException | ContractorException e) {
            view.displayErrorMessage(e.getMessage(), "Error when booking");
        }
    }

    @Override
    public void enableBookContratorButton(boolean modelSelected) {
        view.enableBookContratorButton(modelSelected);
    }

    @Override
    public void resetContractorSearch() {
        model.resetContractorSearch();
    }
}
