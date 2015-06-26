package gui.controller;

import gui.controller.api.BookableController;
import gui.model.api.BookableModel;
import gui.model.contractor.ContractorRowFacade;
import gui.view.api.BookableView;

import java.io.IOException;

import javax.swing.JTable;

import transport.contractor.ContractorException;

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
			e.printStackTrace();
		}
	}

	@Override
	public void bookSelectedContractorWithCustomer(String customerId) {
		try {
			model.bookSelectedContractorWithCustomer(customerId);
		} catch (IOException | ContractorException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enableBookContratorButton(boolean modelSelected) {
		view.enableBookContratorButton(modelSelected);
	}
}