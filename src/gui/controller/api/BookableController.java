package gui.controller.api;

import javax.swing.JTable;

public interface BookableController {

	JTable getJTableWithModel();

	void search(String name, String location);

	void bookSelectedContractorWithCustomer(String customerId);

	void enableBookContratorButton(boolean modelSelected);
}
