package client.controller;

import java.io.IOException;

import javax.swing.JTable;

import transport.contractor.ContractorException;
import client.model.BookableModel;
import client.model.ContractorModel;
import client.view.View;

public class ContractorController implements BookableController {

	private BookableModel model;
	private View view;

	public ContractorController(View view) {
		this.view = view;
		model = new ContractorModel(this);
	}

	@Override
	public JTable getJTableWithModel() {
		return new JTable(model.getTableModel());
	}

	@Override
	public void search(String name, String location) {
		try {
			model.search(name, location);
		} catch (IOException | ContractorException exception) {
			System.out.println("eror at reading contractor " + exception.getMessage());
		}
	}

	@Override
	public void bookSelectedContractorWithCustomer(String customerId) {
		try {
			model.bookSelectedContractorWithCustomer(customerId);
		} catch (IOException | ContractorException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void enableBookContratorButton(boolean modelSelected) {
		view.enableBookContratorButton(modelSelected);
	}
}
