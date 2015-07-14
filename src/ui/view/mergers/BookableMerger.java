package ui.view.mergers;

import javax.swing.JTable;

import ui.controller.ControllerFactory;
import ui.controller.api.BookableController;
import ui.view.ButtonPanel;
import ui.view.MessageBoxPresenter;
import ui.view.TablePanel;
import ui.view.api.BookableView;

public class BookableMerger implements BookableView {

	private static BookableMerger INSTANCE;
	private ButtonPanel buttonPanel;
	private BookableController controller;
	private TablePanel tablePanel;

	static {
		INSTANCE = new BookableMerger();
	}

	private BookableMerger() {
		ControllerFactory factory = new ControllerFactory();
		controller = factory.getContractorController(this);
	}

	public static BookableMerger getInstance() {
		return INSTANCE;
	}

	public void addButtonPanel(ButtonPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	public void bookSelectedContractorWithCustomer(String customerId) {
		controller.bookSelectedContractorWithCustomer(customerId);
	}

	public void search(String name, String location) {
		controller.search(name, location);
	}

	public JTable getJTableWithModel() {
		return controller.getJTableWithModel();
	}

	@Override
	public void enableBookContratorButton(boolean enabled) {
		buttonPanel.enableBookContratorButton(enabled);
		if (!enabled) {
			tablePanel.clearSelection();
		}
	}

	@Override
	public void displayErrorMessage(String errorMessage, String title) {
		MessageBoxPresenter presenter = new MessageBoxPresenter();
		presenter.displayErrorMessageBox(errorMessage, title);
	}

	@Override
	public void clearRowModels() {
		controller.clearRowModels();
	}

	@Override
	public boolean clearTable() {
		return tablePanel.clear();
	}

	public void setTablePanel(TablePanel tablePanel) {
		this.tablePanel = tablePanel;
	}
}
